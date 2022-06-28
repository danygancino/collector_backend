import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Catalogo, CatalogoProxy } from 'src/app/models/catalogo';
import { GrupoFamiliarProxy, NecesidadesEspecificasEntityProxy, NecesidadesEspecificasHabitabilidad, NecesidadesEspecificasProteccion } from 'src/app/models/grupoFamiliar';
import { BeneficiarioService } from 'src/app/services/beneficiario.service';
import { CatalogoService } from 'src/app/services/catalogo.service';
import { Location } from '@angular/common'
import { ActivatedRoute } from '@angular/router';
import { UtilService } from 'src/app/services/util.service';
import { CatalogoItems } from 'src/app/models/catalogo.items.enum';

@Component({
  selector: 'app-necesidades-especificas',
  templateUrl: './necesidades-especificas.component.html',
  styleUrls: ['./necesidades-especificas.component.scss']
})
export class NecesidadesEspecificasComponent implements OnInit {

  submitted = false;
  catalogos: CatalogoProxy;
  necesidadesEspecificasProteccion: NecesidadesEspecificasProteccion[] = new Array();
  necesidadesEspecificasHabitabilidad: NecesidadesEspecificasHabitabilidad[] = new Array();
  necesidadesEspecificas: NecesidadesEspecificasEntityProxy = new NecesidadesEspecificasEntityProxy;
  idGrupoFamiliar: string
  grupoFamiliar: GrupoFamiliarProxy;
  adultosMayores: number = 0;
  discapacitados: number = 0;
  lgb: number = 0;
  edadAdultoMayor = 65;
  disableNecesidadesProteccion = [];

  constructor(private fb: FormBuilder, private catalogoService: CatalogoService,
    private beneficiarioService: BeneficiarioService,
    private location: Location,
    route: ActivatedRoute,
    private utilService: UtilService) {
    this.catalogoService.getCatalogo().then(c => {
      this.catalogos = c;

      this.createForm();
    });
    this.idGrupoFamiliar = route.snapshot.paramMap.get("idGrupoFamiliar");
  }

  async ngOnInit(): Promise<void> {
  }

  async createForm() {
    this.grupoFamiliar = await this.beneficiarioService.getGrupoFamiliarById(this.idGrupoFamiliar);
    if (this.grupoFamiliar.necesidadesEspecifica != null) {
      this.necesidadesEspecificas = this.grupoFamiliar.necesidadesEspecifica;

    }

    this.catalogos.necesidadesEspecificasProteccion.forEach(nep => {
      let catidad: number = this.necesidadesEspecificas.necesidadesEspecificasProteccion?.filter(nept => nept.necesidadEspecificaProteccionId == +nep.id).shift().cantidad | 0;
      this.necesidadesEspecificasProteccion.push(new NecesidadesEspecificasProteccion(+nep.id, catidad))
    });

    this.catalogos.necesidadesEspecificasHabitabilidad.forEach(neh => {
      let catidad: number = this.necesidadesEspecificas.necesidadesEspecificasHabitabilidad?.filter(nept => nept.necesidadEspecificaHabitabilidadId == +neh.id).shift()?.cantidad | 0;
      this.necesidadesEspecificasHabitabilidad.push(new NecesidadesEspecificasHabitabilidad(+neh.id, catidad))
    });

    this.analizeBeneficiario()

  }
  analizeBeneficiario() {
    let catalogoAdultoMayor = this.catalogos.necesidadesEspecificasProteccion.filter(ne => ne.nombre == "ADULTO/A MAYOR")?.shift().id;
    let catalogoLGBITQ = this.catalogos.necesidadesEspecificasProteccion.filter(ne => ne.nombre == "POBLACION LGBTIQ+")?.shift().id;
    let catalogoDiscapacitados = this.catalogos.necesidadesEspecificasProteccion.filter(ne => ne.nombre == "DISCAPACIDAD")?.shift().id;

    this.disableNecesidadesProteccion.push(+catalogoAdultoMayor)
    this.disableNecesidadesProteccion.push(+catalogoLGBITQ)
    this.disableNecesidadesProteccion.push(+catalogoDiscapacitados)

    this.grupoFamiliar.beneficiarios.forEach(b => {
      if (this.utilService.getEdad(b.fechaNacimiento) >= this.edadAdultoMayor)
        this.adultosMayores++;
      if ("SI" === this.catalogoService.getLabel(CatalogoItems.lgbtis, b.lgbtiId.toString()))
        this.lgb++;
      if (this.catalogoService.getLabel(CatalogoItems.discapacidades, b.discapacidadId.toString()) === "SI")
        this.discapacitados++;
    })

    this.necesidadesEspecificasProteccion.filter(n => n.necesidadEspecificaProteccionId === +catalogoAdultoMayor).shift().cantidad = this.adultosMayores;
    this.necesidadesEspecificasProteccion.filter(n => n.necesidadEspecificaProteccionId === +catalogoLGBITQ).shift().cantidad = this.lgb;
    this.necesidadesEspecificasProteccion.filter(n => n.necesidadEspecificaProteccionId === +catalogoDiscapacitados).shift().cantidad = this.discapacitados;

  }


  async saveNecesidades() {
    this.submitted = true;
    this.necesidadesEspecificas.necesidadesEspecificasHabitabilidad = this.necesidadesEspecificasHabitabilidad;
    this.necesidadesEspecificas.necesidadesEspecificasProteccion = this.necesidadesEspecificasProteccion;

    if (this.necesidadesEspecificas != null) {
      await this.beneficiarioService.saveOrUpdateNecesidades(this.necesidadesEspecificas, this.idGrupoFamiliar);
      this.location.back();
    }
  }
}

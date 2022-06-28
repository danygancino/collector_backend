import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Location } from '@angular/common'
import { BreadcrumbService } from 'src/app/app.breadcrumb.service';
import { CatalogoProxy, PaisesEntityProxy } from 'src/app/models/catalogo';
import { Entidad } from 'src/app/models/entidad';
import { BeneficiariosEntityProxy, GrupoFamiliarProxy } from 'src/app/models/grupoFamiliar';
import { TipoEntidad } from 'src/app/models/tipoEntidad.enum';
import { CatalogoService } from 'src/app/services/catalogo.service';
import { EntidadService } from 'src/app/services/entity.service';
import { v4 as uuidv4 } from 'uuid';
import { BeneficiarioService } from 'src/app/services/beneficiario.service';
import { UtilService } from 'src/app/services/util.service';

@Component({
  selector: 'app-beneficiario',
  templateUrl: './beneficiario.component.html',
  styleUrls: ['./beneficiario.component.scss']
})
export class BeneficiarioComponent implements OnInit {

  beneficiarioForm: FormGroup;
  submitted = false;
  beneficiario: BeneficiariosEntityProxy;
  grupoFamiliar: GrupoFamiliarProxy;
  idGrupoFamiliar: string;
  idBeneficiario: string;
  catalogos: CatalogoProxy;
  pais: PaisesEntityProxy;
  profesionesIds: number[] = [];
  oficiosIds: number[] = [];
  maxDate = new Date()
  minDate = new Date()

  constructor(private router: Router, private utilService: UtilService, private breadcrumbService: BreadcrumbService, private fb: FormBuilder, route: ActivatedRoute,
    private catalogoService: CatalogoService, private beneficiarioService: BeneficiarioService, private location: Location) {
    this.idBeneficiario = route.snapshot.paramMap.get("idBeneficiario");
    this.idGrupoFamiliar = route.snapshot.paramMap.get("idGrupoFamiliar");
    this.minDate.setFullYear(this.maxDate.getFullYear() - 100) 
  }

  async ngOnInit() {
    await this.cargarDatos().then(f => this.createForm());
  }

  async cargarDatos() {
    this.grupoFamiliar = await this.beneficiarioService.getGrupoFamiliarById(this.idGrupoFamiliar);

    await this.catalogoService.getCatalogo().then(c => {
      this.catalogos = c;
      this.pais = c.paises.filter(p => p.nombre = "Ecuador").shift();
    });
    this.beneficiario = new BeneficiariosEntityProxy;
    if (this.idBeneficiario == null || this.idBeneficiario == "0") {
      this.beneficiario.grupoFamiliarId = this.grupoFamiliar.id;
      if (this.grupoFamiliar.beneficiarios.length == 0) { //Si es el primer beneficiario le obligo que sea punto focal
        this.catalogos.nivelesParentesco = this.catalogos.nivelesParentesco.filter(np => np.nombre === "PUNTO FOCAL FAMILIAR")
      } else {
        this.catalogos.nivelesParentesco = this.catalogos.nivelesParentesco.filter(np => np.nombre != "PUNTO FOCAL FAMILIAR") // si no es el primero quito punto focal de nivelParentesco
      }
    }
    else {
      let beneficiarioSaved = await this.beneficiarioService.getBeneficiariosBygrupoFamiliarIdAndBeneficiarioId(this.idGrupoFamiliar, this.idBeneficiario) as BeneficiariosEntityProxy;
      
      beneficiarioSaved.grupoFamiliarId = this.idGrupoFamiliar;
      this.beneficiario.mergeBeneficiario(beneficiarioSaved);
      this.breadcrumbService.setItems([
        { label: 'Beneficiario ' + this.beneficiario.nombres, routerLink: ['/pages/form'] }
      ]);
    }
  }

  async saveBeneficiario() {

    this.submitted = true;

    if (this.beneficiario != null && this.beneficiarioForm.valid) {
      this.beneficiario.mergeBeneficiario(this.beneficiarioForm.getRawValue());
      this.beneficiario.nombres = this.beneficiario.nombres.toUpperCase();
      this.beneficiario.oficiosIds = this.oficiosIds;
      this.beneficiario.profesionesIds = this.profesionesIds;
      await this.beneficiarioService.saveOrUpdateBeneficiario(this.beneficiario);
      this.location.back();
    } else
      this.utilService.warnMessage("Revise la información", "");
  }

  createForm() {
    // this.beneficiarioForm = this.fb.group(new BeneficiariosEntityProxy());
    this.oficiosIds = this.beneficiario.oficiosIds;
    this.profesionesIds = this.beneficiario.profesionesIds;

    this.beneficiarioForm = this.fb.group({
      sexoId: [this.beneficiario.sexoId, Validators.required],
      nivelParentescoId: [this.beneficiario.nivelParentescoId, Validators.required],
      generoId: [this.beneficiario.generoId],
      nombres: [this.beneficiario.nombres, Validators.required],
      discapacidadId: [this.beneficiario.discapacidadId, Validators.required],
      estatutoMigratorioId: [this.beneficiario.estatutoMigratorioId, Validators.required],
      etniaId: [this.beneficiario.etniaId, Validators.required],
      lgbtiId: [this.beneficiario.lgbtiId, Validators.required],
      motivoSalidaPaisId: [this.beneficiario.motivoSalidaPaisId],
      nacionalidadId: [this.beneficiario.nacionalidadId, Validators.required],
      nivelEscolaridadId: [this.beneficiario.nivelEscolaridadId],
      situacionMigratoriaId: [this.beneficiario.situacionMigratoriaId, Validators.required],
      tipoDocumentoId: [this.beneficiario.tipoDocumentoId, Validators.required],
      fechaNacimiento: [ this.beneficiario.fechaNacimiento, Validators.required],
      fechaIngresoPais: [this.beneficiario.fechaIngresoPais],
      numeroDocumento: [this.beneficiario.numeroDocumento, Validators.required],
      ciudadId: [this.beneficiario.ciudadId, Validators.required],
      sector: [this.beneficiario.sector],
      direccion: [this.beneficiario.direccion],
      telefono: [this.beneficiario.telefono, Validators.required],
      observaciones: [this.beneficiario.observaciones],
    });

    if (this.beneficiario != null) {
      if ((+this.catalogos.nivelesParentesco.find(np => np.nombre === "PUNTO FOCAL FAMILIAR")?.id) == this.beneficiario.nivelParentescoId) { //Si esta editando y no es punto focal      
        this.beneficiarioForm.controls['nivelParentescoId'].disable();   
      }
    }
  }
}
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Location } from '@angular/common'
import { BreadcrumbService } from 'src/app/app.breadcrumb.service';
import { Catalogo, CatalogoProxy } from 'src/app/models/catalogo';
import { Asistencias, GrupoFamiliarProxy, IntegracionesSocioEconomicasEntityProxy } from 'src/app/models/grupoFamiliar';
import { BeneficiarioService } from 'src/app/services/beneficiario.service';
import { CatalogoService } from 'src/app/services/catalogo.service';
import { UtilService } from 'src/app/services/util.service';


@Component({
  selector: 'app-apoyo-detalle',
  templateUrl: './apoyo-detalle.component.html',
  styleUrls: ['./apoyo-detalle.component.scss']
})
export class ApoyoDetalleComponent implements OnInit {

  formValue: FormGroup;
  submitted = false;
  asistencia: Asistencias
  sortOrder: number;
  sortField: string;
  grupoFamiliar: GrupoFamiliarProxy;
  idGrupoFamiliar: string
  idApoyoDetalle: string
  catalogos: CatalogoProxy;
  componenteId: Catalogo;
  tipoAsistencia: number;
  tiposAsistencia: Catalogo[];

  nombre: string;
  numero: string;
  telefono: string;
  espacios: string;  


  constructor(private breadcrumbService: BreadcrumbService, private router: Router, route: ActivatedRoute, private catalogoService: CatalogoService, private utilService: UtilService,
    private fb: FormBuilder, private beneficiarioService: BeneficiarioService, private location: Location) {
    this.catalogoService.getCatalogo().then(c => {
      this.catalogos = c;
    });

    this.idGrupoFamiliar = route.snapshot.paramMap.get("idGrupoFamiliar");
    this.idApoyoDetalle = route.snapshot.paramMap.get("idApoyoDetalle");
    this.breadcrumbService.setItems([
      { label: 'Apoyo AVSI ' }
    ]);
  }

  async ngOnInit() {
    await this.cargarDatos();
    this.createForm();
  }

  async cargarDatos() {
    this.asistencia = await this.beneficiarioService.getAsistenciaBygrupoFamiliarIdAndBeneficiarioId(this.idGrupoFamiliar, this.idApoyoDetalle);
    if (this.asistencia == null)
      this.asistencia = new Asistencias();
  }

  nuevo() {
    this.router.navigate(['/pages/integracion-socioeconomica-detalle', { idGrupoFamiliar: this.idGrupoFamiliar }]);
  }

  async save() {

    this.submitted = true;

    if(this.componenteId == null ){
      this.utilService.warnMessage("Ingrese un componente", "");
      return
    }

    if(this.tipoAsistencia == null){
      this.utilService.warnMessage("Ingrese un tipo de asistencia", "");
      return
    }

    if (this.asistencia != null && this.formValue.valid) {
      this.asistencia.fecha = this.formValue.controls['fecha'].value;
      this.asistencia.componenteId = +this.componenteId["id"];
      this.asistencia.tipoAsistenciaId = this.tipoAsistencia;
      this.asistencia.nombrePropietarioVivienda = this.nombre;
      this.asistencia.numeroDocumentoPropietarioVivienda = this.numero;
      this.asistencia.telefonoPropietarioVivienda = this.telefono;
      this.asistencia.numeroEspacioHabitables = this.espacios;

      this.asistencia.observacion = this.formValue.controls['observacion'].value;
      await this.beneficiarioService.saveOrUpdateAsistencia(this.asistencia, this.idGrupoFamiliar);
      this.location.back();
    }
  }

  createForm() {
    // this.beneficiarioForm = this.fb.group(new BeneficiariosEntityProxy());
    this.catalogos.componentes.forEach(c => {
      if(+c.id == this.asistencia.componenteId){
        this.componenteId  = c;
        this.loadTipoAsistencia(c);
      }      
    })
    this.tipoAsistencia = this.asistencia.tipoAsistenciaId;
    this.formValue = this.fb.group({
      fecha: [this.asistencia.fecha, Validators.required],    
      observacion: [this.asistencia.observacion],
    })

    this.nombre = this.asistencia.nombrePropietarioVivienda;
    this.numero = this.asistencia.numeroDocumentoPropietarioVivienda;
    this.telefono = this.asistencia.telefonoPropietarioVivienda;
    this.espacios = this.asistencia.numeroEspacioHabitables;
  }

  loadTipoAsistencia(componente){
      this.tiposAsistencia = componente["tiposAsistencia"]
  }

  isHabitabilidad(){
    if(this.tiposAsistencia && this.tipoAsistencia){
      let tipoAsistenciaSeleccionado: Catalogo =  this.tiposAsistencia.filter(ta => ta.id == this.tipoAsistencia.toString()).shift();
      if(tipoAsistenciaSeleccionado.nombre == "CBI ARRIENDO" || tipoAsistenciaSeleccionado.nombre == "ACONDICIONAMIENTO VIVIENDA MULTIFAMILIAR") return true;
    }    
    return false;
  }
}
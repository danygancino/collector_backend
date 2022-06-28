import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Location } from '@angular/common'
import { BreadcrumbService } from 'src/app/app.breadcrumb.service';
import { CatalogoProxy } from 'src/app/models/catalogo';
import { GrupoFamiliarProxy, IntegracionesSocioEconomicasEntityProxy, SeguimientosEntityProxy } from 'src/app/models/grupoFamiliar';
import { BeneficiarioService } from 'src/app/services/beneficiario.service';
import { CatalogoService } from 'src/app/services/catalogo.service';
import { UtilService } from 'src/app/services/util.service';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-seguimiento-detalle',
  templateUrl: './seguimiento-detalle.component.html',
  styleUrls: ['./seguimiento-detalle.component.scss']
})
export class SeguimientoDetalleComponent implements OnInit {
  
  submitted = false;
  formValue: FormGroup;
  seguimiento: SeguimientosEntityProxy
  sortOrder: number;
  sortField: string;
  grupoFamiliar: GrupoFamiliarProxy;
  idGrupoFamiliar: string
  idSeguimiento: string
  catalogos: CatalogoProxy;

  constructor(private messageSerice: MessageService,private utilService: UtilService, private breadcrumbService: BreadcrumbService, private router: Router, route: ActivatedRoute, private catalogoService: CatalogoService,
    private fb: FormBuilder, private beneficiarioService: BeneficiarioService, private location: Location) {
    this.catalogoService.getCatalogo().then(c => {
      this.catalogos = c;
    });

    this.idGrupoFamiliar = route.snapshot.paramMap.get("idGrupoFamiliar");
    this.idSeguimiento = route.snapshot.paramMap.get("idSeguimiento");
    this.breadcrumbService.setItems([
      { label: 'Seguimiento del caso' }
    ]);
  }

  async ngOnInit() {
    await this.cargarDatos();
    this.createForm();
  }

  async cargarDatos() {
    this.seguimiento = await this.beneficiarioService.getSeguimientoBygrupoFamiliarIdAndBeneficiarioId(this.idGrupoFamiliar, this.idSeguimiento);
    if (this.seguimiento == null)
      this.seguimiento = new SeguimientosEntityProxy();
  }


  async saveIntegracion() {
    this.submitted = true;    
    if (this.seguimiento != null && this.formValue.valid) {
      this.seguimiento.fecha = this.formValue.controls['fecha'].value;
      this.seguimiento.componenteId = this.formValue.controls['componente'].value;
      this.seguimiento.tipoSeguimientoId = this.formValue.controls['tipoSeguimiento'].value;
      this.seguimiento.observaciones = this.formValue.controls['observaciones'].value;
      await this.beneficiarioService.saveOrUpdateSeguimiento(this.seguimiento, this.idGrupoFamiliar);
      this.location.back();
    }
  }

  createForm() {
    this.formValue = this.fb.group({
      fecha: [this.seguimiento.fecha, Validators.required],
      componente: [this.seguimiento.componenteId, Validators.required],
      tipoSeguimiento: [this.seguimiento.tipoSeguimientoId, Validators.required],
      observaciones: [this.seguimiento.observaciones],
    })
  }

}

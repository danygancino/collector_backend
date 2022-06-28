import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Location } from '@angular/common'
import { BreadcrumbService } from 'src/app/app.breadcrumb.service';
import { CatalogoProxy } from 'src/app/models/catalogo';
import { GrupoFamiliarProxy, IntegracionesSocioEconomicasEntityProxy } from 'src/app/models/grupoFamiliar';
import { BeneficiarioService } from 'src/app/services/beneficiario.service';
import { CatalogoService } from 'src/app/services/catalogo.service';
import { UtilService } from 'src/app/services/util.service';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-integracion-socioeconomica-detalle',
  templateUrl: './integracion-socioeconomica-detalle.component.html',
  styleUrls: ['./integracion-socioeconomica-detalle.component.scss']
})
export class IntegracionSocioeconomicaDetalleComponent implements OnInit {

  submitted = false;
  formValue: FormGroup;
  integracion: IntegracionesSocioEconomicasEntityProxy
  sortOrder: number;
  sortField: string;
  grupoFamiliar: GrupoFamiliarProxy;
  idGrupoFamiliar: string
  idIntegracionDetalle: string
  catalogos: CatalogoProxy;

  tiposAyuda: string[];

  constructor(private messageSerice: MessageService,private utilService: UtilService, private breadcrumbService: BreadcrumbService, private router: Router, route: ActivatedRoute, private catalogoService: CatalogoService,
    private fb: FormBuilder, private beneficiarioService: BeneficiarioService, private location: Location) {
    this.catalogoService.getCatalogo().then(c => {
      this.catalogos = c;
    });

    this.idGrupoFamiliar = route.snapshot.paramMap.get("idGrupoFamiliar");
    this.idIntegracionDetalle = route.snapshot.paramMap.get("idIntegracionDetalle");
    this.breadcrumbService.setItems([
      { label: 'Integración socioeconómica ' }
    ]);
  }

  async ngOnInit() {
    await this.cargarDatos();
    this.createForm();
  }

  async cargarDatos() {
    this.integracion = await this.beneficiarioService.getIntegracionBygrupoFamiliarIdAndBeneficiarioId(this.idGrupoFamiliar, this.idIntegracionDetalle);
    if (this.integracion == null)
      this.integracion = new IntegracionesSocioEconomicasEntityProxy();
  }

  nuevo() {
    this.router.navigate(['/pages/integracion-socioeconomica-detalle', { idGrupoFamiliar: this.idGrupoFamiliar }]);
  }

  async saveIntegracion() {
    this.submitted = true;
    if(this.tiposAyuda == null || this.tiposAyuda.length <= 0){
      this.utilService.warnMessage("Ingrese al menos 1 tipo de ayuda", "");
      return
    }
    if (this.integracion != null && this.formValue.valid) {
      this.integracion.observaciones = this.formValue.controls['observaciones'].value;
      this.integracion.organizacionApoyoId = this.formValue.controls['organizaciones'].value;
      this.integracion.tiposAyudaIds = this.tiposAyuda;
      await this.beneficiarioService.saveOrUpdateIntegracion(this.integracion, this.idGrupoFamiliar);
      this.location.back();
    }
  }

  createForm() {
    // this.beneficiarioForm = this.fb.group(new BeneficiariosEntityProxy());
    this.tiposAyuda = this.integracion.tiposAyudaIds;
    this.formValue = this.fb.group({
      organizaciones: [this.integracion.organizacionApoyoId, Validators.required],
      observaciones: [this.integracion.observaciones],
    })
  }

}

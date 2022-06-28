import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Location } from '@angular/common'
import { BreadcrumbService } from 'src/app/app.breadcrumb.service';
import { CatalogoProxy } from 'src/app/models/catalogo';
import { GrupoFamiliarProxy, IntegracionesSocioEconomicasEntityProxy, ReferenciasEntityProxy } from 'src/app/models/grupoFamiliar';
import { BeneficiarioService } from 'src/app/services/beneficiario.service';
import { CatalogoService } from 'src/app/services/catalogo.service';
import { UtilService } from 'src/app/services/util.service';

@Component({
  selector: 'app-remision-caso-detalle',
  templateUrl: './remision-caso-detalle.component.html',
  styleUrls: ['./remision-caso-detalle.component.scss']
})
export class RemisionCasoDetalleComponent implements OnInit {

  formValue: FormGroup;
  submitted = false;
  remision: ReferenciasEntityProxy;
  sortOrder: number;
  sortField: string;
  grupoFamiliar: GrupoFamiliarProxy;
  idGrupoFamiliar: string
  idRemisionDetalle: string
  catalogos: CatalogoProxy;
  motivos: string[];

  constructor(private breadcrumbService: BreadcrumbService, private router: Router, route: ActivatedRoute, private catalogoService: CatalogoService, private utilService: UtilService,
    private fb: FormBuilder, private beneficiarioService: BeneficiarioService, private location: Location) {
    this.catalogoService.getCatalogo().then(c => {
      this.catalogos = c;
    });

    this.idGrupoFamiliar = route.snapshot.paramMap.get("idGrupoFamiliar");
    this.idRemisionDetalle = route.snapshot.paramMap.get("idRemisionDetalle");
    this.breadcrumbService.setItems([
      { label: 'Remisión de casos ' }
    ]);
  }

  async ngOnInit() {
    await this.cargarDatos();
    this.createForm();
  }

  async cargarDatos() {
    this.remision = await this.beneficiarioService.getRemisionBygrupoFamiliarIdAndBeneficiarioId(this.idGrupoFamiliar, this.idRemisionDetalle);
    if (this.remision == null)
      this.remision = new ReferenciasEntityProxy();
  }

  nuevo() {
    this.router.navigate(['/pages/remision-caso-detalle', { idGrupoFamiliar: this.idGrupoFamiliar }]);
  }

  async save() {
    this.submitted = true;
    if(this.motivos == null || this.motivos.length <= 0){
      this.utilService.warnMessage("Ingrese al menos 1 motivo de remisión", "");
      return
    }
    if (this.remision != null && this.formValue.valid) {
      this.remision.motivosReferenciaIds = this.motivos;
      this.remision.fecha = this.formValue.controls['fecha'].value;
      this.remision.organizacionesReferenciaIds = this.formValue.controls['organizacion'].value;
      this.remision.observacion = this.formValue.controls['observacion'].value;
      await this.beneficiarioService.saveOrUpdateRemision(this.remision, this.idGrupoFamiliar);
      this.location.back();
    }
  }

  createForm() {
    this.motivos = this.remision.motivosReferenciaIds;
    this.formValue = this.fb.group({
      fecha: [this.remision.fecha, Validators.required],
      organizacion: [this.remision.organizacionesReferenciaIds, Validators.required],
      observacion: [this.remision.observacion],
    })
  }

}

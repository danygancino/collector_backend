import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { BreadcrumbService } from 'src/app/app.breadcrumb.service';
import { CatalogoProxy } from 'src/app/models/catalogo';
import { Asistencias, GrupoFamiliarProxy } from 'src/app/models/grupoFamiliar';
import { BeneficiarioService } from 'src/app/services/beneficiario.service';
import { CatalogoService } from 'src/app/services/catalogo.service';

@Component({
  selector: 'app-apoyos',
  templateUrl: './apoyos.component.html',
  styleUrls: ['./apoyos.component.scss']
})
export class ApoyosComponent implements OnInit {

  asistencias: Asistencias[] = [];
  sortOrder: number;
  sortField: string;
  grupoFamiliar: GrupoFamiliarProxy;
  idGrupoFamiliar: string  
  catalogos: CatalogoProxy;

  constructor(private breadcrumbService: BreadcrumbService, private router: Router, route: ActivatedRoute, 
    private beneficiarioService: BeneficiarioService, private catalogoService: CatalogoService) {
    this.idGrupoFamiliar = route.snapshot.paramMap.get("idGrupoFamiliar");

    this.catalogoService.getCatalogo().then(c => {
      this.catalogos = c;      
    });

    this.breadcrumbService.setItems([
      { label: 'Apoyos AVSI '}
    ]);   

    this.cargarDatos();
  }
  async cargarDatos() {
      this.grupoFamiliar = await this.beneficiarioService.getGrupoFamiliarById(this.idGrupoFamiliar);
      this.asistencias = this.grupoFamiliar.asistencias;
  }

  ngOnInit(): void {
  }

  nuevo(){
    this.router.navigate(['/pages/apoyo-detalle', { idGrupoFamiliar: this.idGrupoFamiliar}]);
  }

  cargarApoyo(asistenciasDetalleId: string){
    this.router.navigate(['/pages/apoyo-detalle', { idGrupoFamiliar: this.idGrupoFamiliar, idApoyoDetalle: asistenciasDetalleId}]);
  }

}
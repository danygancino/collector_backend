

import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { BreadcrumbService } from 'src/app/app.breadcrumb.service';
import { CatalogoProxy } from 'src/app/models/catalogo';
import { GrupoFamiliarProxy, IntegracionesSocioEconomicasEntityProxy, ReferenciasEntityProxy } from 'src/app/models/grupoFamiliar';
import { BeneficiarioService } from 'src/app/services/beneficiario.service';
import { CatalogoService } from 'src/app/services/catalogo.service';


@Component({
  selector: 'app-remision-casos',
  templateUrl: './remision-casos.component.html',
  styleUrls: ['./remision-casos.component.scss']
})
export class RemisionCasosComponent implements OnInit {  

  remisiones: ReferenciasEntityProxy[] = [];
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
      { label: 'Remisiones '}
    ]);   

    this.cargarDatos();
  }
  async cargarDatos() {
      this.grupoFamiliar = await this.beneficiarioService.getGrupoFamiliarById(this.idGrupoFamiliar);
      this.remisiones = this.grupoFamiliar.referencias;
  }

  ngOnInit(): void {
  }

  nuevo(){
    this.router.navigate(['/pages/remision-caso-detalle', { idGrupoFamiliar: this.idGrupoFamiliar}]);
  }

  cargar(remisionDetalleId: string){
    this.router.navigate(['/pages/remision-caso-detalle', { idGrupoFamiliar: this.idGrupoFamiliar, idRemisionDetalle: remisionDetalleId}]);
  }

}

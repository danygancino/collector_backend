import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { BreadcrumbService } from 'src/app/app.breadcrumb.service';
import { CatalogoProxy } from 'src/app/models/catalogo';
import { GrupoFamiliarProxy, SeguimientosEntityProxy } from 'src/app/models/grupoFamiliar';
import { BeneficiarioService } from 'src/app/services/beneficiario.service';
import { CatalogoService } from 'src/app/services/catalogo.service';

@Component({
  selector: 'app-seguimientos',
  templateUrl: './seguimientos.component.html',
  styleUrls: ['./seguimientos.component.scss']
})
export class SeguimientosComponent implements OnInit {

 

  seguimientos: SeguimientosEntityProxy[] = [];
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
      { label: 'Seguimientos del caso'}
    ]);   

    this.cargarDatos();
  }
  async cargarDatos() {
      this.grupoFamiliar = await this.beneficiarioService.getGrupoFamiliarById(this.idGrupoFamiliar);
      this.seguimientos = this.grupoFamiliar.seguimientos;
  }

  ngOnInit(): void {
  }

  nuevo(){
    this.router.navigate(['/pages/seguimiento-detalle', { idGrupoFamiliar: this.idGrupoFamiliar}]);
  }

  cargar(idSeguimiento: string){
    this.router.navigate(['/pages/seguimiento-detalle', { idGrupoFamiliar: this.idGrupoFamiliar, idSeguimiento: idSeguimiento}]);
  }

}

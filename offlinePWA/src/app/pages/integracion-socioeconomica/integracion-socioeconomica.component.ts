import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { BreadcrumbService } from 'src/app/app.breadcrumb.service';
import { CatalogoProxy } from 'src/app/models/catalogo';
import { GrupoFamiliarProxy, IntegracionesSocioEconomicasEntityProxy } from 'src/app/models/grupoFamiliar';
import { BeneficiarioService } from 'src/app/services/beneficiario.service';
import { CatalogoService } from 'src/app/services/catalogo.service';

@Component({
  selector: 'app-integracion-socioeconomica',
  templateUrl: './integracion-socioeconomica.component.html',
  styleUrls: ['./integracion-socioeconomica.component.scss']
})
export class IntegracionSocioeconomicaComponent implements OnInit {

  integracions: IntegracionesSocioEconomicasEntityProxy[] = [];
  sortOrder: number;
  sortField: string;
  grupoFamiliar: GrupoFamiliarProxy;
  idGrupoFamiliar: string  
  catalogos: CatalogoProxy;
  monto: number;

  constructor(private breadcrumbService: BreadcrumbService, private router: Router, route: ActivatedRoute, 
    private beneficiarioService: BeneficiarioService, private catalogoService: CatalogoService) {
    this.idGrupoFamiliar = route.snapshot.paramMap.get("idGrupoFamiliar");

    this.catalogoService.getCatalogo().then(c => {
      this.catalogos = c;      
    });

    this.breadcrumbService.setItems([
      { label: 'Integración socioeconómica '}
    ]);   

    this.cargarDatos();
  }
  async cargarDatos() {
      this.grupoFamiliar = await this.beneficiarioService.getGrupoFamiliarById(this.idGrupoFamiliar);
      this.monto = this.grupoFamiliar.montoIngresosMensual;
      this.integracions = this.grupoFamiliar.integracionesSocioEconomicas;
  }

  ngOnInit(): void {
  }

  nuevo(){
    this.router.navigate(['/pages/integracion-socioeconomica-detalle', { idGrupoFamiliar: this.idGrupoFamiliar}]);
  }

  cargarIntegracion(integracionDetalleId: string){
    this.router.navigate(['/pages/integracion-socioeconomica-detalle', { idGrupoFamiliar: this.idGrupoFamiliar, idIntegracionDetalle: integracionDetalleId}]);
  }
  
  saveMonto(){
    this.grupoFamiliar.montoIngresosMensual = this.monto;
    this.beneficiarioService.saveOrUpdateGrupoFamiliar(this.grupoFamiliar);
  }

}

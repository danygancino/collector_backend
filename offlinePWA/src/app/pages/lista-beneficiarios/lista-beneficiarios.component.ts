import { Component, OnInit } from '@angular/core';
import { BreadcrumbService } from 'src/app/app.breadcrumb.service';
import { EntidadService } from 'src/app/services/entity.service';
import { BeneficiariosEntityProxy, GrupoFamiliarProxy } from 'src/app/models/grupoFamiliar';
import { Entidad } from 'src/app/models/entidad';
import { CatalogoService } from 'src/app/services/catalogo.service';
import { Router } from '@angular/router';
import { BeneficiarioService } from 'src/app/services/beneficiario.service';
import { UtilService } from 'src/app/services/util.service';
import { CachePrimerService } from 'src/app/services/cache-primer.service';
import { SyncService } from 'src/app/services/sync.service';
import {CatalogoProxy} from '../../models/catalogo';



@Component({
  selector: 'app-lista-beneficiarios',
  templateUrl: './lista-beneficiarios.component.html',
  styleUrls: ['./lista-beneficiarios.component.scss']
})
export class ListaBeneficiariosComponent implements OnInit {

  beneficiarios: BeneficiariosEntityProxy[];
  sortOrder: number;
  sortField: string;
  catalogos: CatalogoProxy;
  queryFilter: string;


  constructor(private breadcrumbService: BreadcrumbService, private entidadService: EntidadService, private syncService: SyncService,
    private catalogoService: CatalogoService, private router: Router, private beneficariosService: BeneficiarioService,
    private utilService: UtilService) {

    this.breadcrumbService.setItems([
      { label: 'Búsqueda beneficiarios', routerLink: ['/pages/form'] }
    ]);
  }

  async ngOnInit(): Promise<void> {
    this.cargarDatos().then();
  }

  async cargarDatos() {
    await this.catalogoService.getCatalogo().then(c => {
      this.catalogos = c;
    });
  }

  async createGrupoFamiliar() {
    let grupoFamiliar = new GrupoFamiliarProxy
    await this.beneficariosService.saveOrUpdateGrupoFamiliar(grupoFamiliar);
    this.router.navigate(['/pages/grupoFamiliar', { idGrupoFamiliar: grupoFamiliar.id, idBeneficiario: "0" }]);
  }

  showGrupoDeFamiliar(beneficiario: BeneficiariosEntityProxy) {
    this.router.navigate(['/pages/grupoFamiliar', { idGrupoFamiliar: beneficiario.grupoFamiliarId, idBeneficiario: beneficiario.id }]);
  }

  async searchBeneficiariosByQuery() {
    if (this.queryFilter == null || this.queryFilter.length < 3) {
      this.utilService.warnMessage("Ingrese al menos 3 caracteres", "");
      return
    }
    this.beneficiarios = await this.beneficariosService.searchBeneficiariosByQuery(this.queryFilter);

    if (this.beneficiarios != null && this.beneficiarios.length == 0) {
      this.utilService.warnMessage("La búsqueda no ha entregado resultados", "");
    }
  }
}

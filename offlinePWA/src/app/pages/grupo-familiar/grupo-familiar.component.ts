import { Component, OnInit } from '@angular/core';
import { BreadcrumbService } from 'src/app/app.breadcrumb.service';
import { BeneficiariosEntityProxy, GrupoFamiliarProxy } from 'src/app/models/grupoFamiliar';
import { Router,  ActivatedRoute } from '@angular/router';


import { BeneficiarioService } from 'src/app/services/beneficiario.service';
import { MenuItem } from 'primeng/api';
import { CatalogoService } from 'src/app/services/catalogo.service';
import { UtilService } from 'src/app/services/util.service';
import { CatalogoProxy } from 'src/app/models/catalogo';


@Component({
  selector: 'app-grupo-familiar',
  templateUrl: './grupo-familiar.component.html',
  styleUrls: ['./grupo-familiar.component.scss']
})
export class GrupoFamiliarComponent implements OnInit {
  beneficiarios: BeneficiariosEntityProxy[] = [];
  catalogos: CatalogoProxy;
  sortOrder: number;
  sortField: string;
  grupoFamiliar: GrupoFamiliarProxy;
  idGrupoFamiliar: string
  items: MenuItem[];

  constructor(private breadcrumbService: BreadcrumbService, private router: Router, private utilService: UtilService, 
    route: ActivatedRoute, private beneficiarioService: BeneficiarioService,
    private catalogoService: CatalogoService,
    private beneficiariosService: BeneficiarioService) {

    this.sortField = "nombres"
    this.idGrupoFamiliar = route.snapshot.paramMap.get("idGrupoFamiliar");
    



    this.items = [
      {
        label: 'Agregar beneficiario', icon: 'pi pi-users', command: () => {
          this.newIntegrante();
        }
      },
      {
        label: 'Integración socioeconómica', icon: 'pi pi-wallet', command: () => {
          if (this.isAtleastOneBeneficiario())
            this.router.navigate(['/pages/integracion-socioeconomica', { idGrupoFamiliar: this.grupoFamiliar.id, idBeneficiario: 0 }]);
        }
      },
      {
        label: 'Necesidades específicas', icon: 'pi pi-comments', command: () => {
          if (this.isAtleastOneBeneficiario())
            this.router.navigate(['/pages/necesidades-especificas', { idGrupoFamiliar: this.grupoFamiliar.id, idBeneficiario: 0 }]);
        }
      },
      {
        label: 'Apoyos AVSI', icon: 'pi pi-slack', command: () => {
          if (this.isAtleastOneBeneficiario())
            this.router.navigate(['/pages/apoyos', { idGrupoFamiliar: this.grupoFamiliar.id, idBeneficiario: 0 }]);
        }
      },
      {
        label: 'Remisión de casos', icon: 'pi pi-arrow-circle-up', command: () => {
          if (this.isAtleastOneBeneficiario())
            this.router.navigate(['/pages/remision-casos', { idGrupoFamiliar: this.grupoFamiliar.id, idBeneficiario: 0 }]);
        }
      },
      {
        label: 'Seguimientos del caso', icon: 'pi pi-eye', command: () => {
          if (this.isAtleastOneBeneficiario())
            this.router.navigate(['/pages/seguimientos', { idGrupoFamiliar: this.grupoFamiliar.id, idBeneficiario: 0 }]);
        }
      },
    ];
  }

  isAtleastOneBeneficiario(): boolean {
    let is = this.beneficiarios.length > 0;
    if (!is) {
      this.utilService.warnMessage("Ingrese al menos un integrante antes de continuar", "");
    }
    return is;
  }

  async ngOnInit() {
    await this.cargarDatos();
    
    if (this.idGrupoFamiliar == null || this.idGrupoFamiliar == "0") {
      this.grupoFamiliar = new GrupoFamiliarProxy
      this.beneficiarioService.saveOrUpdateGrupoFamiliar(this.grupoFamiliar);
    }
    else {
      //this.grupoFamiliar = await entidadService.getById(idGrupoFamiliar).first();
      await this.cargarBeneficiariosGrupoFamiliar();
    }
    if(!this.grupoFamiliar.grupoFamiliarId) this.grupoFamiliar.grupoFamiliarId = "";
    this.breadcrumbService.setItems([
      { label: 'Grupo Familiar ' + this.grupoFamiliar.grupoFamiliarId, routerLink: ['/pages/form'] }
    ]);
    
  }

  async cargarDatos() {
       await this.catalogoService.getCatalogo().then(c => {
      this.catalogos = c;
    });
  }

  async cargarBeneficiariosGrupoFamiliar() {

    this.grupoFamiliar = await this.beneficiariosService.getGrupoFamiliarById(this.idGrupoFamiliar);
    this.beneficiarios = this.grupoFamiliar.beneficiarios;
    this.sortBeneficiarios();
  }

  sortBeneficiarios(){
    this.beneficiarios = this.beneficiarios.sort((a: any, b: any) => {
      return <any>new Date(a.fechaNacimiento) - <any>new Date(b.fechaNacimiento);
    });
  }

  newIntegrante() {
    this.router.navigate(['/pages/beneficiario', { idGrupoFamiliar: this.grupoFamiliar.id, idBeneficiario: 0 }]).then(() => {

    });;
  }

  integracionSocioEconomica() {
  }

  cargarBeneficiario(idBeneficiario: number) {

    this.router.navigate(['/pages/beneficiario', { idGrupoFamiliar: this.grupoFamiliar.id, idBeneficiario: idBeneficiario }]).then(() => {

    });
  }
}

import { Component, OnInit } from '@angular/core';
import { AppMainComponent } from './app.main.component';
import { rolesEnum } from './models/role.enum';
import { UserService } from './services/user.service';

@Component({
    selector: 'app-menu',
    templateUrl: './app.menu.component.html',
})
export class AppMenuComponent implements OnInit {

    model: any[];

    constructor(public appMain: AppMainComponent, private userService: UserService) {
    }

    ngOnInit() {
        this.model = [];
        let operadorMenuOptions = {
            label: 'Menú', icon: 'pi pi-home',
            items: [
                { label: 'Búsqueda beneficiarios', icon: 'pi pi-fw pi-home', routerLink: ['/pages/listaBeneficiarios/'] }
            ]
        }
        let administradorMenuOptions = {
            label: 'Administración', icon: 'pi pi-home',
            items: [
                {
                    label: 'Nivel de Parentesco',
                    icon: 'pi pi-fw pi-home',
                    routerLink: ['/pages/administration/catalogSimple', {
                        catalogType: 'NivelParentesco',
                        title: 'Nivel de Parentesco'
                    }]
                },
                {
                    label: 'Sexo',
                    icon: 'pi pi-fw pi-home',
                    routerLink: ['/pages/administration/catalogSimple', {
                        catalogType: 'Sexo',
                        title: 'Sexo'
                    }]
                }, {
                    label: 'Lgbti',
                    icon: 'pi pi-fw pi-home',
                    routerLink: ['/pages/administration/catalogSimple', {
                        catalogType: 'Lgbti',
                        title: 'Lgbti'
                    }]
                }, {
                    label: 'Nacionalidad',
                    icon: 'pi pi-fw pi-home',
                    routerLink: ['/pages/administration/catalogSimple', {
                        catalogType: 'Nacionalidad',
                        title: 'Nacionalidad'
                    }]
                }, {
                    label: 'Etnia',
                    icon: 'pi pi-fw pi-home',
                    routerLink: ['/pages/administration/catalogSimple', {
                        catalogType: 'Etnia',
                        title: 'Etnia'
                    }]
                }, {
                    label: 'Discapacidad',
                    icon: 'pi pi-fw pi-home',
                    routerLink: ['/pages/administration/catalogSimple', {
                        catalogType: 'Discapacidad',
                        title: 'Discapacidad'
                    }]
                }, {
                    label: 'Tipo de Documento',
                    icon: 'pi pi-fw pi-home',
                    routerLink: ['/pages/administration/catalogSimple', {
                        catalogType: 'TipoDocumento',
                        title: 'TipoDocumento'
                    }]
                }, {
                    label: 'Estatuto Migratorio',
                    icon: 'pi pi-fw pi-home',
                    routerLink: ['/pages/administration/catalogSimple', {
                        catalogType: 'EstatutoMigratorio',
                        title: 'Estatuto Migratorio'
                    }]
                }, {
                    label: 'Situacion Migratoria',
                    icon: 'pi pi-fw pi-home',
                    routerLink: ['/pages/administration/catalogSimple', {
                        catalogType: 'SituacionMigratoria',
                        title: 'Situacion Migratoria'
                    }]
                }, {
                    label: 'Motivo de Salida del Pais',
                    icon: 'pi pi-fw pi-home',
                    routerLink: ['/pages/administration/catalogSimple', {
                        catalogType: 'MotivoSalidaPais',
                        title: 'Motivo de Salida del Pais'
                    }]
                }, {
                    label: 'Nivel de Escolaridad',
                    icon: 'pi pi-fw pi-home',
                    routerLink: ['/pages/administration/catalogSimple', {
                        catalogType: 'NivelEscolaridad',
                        title: 'Nivel de Escolaridad'
                    }]
                }, {
                    label: 'País',
                    icon: 'pi pi-fw pi-home',
                    routerLink: ['/pages/administration/catalogSimple', {
                        catalogType: 'Pais',
                        title: 'País'
                    }]
                }, {
                    label: 'Organización de Apoyo',
                    icon: 'pi pi-fw pi-home',
                    routerLink: ['/pages/administration/catalogSimple', {
                        catalogType: 'OrganizacionApoyo',
                        title: 'Organización de Apoyo'
                    }]
                }, {
                    label: 'Profesión',
                    icon: 'pi pi-fw pi-home',
                    routerLink: ['/pages/administration/catalogSimple', {
                        catalogType: 'Profesion',
                        title: 'Profesión'
                    }]
                }, {
                    label: 'Oficio',
                    icon: 'pi pi-fw pi-home',
                    routerLink: ['/pages/administration/catalogSimple', {
                        catalogType: 'Oficio',
                        title: 'Oficio'
                    }]
                }, {
                    label: 'Necesidad Específica de Proteccion',
                    icon: 'pi pi-fw pi-home',
                    routerLink: ['/pages/administration/catalogSimple', {
                        catalogType: 'NecesidadEspecificaProteccion',
                        title: 'Necesidad Específica de Proteccion'
                    }]
                }, {
                    label: 'Necesidad Específica  de Habitabilidad',
                    icon: 'pi pi-fw pi-home',
                    routerLink: ['/pages/administration/catalogSimple', {
                        catalogType: 'NecesidadEspecificaHabitabilidad',
                        title: 'Necesidad Especifica de Habitabilidad'
                    }]
                }, {
                    label: 'Componente',
                    icon: 'pi pi-fw pi-home',
                    routerLink: ['/pages/administration/catalogSimple', {
                        catalogType: 'Componente',
                        title: 'Componente'
                    }]
                }, {
                    label: 'Tipo de Ayuda',
                    icon: 'pi pi-fw pi-home',
                    routerLink: ['/pages/administration/catalogSimple', {
                        catalogType: 'TipoAyuda',
                        title: 'TipoAyuda'
                    }]
                }, {
                    label: 'Motivo de Referencia',
                    icon: 'pi pi-fw pi-home',
                    routerLink: ['/pages/administration/catalogSimple', {
                        catalogType: 'MotivoReferencia',
                        title: 'Motivo de Referencia'
                    }]
                }, {
                    label: 'Organizacion Referencia',
                    icon: 'pi pi-fw pi-home',
                    routerLink: ['/pages/administration/catalogSimple', {
                        catalogType: 'OrganizacionReferencia',
                        title: 'Organizacion Referencia'
                    }]
                }, {
                    label: 'Tipo de Seguimiento',
                    icon: 'pi pi-fw pi-home',
                    routerLink: ['/pages/administration/catalogSimple', {
                        catalogType: 'TipoSeguimiento',
                        title: 'Tipo de Seguimiento'
                    }]
                }
            ]
        }
   

        if(this.userService.hasRole(rolesEnum.operador)) 
            this.model.push(operadorMenuOptions);
        if(this.userService.hasRole(rolesEnum.administrador)) 
            this.model.push(administradorMenuOptions);
            
        // {separator: true},

        // {
        //     label: 'Monitoreo', icon: 'pi pi-fw pi-compass', routerLink: ['utilities'],
        //     items: [
        //         {
        //             label: 'Reportes', icon: 'pi pi-fw pi-align-left',
        //             items: [
        //                 {
        //                     label: 'Entregas - Apoyos', icon: 'pi pi-fw pi-align-left',
        //                     items: [
        //                         {label: 'Reporte 1', icon: 'pi pi-fw pi-align-left'},
        //                         {label: 'Reporte 2', icon: 'pi pi-fw pi-align-left'},
        //                         {label: 'Reporte 3', icon: 'pi pi-fw pi-align-left'},
        //                     ]
        //                 },
        //                 {
        //                     label: 'Referencias', icon: 'pi pi-fw pi-align-left',
        //                     items: [
        //                         {label: 'Reporte 1', icon: 'pi pi-fw pi-align-left'}
        //                     ]
        //                 },
        //             ]
        //         },                   
        //     ]
        // },
        // {separator: true},
        // {
        //     label: 'Administración', icon: 'pi pi-fw pi-align-left',
        //     items: [                    
        //         {
        //             label: 'Catálogos', icon: 'pi pi-fw pi-align-left',
        //             items: [                            
        //                 {label: 'Tipos', icon: 'pi pi-fw pi-pencil', routerLink: ['/pages/crud']},
        //                 {label: 'Paises', icon: 'pi pi-fw pi-pencil', routerLink: ['/pages/crud']},
        //                 {label: 'Proyectos', icon: 'pi pi-fw pi-pencil', routerLink: ['/pages/crud']},

        //             ]
        //         }
        //     ]
        // },          


    }
}

import {RouterModule} from '@angular/router';
import {NgModule} from '@angular/core';

import {AppMainComponent} from './app.main.component';
import {AppNotfoundComponent} from './pages/app.notfound.component';
import {AppErrorComponent} from './pages/app.error.component';
import {AppAccessdeniedComponent} from './pages/app.accessdenied.component';
import {AppLoginComponent} from './pages/login/login.component';



import { FormComponent } from './pages/form/form.component';
import { ListaBeneficiariosComponent } from './pages/lista-beneficiarios/lista-beneficiarios.component';
import { GrupoFamiliarComponent } from './pages/grupo-familiar/grupo-familiar.component';
import { BeneficiarioComponent } from './pages/beneficiario/beneficiario.component';
import { IntegracionSocioeconomicaComponent } from './pages/integracion-socioeconomica/integracion-socioeconomica.component';
import { NecesidadesEspecificasComponent } from './pages/necesidades-especificas/necesidades-especificas.component';
import { ApoyosComponent } from './pages/apoyos/apoyos.component';
import { RemisionCasosComponent } from './pages/remision-casos/remision-casos.component';
import { SeguimientosComponent } from './pages/seguimientos/seguimientos.component';
import { IntegracionSocioeconomicaDetalleComponent } from './pages/integracion-socioeconomica-detalle/integracion-socioeconomica-detalle.component';
import { ApoyoDetalleComponent } from './pages/apoyo-detalle/apoyo-detalle.component';
import { SeguimientoDetalleComponent } from './pages/seguimiento-detalle/seguimiento-detalle.component';
import { RemisionCasoDetalleComponent } from './pages/remision-caso-detalle/remision-caso-detalle.component';
import {NivelParentescoComponent} from './pages/administration/nivel-parentesco/nivel-parentesco.component';
import { ReporteBeneficiariosComponent } from './pages/reporte-beneficiarios/reporte-beneficiarios.component';

@NgModule({
    imports: [
        RouterModule.forRoot([
            {                           
                
                path: 'pages', component: AppMainComponent,
                children: [
                    {path: 'form', component: FormComponent},
                    {path: 'listaBeneficiarios', component: ListaBeneficiariosComponent},
                    {path: 'grupoFamiliar', component: GrupoFamiliarComponent},
                    {path: 'beneficiario', component: BeneficiarioComponent},
                    {path: 'integracion-socioeconomica', component: IntegracionSocioeconomicaComponent},
                    {path: 'integracion-socioeconomica-detalle', component: IntegracionSocioeconomicaDetalleComponent},
                    {path: 'necesidades-especificas', component: NecesidadesEspecificasComponent},
                    {path: 'apoyos', component: ApoyosComponent},
                    {path: 'apoyo-detalle', component: ApoyoDetalleComponent},
                    {path: 'remision-casos', component: RemisionCasosComponent},
                    {path: 'remision-caso-detalle', component: RemisionCasoDetalleComponent},
                    {path: 'seguimientos', component: SeguimientosComponent},
                    {path: 'seguimiento-detalle', component: SeguimientoDetalleComponent},
                    {path: 'administration/catalogSimple', component: NivelParentescoComponent},
                    {path: 'administration/reporte', component: ReporteBeneficiariosComponent},


                    
                
                ]
            },
            {path: '', component: AppLoginComponent},
            {path: 'login', component: AppLoginComponent},
            {path: 'error', component: AppErrorComponent},
            {path: 'access', component: AppAccessdeniedComponent},
            {path: 'notfound', component: AppNotfoundComponent},            
            {path: '**', redirectTo: '/notfound'},
        ], {scrollPositionRestoration: 'enabled'})
    ],
    exports: [RouterModule]
})
export class AppRoutingModule {
}

import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {HttpClientModule, HTTP_INTERCEPTORS} from '@angular/common/http';
import {BrowserModule} from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {HashLocationStrategy, LocationStrategy} from '@angular/common';
import {AppRoutingModule} from './app-routing.module';

import {AccordionModule} from 'primeng/accordion';
import {AutoCompleteModule} from 'primeng/autocomplete';
import {AvatarModule} from 'primeng/avatar';
import {AvatarGroupModule} from 'primeng/avatargroup';
import {BadgeModule} from 'primeng/badge';
import {BreadcrumbModule} from 'primeng/breadcrumb';
import {ButtonModule} from 'primeng/button';
import {CalendarModule} from 'primeng/calendar';
import {CardModule} from 'primeng/card';
import {CarouselModule} from 'primeng/carousel';
import {CascadeSelectModule} from 'primeng/cascadeselect';
import {ChartModule} from 'primeng/chart';
import {CheckboxModule} from 'primeng/checkbox';
import {ChipModule} from 'primeng/chip';
import {ChipsModule} from 'primeng/chips';
import {CodeHighlighterModule} from 'primeng/codehighlighter';
import {ConfirmDialogModule} from 'primeng/confirmdialog';
import {ConfirmPopupModule} from 'primeng/confirmpopup';
import {ColorPickerModule} from 'primeng/colorpicker';
import {ContextMenuModule} from 'primeng/contextmenu';
import {DataViewModule} from 'primeng/dataview';
import {DialogModule} from 'primeng/dialog';
import {DividerModule} from 'primeng/divider';
import {DropdownModule} from 'primeng/dropdown';
import {FieldsetModule} from 'primeng/fieldset';
import {FileUploadModule} from 'primeng/fileupload';
import {FullCalendarModule} from 'primeng/fullcalendar';
import {GalleriaModule} from 'primeng/galleria';
import {InplaceModule} from 'primeng/inplace';
import {InputNumberModule} from 'primeng/inputnumber';
import {InputMaskModule} from 'primeng/inputmask';
import {InputSwitchModule} from 'primeng/inputswitch';
import {InputTextModule} from 'primeng/inputtext';
import {InputTextareaModule} from 'primeng/inputtextarea';
import {KnobModule} from 'primeng/knob';
import {LightboxModule} from 'primeng/lightbox';
import {ListboxModule} from 'primeng/listbox';
import {MegaMenuModule} from 'primeng/megamenu';
import {MenuModule} from 'primeng/menu';
import {MenubarModule} from 'primeng/menubar';
import {MessagesModule} from 'primeng/messages';
import {MessageModule} from 'primeng/message';
import {MultiSelectModule} from 'primeng/multiselect';
import {OrderListModule} from 'primeng/orderlist';
import {OrganizationChartModule} from 'primeng/organizationchart';
import {OverlayPanelModule} from 'primeng/overlaypanel';
import {PaginatorModule} from 'primeng/paginator';
import {PanelModule} from 'primeng/panel';
import {PanelMenuModule} from 'primeng/panelmenu';
import {PasswordModule} from 'primeng/password';
import {PickListModule} from 'primeng/picklist';
import {ProgressBarModule} from 'primeng/progressbar';
import {RadioButtonModule} from 'primeng/radiobutton';
import {RatingModule} from 'primeng/rating';
import {RippleModule} from 'primeng/ripple';
import {ScrollPanelModule} from 'primeng/scrollpanel';
import {ScrollTopModule} from 'primeng/scrolltop';
import {SelectButtonModule} from 'primeng/selectbutton';
import {SidebarModule} from 'primeng/sidebar';
import {SkeletonModule} from 'primeng/skeleton';
import {SlideMenuModule} from 'primeng/slidemenu';
import {SliderModule} from 'primeng/slider';
import {SplitButtonModule} from 'primeng/splitbutton';
import {SplitterModule} from 'primeng/splitter';
import {StepsModule} from 'primeng/steps';
import {TabMenuModule} from 'primeng/tabmenu';
import {TableModule} from 'primeng/table';
import {TabViewModule} from 'primeng/tabview';
import {TagModule} from 'primeng/tag';
import {TerminalModule} from 'primeng/terminal';
import {TieredMenuModule} from 'primeng/tieredmenu';
import {TimelineModule} from 'primeng/timeline';
import {ToastModule} from 'primeng/toast';
import {ToggleButtonModule} from 'primeng/togglebutton';
import {ToolbarModule} from 'primeng/toolbar';
import {TooltipModule} from 'primeng/tooltip';
import {TreeModule} from 'primeng/tree';
import {TreeTableModule} from 'primeng/treetable';
import {VirtualScrollerModule} from 'primeng/virtualscroller';

import {AppCodeModule} from './app.code.component';
import {AppComponent} from './app.component';
import {AppMainComponent} from './app.main.component';
import {AppRightmenuComponent} from './app.rightmenu.component';
import {AppMenuComponent} from './app.menu.component';
import {AppMenuitemComponent} from './app.menuitem.component';
import {AppTopBarComponent} from './app.topbar.component';
import {AppSearchComponent} from './app.search.component';
import {AppFooterComponent} from './app.footer.component';


import {AppNotfoundComponent} from './pages/app.notfound.component';
import {AppErrorComponent} from './pages/app.error.component';
import {AppAccessdeniedComponent} from './pages/app.accessdenied.component';
import {BreadcrumbService} from './app.breadcrumb.service';
import {MenuService} from './app.menu.service';
import {ServiceWorkerModule} from '@angular/service-worker';
import {environment} from '../environments/environment';
import {FormComponent} from './pages/form/form.component';
import {AppLoginComponent} from './pages/login/login.component';
import {ListaBeneficiariosComponent} from './pages/lista-beneficiarios/lista-beneficiarios.component';
import {GrupoFamiliarComponent} from './pages/grupo-familiar/grupo-familiar.component';
import {BeneficiarioComponent} from './pages/beneficiario/beneficiario.component';
import {ReactiveFormsModule} from '@angular/forms';
import {DatabaseService} from './services/databaseservice';
import {EntidadService} from './services/entity.service';
import {CatalogoService} from './services/catalogo.service';
import {BeneficiarioService} from './services/beneficiario.service';
import {UtilService} from './services/util.service';
import {ConfirmationService, MessageService} from 'primeng/api';
import {IntegracionSocioeconomicaComponent} from './pages/integracion-socioeconomica/integracion-socioeconomica.component';
import {NecesidadesEspecificasComponent} from './pages/necesidades-especificas/necesidades-especificas.component';
import {ApoyosComponent} from './pages/apoyos/apoyos.component';
import {RemisionCasosComponent} from './pages/remision-casos/remision-casos.component';
import {SeguimientosComponent} from './pages/seguimientos/seguimientos.component';
import {IntegracionSocioeconomicaDetalleComponent} from './pages/integracion-socioeconomica-detalle/integracion-socioeconomica-detalle.component';
import {ApoyoDetalleComponent} from './pages/apoyo-detalle/apoyo-detalle.component';
import {RemisionCasoDetalleComponent} from './pages/remision-caso-detalle/remision-caso-detalle.component';
import {SeguimientoDetalleComponent} from './pages/seguimiento-detalle/seguimiento-detalle.component';
import {LoaderInterceptor} from './interceptors/loader.interceptor';
import {LoaderComponent} from './components/loader/loader.component';
import {UserService} from './services/user.service';
import {TokenResponseInterceptor} from './interceptors/token-response.interceptor';
import {TokenRequestInterceptor} from './interceptors/token-request.interceptor';
import {AppCodeInterceptor} from './interceptors/app-code.interceptor';
import {NivelParentescoComponent} from './pages/administration/nivel-parentesco/nivel-parentesco.component';
import { StatePipe } from './pipes/state.pipe';
import { NgHttpLoaderModule } from 'ng-http-loader';
import { ReporteBeneficiariosComponent } from './pages/reporte-beneficiarios/reporte-beneficiarios.component';



@NgModule({
    imports: [
        BrowserModule,
        FormsModule,
        AppRoutingModule,
        HttpClientModule,
        BrowserAnimationsModule,
        AccordionModule,
        AutoCompleteModule,
        AvatarModule,
        AvatarGroupModule,
        BadgeModule,
        BreadcrumbModule,
        ButtonModule,
        CalendarModule,
        CardModule,
        CarouselModule,
        CascadeSelectModule,
        ChartModule,
        CheckboxModule,
        ChipModule,
        ChipsModule,
        CodeHighlighterModule,
        ConfirmDialogModule,
        ConfirmPopupModule,
        ColorPickerModule,
        ContextMenuModule,
        DataViewModule,
        DialogModule,
        DividerModule,
        DropdownModule,
        FieldsetModule,
        FileUploadModule,
        FullCalendarModule,
        GalleriaModule,
        InplaceModule,
        InputNumberModule,
        InputMaskModule,
        InputSwitchModule,
        InputTextModule,
        InputTextareaModule,
        KnobModule,
        LightboxModule,
        ListboxModule,
        MegaMenuModule,
        MenuModule,
        MenubarModule,
        MessageModule,
        MessagesModule,
        MultiSelectModule,
        OrderListModule,
        OrganizationChartModule,
        OverlayPanelModule,
        PaginatorModule,
        PanelModule,
        PanelMenuModule,
        PasswordModule,
        PickListModule,
        ProgressBarModule,
        RadioButtonModule,
        RatingModule,
        RippleModule,
        ScrollPanelModule,
        ScrollTopModule,
        SelectButtonModule,
        SidebarModule,
        SkeletonModule,
        SlideMenuModule,
        SliderModule,
        SplitButtonModule,
        SplitterModule,
        StepsModule,
        TableModule,
        TabMenuModule,
        TabViewModule,
        TagModule,
        TerminalModule,
        TimelineModule,
        PasswordModule,
        TieredMenuModule,
        ToastModule,
        ToggleButtonModule,
        ToolbarModule,
        TooltipModule,
        TreeModule,
        TreeTableModule,
        VirtualScrollerModule,
        AppCodeModule,
        ReactiveFormsModule,
        ServiceWorkerModule.register('ngsw-worker.js', {
            enabled: environment.production,
            // Register the ServiceWorker as soon as the app is stable
            // or after 30 seconds (whichever comes first).
            registrationStrategy: 'registerWhenStable:30000'
        }),
        NgHttpLoaderModule.forRoot(), // <============ Don't forget to call 'forRoot()'!

    ],
    declarations: [
        AppComponent,
        AppMainComponent,
        AppRightmenuComponent,
        AppMenuComponent,
        AppMenuitemComponent,
        AppTopBarComponent,
        AppSearchComponent,
        AppFooterComponent,
        AppNotfoundComponent,
        AppErrorComponent,
        AppAccessdeniedComponent,
        FormComponent,
        AppLoginComponent,
        ListaBeneficiariosComponent,
        GrupoFamiliarComponent,
        BeneficiarioComponent,
        IntegracionSocioeconomicaComponent,
        NecesidadesEspecificasComponent,
        ApoyosComponent,
        RemisionCasosComponent,
        SeguimientosComponent,
        IntegracionSocioeconomicaDetalleComponent,
        ApoyoDetalleComponent,
        RemisionCasoDetalleComponent,
        SeguimientoDetalleComponent,
        LoaderComponent,
        NivelParentescoComponent,
        StatePipe,
        ReporteBeneficiariosComponent
    ],
    providers: [

        {
            provide: HTTP_INTERCEPTORS,
            useClass: LoaderInterceptor,
            multi: true
        },
        {
            provide: HTTP_INTERCEPTORS,
            useClass: TokenResponseInterceptor,
            multi: true
        },
        {
            provide: HTTP_INTERCEPTORS,
            useClass: TokenRequestInterceptor,
            multi: true
        },
        {
            provide: HTTP_INTERCEPTORS,
            useClass: AppCodeInterceptor,
            multi: true
        },
        {provide: LocationStrategy, useClass: HashLocationStrategy},
        MenuService, ConfirmationService, UserService, BreadcrumbService, DatabaseService, EntidadService, CatalogoService, BeneficiarioService, UtilService, MessageService, StatePipe
    ],
    bootstrap: [AppComponent]
})
export class AppModule {
}

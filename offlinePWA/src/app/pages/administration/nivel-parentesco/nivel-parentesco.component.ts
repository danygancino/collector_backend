import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {AdministrationService} from '../../../services/administration.service';
import {FilterService, MessageService, SelectItem} from 'primeng/api';
import {Catalogo} from '../../../models/catalogo';
import {StatePipe} from '../../../pipes/state.pipe';
import {UtilService} from '../../../services/util.service';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
    selector: 'app-nivel-parentesco',
    templateUrl: './nivel-parentesco.component.html',
    styleUrls: ['./nivel-parentesco.component.scss']
})
export class NivelParentescoComponent implements OnInit {
    catalogForm: FormGroup;
    submitted = false;
    states: SelectItem[];
    items: Catalogo[];
    cols: any[];
    showDialog: boolean;
    // tslint:disable-next-line:no-inferrable-types
    title: string;
    // tslint:disable-next-line:no-inferrable-types
    catalogType: string;


    constructor(private fb: FormBuilder,
                private  administrationService: AdministrationService,
                private statePipe: StatePipe,
                public utils: UtilService,
                private messageService: MessageService,
                private filterService: FilterService,
                private route: ActivatedRoute,
                private router: Router
    ) {
    }

    ngOnInit(): void {
        this.route.params.subscribe(
            params => {
                this.title = params.title;
                this.catalogType = params.catalogType;
                this.reloadAll();
            }
        );

    }

    reloadAll() {
        /*this.title = this.route.snapshot.paramMap.get('title');
        this.catalogType = this.route.snapshot.paramMap.get('catalogType');*/
        this.createForm();
        this.loadOptions();
        this.loadItems();
        this.cols = [
            {field: 'id', header: 'Id', styleClass: `text-right`},
            {field: 'nombre', header: 'Nombre'},
            {field: 'state', header: 'Estado', type: this.statePipe}

        ];
        this.registerFilters();
    }

    createForm() {

        this.catalogForm = this.fb.group({
            id: [''],
            nombre: ['', Validators.required],
            state: ['', Validators.required]
        });

    }

    private loadOptions() {
        this.states = this.administrationService.getStates();
    }

    private loadItems() {
        this.administrationService.getAllCatalogByType(this.catalogType)
            .subscribe(value => {
                this.items = value;
                this.items.sort((a, b) => {
                    return a.nombre > b.nombre ? 1 : -1;
                });
            }, error => {
                this.messageService.add({severity: 'error', summary: 'Error recuperar los catalogos', detail: error.error.message});
            });
    }

    private registerFilters() {
        this.filterService.register('stateFilter', (value, filter): boolean => {
            return this.utils.stateFilter(value, filter);
        });


    }

    edit(catalogo: Catalogo) {
        console.log(catalogo);
        this.catalogForm.reset();
        this.catalogForm.markAsPristine();
        this.catalogForm.markAsUntouched();
        this.catalogForm.patchValue(catalogo);
        this.messageService.clear();
        this.showDialog = true;
    }

    create() {
        console.log('create catalog');
        this.catalogForm.reset();
        this.catalogForm.markAsPristine();
        this.catalogForm.markAsUntouched();
        this.catalogForm.patchValue({
            id: null,
            nombre: null,
            state: 'ACTIVE'
        });
        this.messageService.clear();
        this.showDialog = true;
    }

    closeDialog() {
        console.log(this.catalogForm);
        this.showDialog = false;
    }


    save() {
        console.log(this.catalogForm);
        const {
            id,
            nombre,
            state
        }
            = this.catalogForm.value;

        const catalogo: Catalogo = new Catalogo(id, nombre, state);

        if (catalogo.id) {
            // update
            this.administrationService.updateByCatalogByType(this.catalogType, catalogo).subscribe(value => {
                this.showDialog = false;
                this.messageService.add({severity: 'success', summary: 'Guardado', detail: 'Se ha guardado exitosamente'});
                this.loadItems();
            }, error => {
                this.messageService.add({
                    severity: 'error',
                    summary: 'Error al guardar',
                    detail: error.error.message ? error.error.message : 'ya existe un catálogo con este nombre'
                });
            });
        } else {
            // create
            this.administrationService.saveByCatalogByType(this.catalogType, catalogo).subscribe(value => {
                this.showDialog = false;
                this.messageService.add({severity: 'success', summary: 'Guardado', detail: 'Se ha guardado exitosamente'});
                this.loadItems();
            }, error => {
                this.messageService.add({
                    severity: 'error',
                    summary: 'Error al guardar: ',
                    detail: error.error.message
                });
            });
        }

    }
}

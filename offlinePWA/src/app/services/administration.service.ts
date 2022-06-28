import {Injectable} from '@angular/core';
import {environment} from '../../environments/environment';
import {Catalogo} from '../models/catalogo';
import {HttpClient} from '@angular/common/http';

const mainServiceUrl = environment.base_url + 'administration';

@Injectable({
    providedIn: 'root'
})
export class AdministrationService {

    constructor(private http: HttpClient) {
    }

    private states = [{label: 'ACTIVO', value: 'ACTIVE'}, {label: 'INACTIVO', value: 'INACTIVE'}];

    public getStates() {
        return this.states;
    }

    public getAllCatalogByType(catalogType: string) {
        return this.http.get<Catalogo[]>(`${mainServiceUrl}/catalog/${catalogType}`);
    }

    public saveByCatalogByType(catalogType: string, catalog: Catalogo) {
        return this.http.post(`${mainServiceUrl}/catalog/${catalogType}`, catalog);
    }

    public updateByCatalogByType(catalogType: string, catalog: Catalogo) {
        return this.http.put(`${mainServiceUrl}/catalog/${catalogType}`, catalog);
    }


}

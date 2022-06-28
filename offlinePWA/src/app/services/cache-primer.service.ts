import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { tap } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { apiConection } from '../models/apiConection.enum';
import { CatalogoProxy } from '../models/catalogo';
import { CatalogoService } from './catalogo.service';


const base_url = environment.base_url;

@Injectable({providedIn: 'root'})
export class CachePrimerService {
  

  constructor(private http: HttpClient, private catalogoService: CatalogoService) {
    this.primeCache();  
  }

  primeCache(): void {
    this.llenarCatalogos();
    this.llenarEntidades();    
  }

  llenarCatalogos() {   
    this.catalogoService.fillCatalogo();    
  }

  llenarEntidades() {
    let url = base_url + apiConection.getEntidades;
    return this.http.get(url);
  }
}
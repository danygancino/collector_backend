import { HttpClient, HttpErrorResponse, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { concat, EMPTY, Observable, throwError, TimeoutError } from 'rxjs';
import { catchError, map, retry, share, timeout } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { apiConection } from '../models/apiConection.enum';
import { Entidad } from '../models/entidad';
import { BeneficiariosEntityProxy, GrupoFamiliarProxy } from '../models/grupoFamiliar';
import { SyncTask } from '../models/syncTask';
import { TipoEntidad } from '../models/tipoEntidad.enum';
import { CatalogoService } from './catalogo.service';
import { EntidadService } from './entity.service';
import { UtilService } from './util.service';

const base_url = environment.base_url;
const HTTP_TIMEOUT_IN_MS = 20000;
@Injectable({ providedIn: 'root' })
export class SyncService {

  constructor(private http: HttpClient, private entidadService: EntidadService, private catalogoService: CatalogoService, private utilService: UtilService) { }

  async sync() {
    let dirtyEntities: Entidad[] = await this.entidadService.getAllDirty() as Entidad[];
    let dirtyEntitiesToSubmit: Entidad[] = [];
    dirtyEntities = dirtyEntities.filter(de => de.tipoEntidad === TipoEntidad.grupoFamiliar);
    dirtyEntities.forEach(de => {
      if (de.entidad["beneficiarios"].length > 0)
        dirtyEntitiesToSubmit.push(de);
    })

    let response = await this.http
      .post(base_url + apiConection.postEntidades, dirtyEntitiesToSubmit.map(d => d.entidad))
      .pipe(
        timeout(HTTP_TIMEOUT_IN_MS),
        retry(1),
        catchError((err: HttpErrorResponse) => this.handleError(err)),
        share()
      ).subscribe(async a => {

        let lastSyncDate = localStorage.getItem("lastSyncDate");
        let url = base_url + apiConection.getEntidades;
        let grupoFamiliaresCount = (await this.entidadService.getAll()).length - dirtyEntities.length;
        if (grupoFamiliaresCount > 0)
          url += lastSyncDate ? `\/${lastSyncDate}` : "";
        this.http.get(url).subscribe(response => {
          let entidades = response["gruposFamiliares"].map(gf => new Entidad(gf.id, TipoEntidad.grupoFamiliar, gf, false));
          let horaSync = response["queryTime"];
          localStorage.setItem("lastSyncDate", horaSync);
          if (entidades != null && entidades.length > 0) {
            this.parseDateOnGrupoFamiliar(entidades);
            entidades.forEach(d => this.entidadService.remove(d.id));
            this.entidadService.saveAll(entidades).then(r => {
              this.utilService.successMessage("Actualización correcta", `Datos descargados: ${entidades.length}`);
              setTimeout(function () {
                location.reload();
              }, 3000);
            })
          }
          this.utilService.successMessage("Sincronizado correctamente", `Datos subidos: ${localStorage.getItem("dirtyCountData")}`)
          localStorage.setItem("dirtyCountData", "0");
          localStorage.setItem("lastSyncDate", horaSync);
        });
      });
    if (response != null) {  //TODO ver si responde que ingreso
    //  this.catalogoService.fillCatalogo();
    }

  }

  
  //Elimina toda la base y obtiene nuevos
  async cleanEntitiesData() {

  }

  parseDateOnGrupoFamiliar(entidades: Entidad[]) {
    entidades.forEach(e => {
      let gf = <GrupoFamiliarProxy>e.entidad;
      gf.ultimaFechaActualizacion = this.utilService.getDateFromString(gf.ultimaFechaActualizacion.toString());
      gf.beneficiarios.forEach(b => { 
        if (b.fechaNacimiento != null)
          b.fechaNacimiento = this.utilService.getDateFromString(b.fechaNacimiento.toString());
        if (b.fechaIngresoPais != null)
          b.fechaIngresoPais = this.utilService.getDateFromString(b.fechaIngresoPais.toString());
        if (b.ultimaFechaActualizacion != null)
          b.ultimaFechaActualizacion = this.utilService.getDateFromString(b.ultimaFechaActualizacion.toString());
      })
      gf.asistencias.forEach(a => {
        if (a.fecha != null)
          a.fecha = this.utilService.getDateFromString(a.fecha.toString());
        if (a.ultimaFechaActualizacion != null)
          a.ultimaFechaActualizacion = this.utilService.getDateFromString(a.ultimaFechaActualizacion.toString());
      })
      gf.referencias.forEach(r => {
        if (r.ultimaFechaActualizacion != null)
          r.fecha = this.utilService.getDateFromString(r.fecha.toString());
        if (r.ultimaFechaActualizacion != null)
          r.ultimaFechaActualizacion = this.utilService.getDateFromString(r.ultimaFechaActualizacion.toString());
      })
      gf.seguimientos.forEach(s => {
        if (s.ultimaFechaActualizacion != null)
          s.fecha = this.utilService.getDateFromString(s.fecha.toString());
        if (s.ultimaFechaActualizacion != null)
          s.ultimaFechaActualizacion = this.utilService.getDateFromString(s.ultimaFechaActualizacion.toString());
      })
    })
  }

  private handleError<T>(
    err: HttpErrorResponse
  ): Observable<any> {
    if (this.offlineOrBadConnection(err)) {
      // A client-side or network error occurred. Handle it accordingly.
      this.utilService.warnMessage("Error de conexión", "No hemos podido acceder a los servidores, inténtelo más tarde")
      return EMPTY;
    } else {
      //console.log('A backend error occurred.', err);
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong.
      if (err.status == 400 || err.status == 0) {
        this.utilService.warnMessage("Error", "La sincronización falló, contáctese con el administrador")
      }
      else if (err.status == 401) {
        this.utilService.warnMessage("Error de autenticación", "Cierre su sessión, vuelva a entrar e inténtelo nuevamente")
      } else {
        this.utilService.warnMessage("Error de desconocido", "Informe a administrador")

      }
      return throwError(err);
    }
  }

  private offlineOrBadConnection(err: HttpErrorResponse): boolean {
    return (
      err instanceof TimeoutError ||
      err.error instanceof ErrorEvent ||
      !this.connectionService()
    );
  }

  public connectionService() {
    return window.navigator.onLine
  }
}

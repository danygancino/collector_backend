import { Injectable } from '@angular/core';
import { Entidad } from '../models/entidad';
import { Asistencias, BeneficiariosEntityProxy, GrupoFamiliarProxy, IntegracionesSocioEconomicasEntityProxy, NecesidadesEspecificasEntityProxy, ReferenciasEntityProxy, SeguimientosEntityProxy } from '../models/grupoFamiliar';
import { TipoEntidad } from '../models/tipoEntidad.enum';
import { IntegracionSocioeconomicaComponent } from '../pages/integracion-socioeconomica/integracion-socioeconomica.component';
import { EntidadService } from './entity.service';
import { UtilService } from './util.service';


@Injectable()
export class BeneficiarioService {

  constructor(private entidadSerice: EntidadService, private utilService: UtilService) {
  }

  async searchBeneficiariosByQuery(query: string): Promise<BeneficiariosEntityProxy[]> {
    let beneficiariosList = await this.getAllBeneficiarios();
    query = query.toLowerCase();
    return beneficiariosList.filter(ben => ben.nombres.toLowerCase().indexOf(query) !== -1 || ben.numeroDocumento.toLowerCase().indexOf(query) !== -1)
  }

  async getAllBeneficiarios(): Promise<BeneficiariosEntityProxy[]> {

    let grupoFamniliarList = await this.getAllGrupoFamiliar();
    let beneficiariosList: BeneficiariosEntityProxy[] = [];
    grupoFamniliarList.forEach(gf => {
      beneficiariosList = beneficiariosList.concat(gf.beneficiarios)
    });

    return beneficiariosList;
  }

  async getBeneficiariosBygrupoFamiliarIdAndBeneficiarioId(grupoFamiliarId: string, beneficiarioId: string): Promise<BeneficiariosEntityProxy> {
    let grupoFamliar = await this.getGrupoFamiliarById(grupoFamiliarId);
    if(grupoFamliar===undefined) return null;
    return grupoFamliar.beneficiarios.filter(b => b.id === beneficiarioId).shift();    
  }


  async getAsistenciaBygrupoFamiliarIdAndBeneficiarioId(grupoFamiliarId: string, asistenciaId: string): Promise<Asistencias> {
    let grupoFamliar = await this.getGrupoFamiliarById(grupoFamiliarId);
    if(grupoFamliar===undefined) return null;
    return grupoFamliar.asistencias.filter(a => a.id === asistenciaId).shift();    
  }

  async getIntegracionBygrupoFamiliarIdAndBeneficiarioId(grupoFamiliarId: string, integracionId: string): Promise<IntegracionesSocioEconomicasEntityProxy> {
    let grupoFamliar = await this.getGrupoFamiliarById(grupoFamiliarId);
    if(grupoFamliar===undefined) return null;
    return grupoFamliar.integracionesSocioEconomicas.filter(b => b.id === integracionId).shift();    
  }

  async getSeguimientoBygrupoFamiliarIdAndBeneficiarioId(grupoFamiliarId: string, seguimientoId: string): Promise<SeguimientosEntityProxy> {
    let grupoFamliar = await this.getGrupoFamiliarById(grupoFamiliarId);
    if(grupoFamliar===undefined) return null;
    return grupoFamliar.seguimientos.filter(b => b.id === seguimientoId).shift();    
  }



  async getRemisionBygrupoFamiliarIdAndBeneficiarioId(grupoFamiliarId: string, remisionId: string): Promise<ReferenciasEntityProxy> {
    let grupoFamliar = await this.getGrupoFamiliarById(grupoFamiliarId);
    if(grupoFamliar===undefined) return null;
    return grupoFamliar.referencias.filter(b => b.id === remisionId).shift();    
  }


  async getGrupoFamiliarById(grupoFamiliarId: string): Promise<GrupoFamiliarProxy> {
    let entidad = await this.entidadSerice.getById(grupoFamiliarId).first();
    if (entidad != null)
      return entidad.entidad as GrupoFamiliarProxy;
    return null;
  }



  async getAllGrupoFamiliar(): Promise<GrupoFamiliarProxy[]> {
    let EntidadList: Entidad[] = [];
    let grupoFamiliarList: GrupoFamiliarProxy[] = [];
    EntidadList = await this.entidadSerice.getAll() as Entidad[];

    EntidadList.forEach(entidad => {
      grupoFamiliarList = grupoFamiliarList.concat(entidad.entidad as GrupoFamiliarProxy);
    });

    return grupoFamiliarList;
  }

  async saveOrUpdateBeneficiario(beneficiario: BeneficiariosEntityProxy) {
    if (beneficiario != null && beneficiario.grupoFamiliarId != null && beneficiario.grupoFamiliarId != "") {
      let grupoFamiliar = await this.getGrupoFamiliarById(beneficiario.grupoFamiliarId);      
      beneficiario.grupoFamiliarCode = grupoFamiliar.grupoFamiliarId;
      const removeIndex  = grupoFamiliar.beneficiarios.findIndex(b => b.id == beneficiario.id);
      if(removeIndex >= 0){
        grupoFamiliar.beneficiarios.splice( removeIndex, 1 );        
      } 
      beneficiario.ultimaFechaActualizacion = new Date;
      grupoFamiliar.beneficiarios.push(beneficiario);
      await this.saveOrUpdateGrupoFamiliar(grupoFamiliar);
    }
  }

  async saveOrUpdateIntegracion(integracion: IntegracionesSocioEconomicasEntityProxy, grupoFamiliarId: string) {
    if (integracion != null && grupoFamiliarId != null && grupoFamiliarId != "") {
      let grupoFamiliar = await this.getGrupoFamiliarById(grupoFamiliarId);      
      const removeIndex  = grupoFamiliar.integracionesSocioEconomicas.findIndex(b => b.id == integracion.id);
      if(removeIndex >= 0){
        grupoFamiliar.integracionesSocioEconomicas.splice( removeIndex, 1 );        
      } 
      integracion.ultimaFechaActualizacion = new Date;
      grupoFamiliar.integracionesSocioEconomicas.push(integracion);
      await this.saveOrUpdateGrupoFamiliar(grupoFamiliar);
    }
  }

  async saveOrUpdateAsistencia(asistencia: Asistencias, grupoFamiliarId: string) {
    if (asistencia != null && grupoFamiliarId != null && grupoFamiliarId != "") {
      let grupoFamiliar = await this.getGrupoFamiliarById(grupoFamiliarId);      
      const removeIndex  = grupoFamiliar.asistencias.findIndex(b => b.id == asistencia.id);
      if(removeIndex >= 0){
        grupoFamiliar.asistencias.splice( removeIndex, 1 );        
      } 
      asistencia.ultimaFechaActualizacion = new Date;
      grupoFamiliar.asistencias.push(asistencia);
      await this.saveOrUpdateGrupoFamiliar(grupoFamiliar);
    }
  }


  async saveOrUpdateRemision(remision: ReferenciasEntityProxy, grupoFamiliarId: string) {
    if (remision != null && grupoFamiliarId != null && grupoFamiliarId != "") {
      let grupoFamiliar = await this.getGrupoFamiliarById(grupoFamiliarId);      
      const removeIndex  = grupoFamiliar.referencias.findIndex(b => b.id == remision.id);
      if(removeIndex >= 0){
        grupoFamiliar.referencias.splice( removeIndex, 1 );        
      } 
      remision.ultimaFechaActualizacion = new Date;
      grupoFamiliar.referencias.push(remision);
      await this.saveOrUpdateGrupoFamiliar(grupoFamiliar);
    }
  }


  async saveOrUpdateSeguimiento(seguimiento: SeguimientosEntityProxy, grupoFamiliarId: string) {
    if (seguimiento != null && grupoFamiliarId != null && grupoFamiliarId != "") {
      let grupoFamiliar = await this.getGrupoFamiliarById(grupoFamiliarId);      
      const removeIndex  = grupoFamiliar.seguimientos.findIndex(b => b.id == seguimiento.id);
      if(removeIndex >= 0){
        grupoFamiliar.seguimientos.splice( removeIndex, 1 );        
      } 
      seguimiento.ultimaFechaActualizacion = new Date;
      grupoFamiliar.seguimientos.push(seguimiento);
      await this.saveOrUpdateGrupoFamiliar(grupoFamiliar);
    }
  }

  async saveOrUpdateNecesidades(necesidades: NecesidadesEspecificasEntityProxy, grupoFamiliarId: string) {
    if (necesidades != null && grupoFamiliarId != null && grupoFamiliarId != "") {
      let grupoFamiliar = await this.getGrupoFamiliarById(grupoFamiliarId);  
      necesidades.ultimaFechaActualizacion = new Date;          
      grupoFamiliar.necesidadesEspecifica = necesidades;
      await this.saveOrUpdateGrupoFamiliar(grupoFamiliar);
    }
  }

  async saveOrUpdateGrupoFamiliar(grupoFamiliar: GrupoFamiliarProxy) {
    if (grupoFamiliar != null) {
      grupoFamiliar.ultimaFechaActualizacion = new Date();
      let grupoFamiliarSaved = await this.getGrupoFamiliarById(grupoFamiliar.id);
      if (grupoFamiliarSaved != null) {
        //grupoFamiliarSaved = grupoFamiliar.mergeGrupoFamiliar(grupoFamiliarSaved)
        grupoFamiliarSaved = grupoFamiliar;
        this.entidadSerice.update(grupoFamiliarSaved.id, new Entidad(grupoFamiliarSaved.id, TipoEntidad.grupoFamiliar, grupoFamiliarSaved, true))
        this.utilService.infoMessage("Registro actualizado exitósamente","")        
      } else { 
        this.entidadSerice.save(new Entidad(grupoFamiliar.id, TipoEntidad.grupoFamiliar, grupoFamiliar, true));
        this.utilService.infoMessage("Registro guardado exitósamente","")        
      }
      this.incrementarDirty();
    }
  }
  
  incrementarDirty() {
    let dirtyCounData = localStorage.getItem("dirtyCountData");
    if(dirtyCounData == null) dirtyCounData = "0";
    let integer: number = +localStorage.getItem("dirtyCountData");
    integer++;
    localStorage.setItem("dirtyCountData", integer.toString());
  }  
}

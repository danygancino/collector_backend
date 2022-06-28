import { Injectable } from '@angular/core';
import Dexie from 'dexie';
import { Catalogo, CatalogoProxy, PaisesEntityProxy, ProvinciasEntityProxy } from '../models/catalogo';
import { DatabaseService } from './databaseservice';
import { v4 as uuidv4 } from 'uuid';
import { environment } from 'src/environments/environment';
import { apiConection } from '../models/apiConection.enum';
import { HttpClient } from '@angular/common/http';
import { CatalogoItems } from '../models/catalogo.items.enum';
import { UtilService } from './util.service';
import { UserService } from './user.service';

const base_url = environment.base_url;

@Injectable()
export class CatalogoService {
  table: Dexie.Table<CatalogoProxy, number>;
  catalogo: CatalogoProxy;
  isBusy: boolean = false;

  constructor(private database: DatabaseService, private http: HttpClient, private utilService: UtilService) {
    this.table = this.database.table('catalogo');
  }

  public async getCatalogo(): Promise<CatalogoProxy> {
    if (this.catalogo == null && !this.isBusy) {
      this.catalogo = await (await this.table.toArray()).shift();
      if (this.catalogo == null) {
        this.isBusy = true;
        this.utilService.infoMessage("Catálogos no iniciados", "Descargando nuevos catálogos")
        this.fillCatalogo();
        setTimeout(function () {
          location.reload();
        }, 2000);
      }
    }
    return this.catalogo
  }


  public add(data: CatalogoProxy) {
    return this.table.add(data);
  }

  public update(id, data: CatalogoProxy) {
    return this.table.update(id, data);
  }

  public remove(id) {
    return this.table.delete(id);
  }

  public getById(id: string) {
    return this.table.where({ id: id }).first();
  }

  public getLabel(catalogoItem: CatalogoItems, catalogoid: string): string {
    if (this.catalogo == null || catalogoid == null) return "";
    let label;
    if (catalogoItem == CatalogoItems.nivelesParentesco) label = this.catalogo.nivelesParentesco.filter(c => c.id == catalogoid).shift();
    if (catalogoItem == CatalogoItems.oficios) label = label = this.catalogo.oficios.filter(c => c.id == catalogoid).shift();
    if (catalogoItem == CatalogoItems.organizacionesApoyo) label = this.catalogo.organizacionesApoyo.filter(c => c.id == catalogoid).shift();
    if (catalogoItem == CatalogoItems.organizacionesReferencia) label = this.catalogo.organizacionesReferencia.filter(c => c.id == catalogoid).shift();
    if (catalogoItem == CatalogoItems.profesiones) label = this.catalogo.profesiones.filter(c => c.id == catalogoid).shift();
    if (catalogoItem == CatalogoItems.sexos) label = this.catalogo.sexos.filter(c => c.id == catalogoid).shift();
    if (catalogoItem == CatalogoItems.situacionesMigratorias) label = this.catalogo.situacionesMigratorias.filter(c => c.id == catalogoid).shift();
    if (catalogoItem == CatalogoItems.tiposAsistencia) label = this.catalogo.tiposAsistencia.filter(c => c.id == catalogoid).shift();
    if (catalogoItem == CatalogoItems.tiposAyuda) label = this.catalogo.tiposAyuda.filter(c => c.id == catalogoid).shift();
    if (catalogoItem == CatalogoItems.tiposDocumento) label = this.catalogo.tiposDocumento.filter(c => c.id == catalogoid).shift();
    if (catalogoItem == CatalogoItems.tiposSeguimiento) label = this.catalogo.tiposSeguimiento.filter(c => c.id == catalogoid).shift();
    if (catalogoItem == CatalogoItems.componentes) label = this.catalogo.componentes.filter(c => c.id == catalogoid).shift();
    if (catalogoItem == CatalogoItems.discapacidades) label = this.catalogo.discapacidades.filter(c => c.id == catalogoid).shift();
    if (catalogoItem == CatalogoItems.estatutosMigratorios) label = this.catalogo.estatutosMigratorios.filter(c => c.id == catalogoid).shift();
    if (catalogoItem == CatalogoItems.etnias) label = this.catalogo.etnias.filter(c => c.id == catalogoid).shift();
    if (catalogoItem == CatalogoItems.generos) label = this.catalogo.generos.filter(c => c.id == catalogoid).shift();
    if (catalogoItem == CatalogoItems.lgbtis) label = this.catalogo.lgbtis.filter(c => c.id == catalogoid).shift();
    if (catalogoItem == CatalogoItems.motivosReferencias) label = this.catalogo.motivosReferencias.filter(c => c.id == catalogoid).shift();
    if (catalogoItem == CatalogoItems.motivosSalidaPais) label = this.catalogo.motivosSalidaPais.filter(c => c.id == catalogoid).shift();
    if (catalogoItem == CatalogoItems.nacionalidades) label = this.catalogo.nacionalidades.filter(c => c.id == catalogoid).shift();
    if (catalogoItem == CatalogoItems.necesidadesEspecificasHabitabilidad) label = this.catalogo.necesidadesEspecificasHabitabilidad.filter(c => c.id == catalogoid).shift();
    if (catalogoItem == CatalogoItems.necesidadesEspecificasProteccion) label = this.catalogo.necesidadesEspecificasProteccion.filter(c => c.id == catalogoid).shift();
    if (catalogoItem == CatalogoItems.nivelesEscolaridad) label = this.catalogo.nivelesEscolaridad.filter(c => c.id == catalogoid).shift();
    if (label != null) return label.nombre; 
    return ""
  }

  public fillCatalogo() {

    let url = base_url + apiConection.getCatalogs;
    this.http.get(url).subscribe((catalogo: CatalogoProxy) => {
      if (catalogo != null) {
        this.deleteAll();
        this.add(catalogo).then(r => {
          this.isBusy = false;
          this.utilService.successMessage("Catálogos descargados exitósamente", "");
        })
      }

    });
  }

  public fillFakeCatalogo() {
    this.deleteAll();
    var catalogo = new CatalogoProxy();
    let componentes: Catalogo[] = [];
    let discapacidades: Catalogo[] = [];
    let estatutosMigratorios: Catalogo[] = [];
    let etnias: Catalogo[] = [];
    let generos: Catalogo[] = [];
    let lgbtis: Catalogo[] = [];
    let motivosReferencias: Catalogo[] = [];
    let motivosSalidaPais: Catalogo[] = [];
    let nacionalidades: Catalogo[] = [];
    let necesidadesEspecificasProteccion: Catalogo[] = [];
    let necesidadesEspecificasHabitabilidad: Catalogo[] = [];
    let nivelesEscolaridad: Catalogo[] = [];
    let nivelesParentesco: Catalogo[] = [];
    let oficios: Catalogo[] = [];
    let organizacionesApoyo: Catalogo[] = [];
    let organizacionesReferencia: Catalogo[] = [];
    let profesiones: Catalogo[] = [];
    let sexos: Catalogo[] = [];
    let situacionesMigratorias: Catalogo[] = [];
    let tiposAsistencia: Catalogo[] = [];
    let tiposAyuda: Catalogo[] = [];
    let tiposDocumento: Catalogo[] = [];
    let tiposSeguimiento: Catalogo[] = [];
    let paises: PaisesEntityProxy[] = [];
    let ciudades: Catalogo[] = [];
    let provincias: ProvinciasEntityProxy[] = [];

    componentes.push(new Catalogo("1", "PROTECCIÓN", "ACTIVO"));
    componentes.push(new Catalogo("2", "HABITABILIDAD", "ACTIVO"));
    componentes.push(new Catalogo("3", "INCLUSIÓN SOCIOECONÓMICA", "ACTIVO"));
    componentes.push(new Catalogo("4", "OTRO ", "ACTIVO"));
    
    generos.push(new Catalogo("1", "MASCULINO ", "ACTIVO"));
    generos.push(new Catalogo("2", "FEMENINO ", "ACTIVO"));
    generos.push(new Catalogo("3", "OTRO ", "ACTIVO"));

    lgbtis.push(new Catalogo("1", "SI ", "ACTIVO"));
    lgbtis.push(new Catalogo("2", "NO ", "ACTIVO"));
    lgbtis.push(new Catalogo("3", "NO DEFINIDO", "ACTIVO"));

    nacionalidades.push(new Catalogo("1", "ECUATORIANA", "ACTIVO"));
    nacionalidades.push(new Catalogo("2", "VENEZOLANA", "ACTIVO"));
    nacionalidades.push(new Catalogo("3", "COLOMBIANA", "ACTIVO"));

    etnias.push(new Catalogo("1", "BLANCO", "ACTIVO"));
    etnias.push(new Catalogo("2", "MESTIZO", "ACTIVO"));
    etnias.push(new Catalogo("3", "AFRODESCENDIENTE", "ACTIVO"));

    tiposDocumento.push(new Catalogo("1", "CÉDULA", "ACTIVO"));
    tiposDocumento.push(new Catalogo("2", "PASAPORTE", "ACTIVO"));
    tiposDocumento.push(new Catalogo("3", "TARJETA ANDINA", "ACTIVO"));

    organizacionesApoyo.push(new Catalogo("1", "ACNUR", "ACTIVO"));
    organizacionesApoyo.push(new Catalogo("2", "ADRA", "ACTIVO"));
    organizacionesApoyo.push(new Catalogo("3", "ALAS DE COLIBRÍ", "ACTIVO"));

    organizacionesReferencia.push(new Catalogo("1", "ACNUR", "ACTIVO"));
    organizacionesReferencia.push(new Catalogo("2", "ADRA", "ACTIVO"));
    organizacionesReferencia.push(new Catalogo("3", "ALAS DE COLIBRÍ", "ACTIVO"));

    tiposAsistencia.push(new Catalogo("1", "ACONDICIONAMIENTO VIVIENDA MULTIFAMILIAR", "ACTIVO"));
    tiposAsistencia.push(new Catalogo("2", "ACTIVIDAD DE AUTOGESTIÓN COMUNITARIA", "ACTIVO"));
    tiposAsistencia.push(new Catalogo("3", "ACTIVIDAD DE PLANIFICACIÓN COMUNITARIA", "ACTIVO"));

    tiposAyuda.push(new Catalogo("1", "ASESORÍA LEGAL", "ACTIVO"));
    tiposAyuda.push(new Catalogo("2", "ACCESO A MÉTODOS ANTICONCEPTIVOS /MÉTODOS PREVENTIVOS", "ACTIVO"));
    tiposAyuda.push(new Catalogo("3", "CAPACITACIÓN/TALLER EN TEMAS DE PROTECCIÓN", "ACTIVO"));

    nivelesParentesco.push(new Catalogo("1", "PUNTO FOCAL FAMILIAR", "ACTIVO"));
    nivelesParentesco.push(new Catalogo("2", "PADRE (DEL PUNTO FOCAL)", "ACTIVO"));
    nivelesParentesco.push(new Catalogo("3", "MADRE (DEL PUNTO FOCAL)", "ACTIVO"));

    discapacidades.push(new Catalogo("1", "SI", "ACTIVO"));
    discapacidades.push(new Catalogo("2", "NO", "ACTIVO"));
    discapacidades.push(new Catalogo("3", "NO DEFINIDO", "ACTIVO"));



    for (let index = 1; index < 4; index++) {

      estatutosMigratorios.push(new Catalogo("" + index, "estatutosMigratorios " + index, "ACTIVO"));

      motivosReferencias.push(new Catalogo("" + index, "motivosReferencias " + index, "ACTIVO"));
      motivosSalidaPais.push(new Catalogo("" + index, "motivosSalidaPais " + index, "ACTIVO"));
   
      necesidadesEspecificasProteccion.push(new Catalogo("" + index, "necesidadesEspecificasProteccion " + index, "ACTIVO"));
      necesidadesEspecificasHabitabilidad.push(new Catalogo("" + index, "necesidadesEspecificasHabitabilidad " + index, "ACTIVO"));
      nivelesEscolaridad.push(new Catalogo("" + index, "nivelesEscolaridad " + index, "ACTIVO"));
      oficios.push(new Catalogo("" + index, "oficios " + index, "ACTIVO"));

      profesiones.push(new Catalogo("" + index, "profesiones " + index, "ACTIVO"));
      sexos.push(new Catalogo("" + index, "sexos " + index, "ACTIVO"));
      situacionesMigratorias.push(new Catalogo("" + index, "situacionesMigratorias " + index, "ACTIVO"));
      tiposSeguimiento.push(new Catalogo("" + index, "tiposSeguimiento " + index, "ACTIVO"));
      ciudades.push(new Catalogo("" + index, "ciudad " + index, "ACTIVO"));
    }

    catalogo.componentes = componentes;
    catalogo.discapacidades = discapacidades;
    catalogo.estatutosMigratorios = estatutosMigratorios;
    catalogo.estatutosMigratorios = estatutosMigratorios;
    catalogo.etnias = etnias;
    catalogo.generos = generos;
    catalogo.lgbtis = lgbtis;
    catalogo.motivosReferencias = motivosReferencias;
    catalogo.motivosSalidaPais = motivosSalidaPais;
    catalogo.nacionalidades = nacionalidades;
    catalogo.necesidadesEspecificasProteccion = necesidadesEspecificasProteccion;
    catalogo.necesidadesEspecificasHabitabilidad = necesidadesEspecificasHabitabilidad;
    catalogo.nivelesEscolaridad = nivelesEscolaridad;

    catalogo.nivelesParentesco = nivelesParentesco;
    catalogo.oficios = oficios;
    catalogo.organizacionesApoyo = organizacionesApoyo;
    catalogo.organizacionesReferencia = organizacionesReferencia;
    catalogo.profesiones = profesiones;
    catalogo.sexos = sexos;
    catalogo.situacionesMigratorias = situacionesMigratorias;
    catalogo.tiposAsistencia = tiposAsistencia;
    catalogo.tiposAyuda = tiposAyuda;
    catalogo.tiposDocumento = tiposDocumento;
    catalogo.tiposSeguimiento = tiposSeguimiento;

    let provincia: ProvinciasEntityProxy = { id: uuidv4(), nombre: "Pichincha", state: "ACTIVE", ciudades }
    provincias.push(provincia)
    let pais: PaisesEntityProxy = { id: uuidv4(), nombre: "Ecuador", state: "ACTIVE", provincias }
    paises.push(pais);

    catalogo.paises = paises;
    this.add(catalogo);

  }

  private deleteAll() {
    return this.table.clear();
  }
}

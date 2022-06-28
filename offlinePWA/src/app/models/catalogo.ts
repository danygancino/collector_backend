import { v4 as uuidv4 } from 'uuid';
// Stores the currently-being-typechecked object for error messages.
let obj: any = null;
export class CatalogoProxy {
    componentes: Catalogo[];
    discapacidades: Catalogo[] | null;
    estatutosMigratorios: Catalogo[] | null;
    etnias: Catalogo[] | null;
    generos: Catalogo[] | null;
    lgbtis: Catalogo[] | null;
    motivosReferencias: Catalogo[] | null;
    motivosSalidaPais: Catalogo[] | null;
    nacionalidades: Catalogo[] | null;
    necesidadesEspecificasProteccion: Catalogo[] | null;
    necesidadesEspecificasHabitabilidad: Catalogo[] | null;
    nivelesEscolaridad: Catalogo[] | null;
    nivelesParentesco: Catalogo[] | null;
    oficios: Catalogo[] | null;
    organizacionesApoyo: Catalogo[] | null;
    organizacionesReferencia: Catalogo[] | null;
    profesiones: Catalogo[] | null;
    sexos: Catalogo[] | null;
    situacionesMigratorias: Catalogo[] | null;
    tiposAsistencia: Catalogo[] | null;
    tiposAyuda: Catalogo[] | null;
    tiposDocumento: Catalogo[] | null;
    tiposSeguimiento: Catalogo[] | null;
    paises: PaisesEntityProxy[] | null;
   static Parse(d: string): CatalogoProxy {
    return CatalogoProxy.Create(JSON.parse(d));
  }
  public static Create(d: any, field: string = 'root'): CatalogoProxy {
    if (!field) {
      obj = d;
      field = "root";
    }
    if (d === null || d === undefined) {
      throwNull2NonNull(field, d);
    } else if (typeof(d) !== 'object') {
      throwNotObject(field, d, false);
    } else if (Array.isArray(d)) {
      throwIsArray(field, d, false);
    }
    checkArray(d.componentes, field + ".componentes");
    if (d.componentes) {
      for (let i = 0; i < d.componentes.length; i++) {
        d.componentes[i] = Catalogo.Create(d.componentes[i], field + ".componentes" + "[" + i + "]");
      }
    }
    if (d.componentes === undefined) {
      d.componentes = null;
    }
    checkArray(d.discapacidades, field + ".discapacidades");
    if (d.discapacidades) {
      for (let i = 0; i < d.discapacidades.length; i++) {
        d.discapacidades[i] = Catalogo.Create(d.discapacidades[i], field + ".discapacidades" + "[" + i + "]");
      }
    }
    if (d.discapacidades === undefined) {
      d.discapacidades = null;
    }
    checkArray(d.estatutosMigratorios, field + ".estatutosMigratorios");
    if (d.estatutosMigratorios) {
      for (let i = 0; i < d.estatutosMigratorios.length; i++) {
        d.estatutosMigratorios[i] = Catalogo.Create(d.estatutosMigratorios[i], field + ".estatutosMigratorios" + "[" + i + "]");
      }
    }
    if (d.estatutosMigratorios === undefined) {
      d.estatutosMigratorios = null;
    }
    checkArray(d.etnias, field + ".etnias");
    if (d.etnias) {
      for (let i = 0; i < d.etnias.length; i++) {
        d.etnias[i] = Catalogo.Create(d.etnias[i], field + ".etnias" + "[" + i + "]");
      }
    }
    if (d.etnias === undefined) {
      d.etnias = null;
    }
    checkArray(d.generos, field + ".generos");
    if (d.generos) {
      for (let i = 0; i < d.generos.length; i++) {
        d.generos[i] = Catalogo.Create(d.generos[i], field + ".generos" + "[" + i + "]");
      }
    }
    if (d.generos === undefined) {
      d.generos = null;
    }
    checkArray(d.lgbtis, field + ".lgbtis");
    if (d.lgbtis) {
      for (let i = 0; i < d.lgbtis.length; i++) {
        d.lgbtis[i] = Catalogo.Create(d.lgbtis[i], field + ".lgbtis" + "[" + i + "]");
      }
    }
    if (d.lgbtis === undefined) {
      d.lgbtis = null;
    }
    checkArray(d.motivosReferencias, field + ".motivosReferencias");
    if (d.motivosReferencias) {
      for (let i = 0; i < d.motivosReferencias.length; i++) {
        d.motivosReferencias[i] = Catalogo.Create(d.motivosReferencias[i], field + ".motivosReferencias" + "[" + i + "]");
      }
    }
    if (d.motivosReferencias === undefined) {
      d.motivosReferencias = null;
    }
    checkArray(d.motivosSalidaPais, field + ".motivosSalidaPais");
    if (d.motivosSalidaPais) {
      for (let i = 0; i < d.motivosSalidaPais.length; i++) {
        d.motivosSalidaPais[i] = Catalogo.Create(d.motivosSalidaPais[i], field + ".motivosSalidaPais" + "[" + i + "]");
      }
    }
    if (d.motivosSalidaPais === undefined) {
      d.motivosSalidaPais = null;
    }
    checkArray(d.nacionalidades, field + ".nacionalidades");
    if (d.nacionalidades) {
      for (let i = 0; i < d.nacionalidades.length; i++) {
        d.nacionalidades[i] = Catalogo.Create(d.nacionalidades[i], field + ".nacionalidades" + "[" + i + "]");
      }
    }
    if (d.nacionalidades === undefined) {
      d.nacionalidades = null;
    }
    checkArray(d.necesidadesEspecificasProteccion, field + ".necesidadesEspecificasProteccion");
    if (d.necesidadesEspecificasProteccion) {
      for (let i = 0; i < d.necesidadesEspecificasProteccion.length; i++) {
        d.necesidadesEspecificasProteccion[i] = Catalogo.Create(d.necesidadesEspecificasProteccion[i], field + ".necesidadesEspecificasProteccion" + "[" + i + "]");
      }
    }
    if (d.necesidadesEspecificasProteccion === undefined) {
      d.necesidadesEspecificasProteccion = null;
    }
    checkArray(d.necesidadesEspecificasHabitabilidad, field + ".necesidadesEspecificasHabitabilidad");
    if (d.necesidadesEspecificasHabitabilidad) {
      for (let i = 0; i < d.necesidadesEspecificasHabitabilidad.length; i++) {
        d.necesidadesEspecificasHabitabilidad[i] = Catalogo.Create(d.necesidadesEspecificasHabitabilidad[i], field + ".necesidadesEspecificasHabitabilidad" + "[" + i + "]");
      }
    }
    if (d.necesidadesEspecificasHabitabilidad === undefined) {
      d.necesidadesEspecificasHabitabilidad = null;
    }
    checkArray(d.nivelesEscolaridad, field + ".nivelesEscolaridad");
    if (d.nivelesEscolaridad) {
      for (let i = 0; i < d.nivelesEscolaridad.length; i++) {
        d.nivelesEscolaridad[i] = Catalogo.Create(d.nivelesEscolaridad[i], field + ".nivelesEscolaridad" + "[" + i + "]");
      }
    }
    if (d.nivelesEscolaridad === undefined) {
      d.nivelesEscolaridad = null;
    }
    checkArray(d.nivelesParentesco, field + ".nivelesParentesco");
    if (d.nivelesParentesco) {
      for (let i = 0; i < d.nivelesParentesco.length; i++) {
        d.nivelesParentesco[i] = Catalogo.Create(d.nivelesParentesco[i], field + ".nivelesParentesco" + "[" + i + "]");
      }
    }
    if (d.nivelesParentesco === undefined) {
      d.nivelesParentesco = null;
    }
    checkArray(d.oficios, field + ".oficios");
    if (d.oficios) {
      for (let i = 0; i < d.oficios.length; i++) {
        d.oficios[i] = Catalogo.Create(d.oficios[i], field + ".oficios" + "[" + i + "]");
      }
    }
    if (d.oficios === undefined) {
      d.oficios = null;
    }
    checkArray(d.organizacionesApoyo, field + ".organizacionesApoyo");
    if (d.organizacionesApoyo) {
      for (let i = 0; i < d.organizacionesApoyo.length; i++) {
        d.organizacionesApoyo[i] = Catalogo.Create(d.organizacionesApoyo[i], field + ".organizacionesApoyo" + "[" + i + "]");
      }
    }
    if (d.organizacionesApoyo === undefined) {
      d.organizacionesApoyo = null;
    }
    checkArray(d.organizacionesReferencia, field + ".organizacionesReferencia");
    if (d.organizacionesReferencia) {
      for (let i = 0; i < d.organizacionesReferencia.length; i++) {
        d.organizacionesReferencia[i] = Catalogo.Create(d.organizacionesReferencia[i], field + ".organizacionesReferencia" + "[" + i + "]");
      }
    }
    if (d.organizacionesReferencia === undefined) {
      d.organizacionesReferencia = null;
    }
    checkArray(d.profesiones, field + ".profesiones");
    if (d.profesiones) {
      for (let i = 0; i < d.profesiones.length; i++) {
        d.profesiones[i] = Catalogo.Create(d.profesiones[i], field + ".profesiones" + "[" + i + "]");
      }
    }
    if (d.profesiones === undefined) {
      d.profesiones = null;
    }
    checkArray(d.sexos, field + ".sexos");
    if (d.sexos) {
      for (let i = 0; i < d.sexos.length; i++) {
        d.sexos[i] = Catalogo.Create(d.sexos[i], field + ".sexos" + "[" + i + "]");
      }
    }
    if (d.sexos === undefined) {
      d.sexos = null;
    }
    checkArray(d.situacionesMigratorias, field + ".situacionesMigratorias");
    if (d.situacionesMigratorias) {
      for (let i = 0; i < d.situacionesMigratorias.length; i++) {
        d.situacionesMigratorias[i] = Catalogo.Create(d.situacionesMigratorias[i], field + ".situacionesMigratorias" + "[" + i + "]");
      }
    }
    if (d.situacionesMigratorias === undefined) {
      d.situacionesMigratorias = null;
    }
    checkArray(d.tiposAsistencia, field + ".tiposAsistencia");
    if (d.tiposAsistencia) {
      for (let i = 0; i < d.tiposAsistencia.length; i++) {
        d.tiposAsistencia[i] = Catalogo.Create(d.tiposAsistencia[i], field + ".tiposAsistencia" + "[" + i + "]");
      }
    }
    if (d.tiposAsistencia === undefined) {
      d.tiposAsistencia = null;
    }
    checkArray(d.tiposAyuda, field + ".tiposAyuda");
    if (d.tiposAyuda) {
      for (let i = 0; i < d.tiposAyuda.length; i++) {
        d.tiposAyuda[i] = Catalogo.Create(d.tiposAyuda[i], field + ".tiposAyuda" + "[" + i + "]");
      }
    }
    if (d.tiposAyuda === undefined) {
      d.tiposAyuda = null;
    }
    checkArray(d.tiposDocumento, field + ".tiposDocumento");
    if (d.tiposDocumento) {
      for (let i = 0; i < d.tiposDocumento.length; i++) {
        d.tiposDocumento[i] = Catalogo.Create(d.tiposDocumento[i], field + ".tiposDocumento" + "[" + i + "]");
      }
    }
    if (d.tiposDocumento === undefined) {
      d.tiposDocumento = null;
    }
    checkArray(d.tiposSeguimiento, field + ".tiposSeguimiento");
    if (d.tiposSeguimiento) {
      for (let i = 0; i < d.tiposSeguimiento.length; i++) {
        d.tiposSeguimiento[i] = Catalogo.Create(d.tiposSeguimiento[i], field + ".tiposSeguimiento" + "[" + i + "]");
      }
    }
    if (d.tiposSeguimiento === undefined) {
      d.tiposSeguimiento = null;
    }
    checkArray(d.paises, field + ".paises");
    if (d.paises) {
      for (let i = 0; i < d.paises.length; i++) {
        d.paises[i] = PaisesEntityProxy.Create(d.paises[i], field + ".paises" + "[" + i + "]");
      }
    }
    if (d.paises === undefined) {
      d.paises = null;
    }
    return d;
  }
  public constructor() {   
      this.componentes = Catalogo[2];
  }
}

export class Catalogo {
    id: string;
    nombre: string;
    state: string;
   static Parse(d: string): Catalogo {
    return Catalogo.Create(JSON.parse(d));
  }
  public static Create(d: any, field: string = 'root'): Catalogo {
    if (!field) {
      obj = d;
      field = "root";
    }
    if (d === null || d === undefined) {
      throwNull2NonNull(field, d);
    } else if (typeof(d) !== 'object') {
      throwNotObject(field, d, false);
    } else if (Array.isArray(d)) {
      throwIsArray(field, d, false);
    }
    checkNumber(d.id, false, field + ".id");
    checkString(d.nombre, false, field + ".nombre");
    checkString(d.state, false, field + ".state");
    return d;
  }
   constructor(id: string, nombre: string, state: string) {
    this.id = id;
    this.nombre = nombre;
    this.state = state;
       
  }
}

export class PaisesEntityProxy {
    id: number;
    nombre: string;
    state: string;
    provincias: ProvinciasEntityProxy[] | null;
   static Parse(d: string): PaisesEntityProxy {
    return PaisesEntityProxy.Create(JSON.parse(d));
  }
  public static Create(d: any, field: string = 'root'): PaisesEntityProxy {
    if (!field) {
      obj = d;
      field = "root";
    }
    if (d === null || d === undefined) {
      throwNull2NonNull(field, d);
    } else if (typeof(d) !== 'object') {
      throwNotObject(field, d, false);
    } else if (Array.isArray(d)) {
      throwIsArray(field, d, false);
    }
    checkNumber(d.id, false, field + ".id");
    checkString(d.nombre, false, field + ".nombre");
    checkString(d.state, false, field + ".state");
    checkArray(d.provincias, field + ".provincias");
    if (d.provincias) {
      for (let i = 0; i < d.provincias.length; i++) {
        d.provincias[i] = ProvinciasEntityProxy.Create(d.provincias[i], field + ".provincias" + "[" + i + "]");
      }
    }
    if (d.provincias === undefined) {
      d.provincias = null;
    }
    return d;
  }
  public constructor() {
    
  }
}

export class ProvinciasEntityProxy {
    id: number;
    nombre: string;
    state: string;
    ciudades: Catalogo[] | null;
   static Parse(d: string): ProvinciasEntityProxy {
    return ProvinciasEntityProxy.Create(JSON.parse(d));
  }
  public static Create(d: any, field: string = 'root'): ProvinciasEntityProxy {
    if (!field) {
      obj = d;
      field = "root";
    }
    if (d === null || d === undefined) {
      throwNull2NonNull(field, d);
    } else if (typeof(d) !== 'object') {
      throwNotObject(field, d, false);
    } else if (Array.isArray(d)) {
      throwIsArray(field, d, false);
    }
    checkNumber(d.id, false, field + ".id");
    checkString(d.nombre, false, field + ".nombre");
    checkString(d.state, false, field + ".state");
    checkArray(d.ciudades, field + ".ciudades");
    if (d.ciudades) {
      for (let i = 0; i < d.ciudades.length; i++) {
        d.ciudades[i] = Catalogo.Create(d.ciudades[i], field + ".ciudades" + "[" + i + "]");
      }
    }
    if (d.ciudades === undefined) {
      d.ciudades = null;
    }
    return d;
  }
  private constructor() {
   
  }
}

function throwNull2NonNull(field: string, d: any): never {
  return errorHelper(field, d, "non-nullable object", false);
}
function throwNotObject(field: string, d: any, nullable: boolean): never {
  return errorHelper(field, d, "object", nullable);
}
function throwIsArray(field: string, d: any, nullable: boolean): never {
  return errorHelper(field, d, "object", nullable);
}
function checkArray(d: any, field: string): void {
  if (!Array.isArray(d) && d !== null && d !== undefined) {
    errorHelper(field, d, "array", true);
  }
}
function checkNumber(d: any, nullable: boolean, field: string): void {
  if (typeof(d) !== 'number' && (!nullable || (nullable && d !== null && d !== undefined))) {
    errorHelper(field, d, "number", nullable);
  }
}
function checkString(d: any, nullable: boolean, field: string): void {
  if (typeof(d) !== 'string' && (!nullable || (nullable && d !== null && d !== undefined))) {
    errorHelper(field, d, "string", nullable);
  }
}
function errorHelper(field: string, d: any, type: string, nullable: boolean): never {
  if (nullable) {
    type += ", null, or undefined";
  }
  throw new TypeError('Expected ' + type + " at " + field + " but found:\n" + JSON.stringify(d) + "\n\nFull object:\n" + JSON.stringify(obj));
}

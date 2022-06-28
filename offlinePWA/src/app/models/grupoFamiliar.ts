
import { v4 as uuidv4 } from 'uuid';
import { Catalogo } from './catalogo';

// Stores the currently-being-typechecked object for error messages.
let obj: any = null;
export class GrupoFamiliarProxy {
  public id: string;
  public grupoFamiliarId: string;
  public ultimaFechaActualizacion: Date;
  public beneficiarios: BeneficiariosEntityProxy[] | null;
  public montoIngresosMensual: number;
  public asistencias: Asistencias[] | null;
  public referencias: ReferenciasEntityProxy[] | null;
  public integracionesSocioEconomicas: IntegracionesSocioEconomicasEntityProxy[] | null;
  public necesidadesEspecifica: NecesidadesEspecificasEntityProxy| null;
  public seguimientos: SeguimientosEntityProxy[] | null;

  public mergeGrupoFamiliar(grupoFamiliar: GrupoFamiliarProxy) {
    if (grupoFamiliar === null || grupoFamiliar === undefined) {
      return this;
    }

    if (grupoFamiliar.beneficiarios !== null && grupoFamiliar.beneficiarios !== undefined) {
      this.beneficiarios = grupoFamiliar.beneficiarios;
    }

    if (grupoFamiliar.montoIngresosMensual !== null && grupoFamiliar.montoIngresosMensual !== undefined) {
      this.montoIngresosMensual = grupoFamiliar.montoIngresosMensual;
    }

    if (grupoFamiliar.asistencias !== null && grupoFamiliar.asistencias !== undefined) {
      this.asistencias = grupoFamiliar.asistencias;
    }

    if (grupoFamiliar.referencias !== null && grupoFamiliar.referencias !== undefined) {
      this.referencias = grupoFamiliar.referencias;
    }


    if (grupoFamiliar.integracionesSocioEconomicas !== null && grupoFamiliar.integracionesSocioEconomicas !== undefined) {
      this.integracionesSocioEconomicas = grupoFamiliar.integracionesSocioEconomicas;
    }


    if (grupoFamiliar.necesidadesEspecifica !== null && grupoFamiliar.necesidadesEspecifica !== undefined) {
      this.necesidadesEspecifica = grupoFamiliar.necesidadesEspecifica;
    }

    if (grupoFamiliar.seguimientos !== null && grupoFamiliar.seguimientos !== undefined) {
      this.seguimientos = grupoFamiliar.seguimientos;
    }
  }

  constructor() {
    this.id = uuidv4();
    this.beneficiarios = []
    this.referencias = []
    this.asistencias = []
    this.integracionesSocioEconomicas = []
    //this.necesidadesEspecificas
    this.seguimientos = []
    this.montoIngresosMensual = 0
  }
}

export class BeneficiariosEntityProxy {
  public id: string = '';
  public grupoFamiliarId: string = '';
  public grupoFamiliarCode: string = '';
  public ultimaFechaActualizacion: Date;
  public nombres: string = '';
  public nivelParentescoId: number;
  public sexoId: number;
  public fechaNacimiento: Date;
  public edad: number;
  public generoId: number;
  public lgbtiId: number;
  public etniaId: number;
  public nacionalidadId: number;
  public discapacidadId: number;
  public fechaIngresoPais: Date;
  public tipoDocumentoId: number;
  public numeroDocumento: string = '';
  public estatutoMigratorioId: number;
  public situacionMigratoriaId: number;
  public motivoSalidaPaisId: number;
  public ciudadId: number;
  public sector: string;
  public direccion: string;
  public telefono: string = '';
  public nivelEscolaridadId: number;
  public profesionesIds: number[] | null;
  public oficiosIds: number[] | null;
  public observaciones: string;


  public mergeBeneficiario(beneficiario: BeneficiariosEntityProxy) {

    if (beneficiario === null || beneficiario === undefined) {
      return beneficiario;
    }

    if (beneficiario.id !== null && beneficiario.id !== undefined) {
      this.id = beneficiario.id;
    }

    if (beneficiario.grupoFamiliarId !== null && beneficiario.grupoFamiliarId !== undefined) {
      this.grupoFamiliarId = beneficiario.grupoFamiliarId;
    }

    if (beneficiario.nombres !== null && beneficiario.nombres !== undefined) {
      this.nombres = beneficiario.nombres;
    }

    if (beneficiario.nivelParentescoId !== null && beneficiario.nivelParentescoId !== undefined) {
      this.nivelParentescoId = beneficiario.nivelParentescoId;
    }

    if (beneficiario.sexoId !== null && beneficiario.sexoId !== undefined) {
      this.sexoId = beneficiario.sexoId;
    }

    if (beneficiario.fechaNacimiento !== null && beneficiario.fechaNacimiento !== undefined) {
      this.fechaNacimiento = beneficiario.fechaNacimiento;
    }

    if (beneficiario.edad !== null && beneficiario.edad !== undefined) {
      this.edad = beneficiario.edad;
    }

    if (beneficiario.generoId !== null && beneficiario.generoId !== undefined) {
      this.generoId = beneficiario.generoId;
    }

    if (beneficiario.lgbtiId !== null && beneficiario.lgbtiId !== undefined) {
      this.lgbtiId = beneficiario.lgbtiId;
    }

    if (beneficiario.etniaId !== null && beneficiario.etniaId !== undefined) {
      this.etniaId = beneficiario.etniaId;
    }

    if (beneficiario.nacionalidadId !== null && beneficiario.nacionalidadId !== undefined) {
      this.nacionalidadId = beneficiario.nacionalidadId;
    }

    if (beneficiario.discapacidadId !== null && beneficiario.discapacidadId !== undefined) {
      this.discapacidadId = beneficiario.discapacidadId;
    }

    if (beneficiario.fechaIngresoPais !== null && beneficiario.fechaIngresoPais !== undefined) {
      this.fechaIngresoPais = beneficiario.fechaIngresoPais;
    }

    if (beneficiario.tipoDocumentoId !== null && beneficiario.tipoDocumentoId !== undefined) {
      this.tipoDocumentoId = beneficiario.tipoDocumentoId;
    }

    if (beneficiario.numeroDocumento !== null && beneficiario.numeroDocumento !== undefined) {
      this.numeroDocumento = beneficiario.numeroDocumento;
    }

    if (beneficiario.estatutoMigratorioId !== null && beneficiario.estatutoMigratorioId !== undefined) {
      this.estatutoMigratorioId = beneficiario.estatutoMigratorioId;
    }

    if (beneficiario.situacionMigratoriaId !== null && beneficiario.situacionMigratoriaId !== undefined) {
      this.situacionMigratoriaId = beneficiario.situacionMigratoriaId;
    }

    if (beneficiario.motivoSalidaPaisId !== null && beneficiario.motivoSalidaPaisId !== undefined) {
      this.motivoSalidaPaisId = beneficiario.motivoSalidaPaisId;
    }

    if (beneficiario.ciudadId !== null && beneficiario.ciudadId !== undefined) {
      this.ciudadId = beneficiario.ciudadId;
    }

    if (beneficiario.sector !== null && beneficiario.sector !== undefined) {
      this.sector = beneficiario.sector;
    }

    if (beneficiario.telefono !== null && beneficiario.telefono !== undefined) {
      this.telefono = beneficiario.telefono;
    }

    if (beneficiario.nivelEscolaridadId !== null && beneficiario.nivelEscolaridadId !== undefined) {
      this.nivelEscolaridadId = beneficiario.nivelEscolaridadId;
    }

    if (beneficiario.profesionesIds !== null && beneficiario.profesionesIds !== undefined) {
      this.profesionesIds = beneficiario.profesionesIds;
    }

    if (beneficiario.oficiosIds !== null && beneficiario.oficiosIds !== undefined) {
      this.oficiosIds = beneficiario.oficiosIds;
    }

    if (beneficiario.observaciones !== null && beneficiario.observaciones !== undefined) {
      this.observaciones = beneficiario.observaciones;
    }

    if (beneficiario.direccion !== null && beneficiario.direccion !== undefined) {
      this.direccion = beneficiario.direccion;
    }

    if (beneficiario.grupoFamiliarCode !== null && beneficiario.grupoFamiliarCode !== undefined) {
      this.grupoFamiliarCode = beneficiario.grupoFamiliarCode;
    }
  }

  public getFechaNacimiento(){
    return new Date(this.fechaNacimiento)
  }

  constructor() {
    this.id = uuidv4();
  }
}

export class IntegracionesSocioEconomicasEntityProxy {
  public id: string;
  public ultimaFechaActualizacion: Date;
  public organizacionApoyoId: number;
  public tiposAyudaIds: string[] | null;
  public observaciones: string;

  public constructor() {
    this.id = uuidv4();
  }
}

export class NecesidadesEspecificasProteccion {
  public necesidadEspecificaProteccionId: number;
  public cantidad: number;

  public constructor(necesidadEspecificaProteccionId: number, cantidad: number) {
    this.necesidadEspecificaProteccionId = necesidadEspecificaProteccionId;
    this.cantidad = cantidad;
  }
}

export class NecesidadesEspecificasHabitabilidad {
  public necesidadEspecificaHabitabilidadId: number;
  public cantidad: number;

  public constructor(necesidadEspecificaHabitabilidadId: number, cantidad: number) {
    this.necesidadEspecificaHabitabilidadId = necesidadEspecificaHabitabilidadId;
    this.cantidad = cantidad;
  }

}

export class NecesidadesEspecificasEntityProxy {
  public id: string;
  public ultimaFechaActualizacion: Date;
  public necesidadesEspecificasProteccion: NecesidadesEspecificasProteccion[] | null;
  public necesidadesEspecificasHabitabilidad: NecesidadesEspecificasHabitabilidad[] | null;
  public observaciones: null;

  public constructor() {
    this.id = uuidv4();
  }
}

export class SeguimientosEntityProxy {
  public id: string;
  public ultimaFechaActualizacion: Date;
  public fecha: Date;
  public componenteId: number;
  public tipoSeguimientoId: number;
  public observaciones: string;
  
  public constructor() {
    this.id = uuidv4();
  }
}

export class Asistencias {
  public id: string;
  public fecha: Date;
  public ultimaFechaActualizacion: Date;
  public componenteId: number;
  public tipoAsistenciaId: number;
  public observacion: string;
  public nombrePropietarioVivienda: string;
  public numeroDocumentoPropietarioVivienda: string;
  public telefonoPropietarioVivienda: string;
  public numeroEspacioHabitables: string;

  constructor() {
    this.id = uuidv4();
  }
}


export class ReferenciasEntityProxy {
  public id: string;
  public fecha: Date;
  public ultimaFechaActualizacion: Date;
  public beneficiariosIds: string[] | null;
  public organizacionesReferenciaIds: number;
  public motivosReferenciaIds: string[] | null;
  public observacion: string | null;

 
  public constructor() {
    this.id = uuidv4()
  }
}


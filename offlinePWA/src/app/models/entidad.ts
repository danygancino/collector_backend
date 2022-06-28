import { TipoEntidad } from "./tipoEntidad.enum";

export class Entidad {
  public id: string | null;
  public tipoEntidad: TipoEntidad | null;
  public entidad: Object | null;
  public isDirty: string = "false";
  constructor(id: string, tipoEntidad: TipoEntidad, entidad: Object, isDirty: boolean){
    this.id = id;
    this.isDirty = `${isDirty}`
    this.tipoEntidad = tipoEntidad;
    this.entidad = entidad;
  }

}

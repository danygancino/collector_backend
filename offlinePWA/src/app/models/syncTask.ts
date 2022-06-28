import { Entidad } from "./entidad";
import { GrupoFamiliarProxy } from "./grupoFamiliar";

export class SyncTask{

    constructor(
        public url: string,
        public entidad: Entidad,
        public params?: string) { }


    equals(task: SyncTask): boolean {
      return this.entidad.id == task.entidad.id;
    }
}


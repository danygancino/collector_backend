import { Injectable } from '@angular/core';
import Dexie from 'dexie';

@Injectable()
export class DatabaseService extends Dexie {
  constructor() {
    super("database");
    var db = this;
    db.version(2).stores({
      entidad: '++id, tipoEntidad, entidad, isDirty',
      catalogo: '++id, entidad, documento'
    });
  }
}



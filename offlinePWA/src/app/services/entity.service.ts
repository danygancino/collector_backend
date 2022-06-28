import { Injectable } from '@angular/core';
import Dexie from 'dexie';
import { Entidad } from '../models/entidad';
import { DatabaseService } from './databaseservice';

@Injectable()
export class EntidadService {
  table: Dexie.Table<Entidad, number>;

  constructor(private database: DatabaseService) {
    this.table = this.database.table('entidad');  
  }

  public save(entidad: Entidad){
    this.add(entidad);
  }

  
  public saveAll(entidades: Entidad[]){
    return this.table.bulkAdd(entidades);
  }  

  public getAll() {
    return this.table.toArray();
  }


  public count() {
    return this.table.count();
  }

  public getAllDirty() {
    return this.table.where({isDirty: "true"}).toArray();
  }

  private add(data) {
    return this.table.add(data);
  }

  public update(id, entidad: Entidad) {
    return this.table.update(id, entidad);
  }

  public remove(id) {
    return this.table.delete(id);
  }

  public deleteAll() {
    return this.table.clear();
  }

  public deleteAllDirty() {
    this.getAllDirty().then(d => d.forEach( a => this.remove(a.id)));
  }


  public getById(id: string){
    return this.table.where({id: id})
  }
}

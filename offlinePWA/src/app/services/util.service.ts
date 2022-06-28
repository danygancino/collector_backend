import { Injectable } from '@angular/core';
import { MessageService } from 'primeng/api';

@Injectable()
export class UtilService  {
  constructor(private messageService: MessageService) {}

  infoMessage(resumen: string, detalle: string, debug: boolean = false){
    this.toasMessage("info", resumen, detalle, debug);
  }

  warnMessage(resumen: string, detalle: string, debug: boolean = false){
    this.toasMessage("warn", resumen, detalle, debug);
  }


  successMessage(resumen: string, detalle: string, debug: boolean = false){
    this.toasMessage("success", resumen, detalle, debug);
  }


  toasMessage(success: string, resumen: string = "", detalle: string= "", debug: boolean = false) {
    this.messageService.add({severity:success, summary:resumen, detail:detalle});
    if(debug)
      console.log(resumen + " " + detalle)
  }

  getEdad(fechaNacimiento:Date): number{
    let timeDiff = Math.abs(Date.now() - fechaNacimiento.getTime());
    let age = Math.floor((timeDiff / (1000 * 3600 * 24))/365.25);    
    return age;
  }


  getDateFromString(stringDate: string){
    if(stringDate!=null)
      return new Date(stringDate);
    return new Date();
  }


  public resolveFieldData(data: any, field: string): any {
    if (data && field) {
      if (field.indexOf('.') === -1) {
        return data[field];
      } else {
        const fields: string[] = field.split('.');
        let value = data;
        for (let i = 0, len = fields.length; i < len; ++i) {
          if (value == null) {
            return null;
          }
          value = value[fields[i]];
        }
        return value;
      }
    } else {
      return null;
    }
  }

  public stateFilter(value: string, filter: string) {

    if (filter === undefined || filter === null || filter.trim() === '') {
      return true;
    }
    if (value === undefined || value === null || value.length < 0) {
      return false;
    }
    let tester: string = '';
    if (value === 'ACTIVE') {
      tester = 'ACTIVO';
    }
    if (value === 'INACTIVE') {
      tester = 'INACTIVO';
    }

    return tester.toLowerCase().includes(filter.toLowerCase()); // || filter.toLowerCase().includes(tester.toLowerCase());

  }

}




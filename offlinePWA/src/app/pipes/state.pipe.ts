import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'state'
})
export class StatePipe implements PipeTransform {

  transform(value: string): string {

    if (!value) {
      return null;
    }
    if (value === 'ACTIVE') {
      return 'ACTIVO';
    } else if (value === 'INACTIVE') {
      return 'INACTIVO';
    }
  }
}

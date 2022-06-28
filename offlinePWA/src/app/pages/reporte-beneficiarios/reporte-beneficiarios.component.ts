import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { apiConection } from 'src/app/models/apiConection.enum';
import { Entidad } from 'src/app/models/entidad';
import { BeneficiariosEntityProxy, GrupoFamiliarProxy } from 'src/app/models/grupoFamiliar';
import { TipoEntidad } from 'src/app/models/tipoEntidad.enum';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-reporte-beneficiarios',
  templateUrl: './reporte-beneficiarios.component.html',
  styleUrls: ['./reporte-beneficiarios.component.scss']
})
export class ReporteBeneficiariosComponent implements OnInit {
  cols: any[];

  exportColumns: any[];
  gruposFamiliares: GrupoFamiliarProxy[]
  beneficiario: BeneficiariosEntityProxy
  beneficiarios: BeneficiariosEntityProxy[]

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    this.getGrupoFamiliaresReport();
    this.cols = [
      { field: 'entidad["grupoFamiliarId"]', header: 'Id' },
      { field: 'name', header: 'Name' },
      { field: 'category', header: 'Category' },
      { field: 'quantity', header: 'Quantity' }
    ];
    this.exportColumns = this.cols.map(col => ({ title: col.header, dataKey: col.field }));
  }

  async getGrupoFamiliaresReport() {

    const base_url = environment.base_url;
    let url = base_url + apiConection.getGruposFamiliaresReport;
    return await this.http.get(url).subscribe(response => {
      this.gruposFamiliares = response["gruposFamiliares"].map(gf => new Entidad(gf.id, TipoEntidad.grupoFamiliar, gf, false));;
      this.gruposFamiliares.forEach(gf => {
        gf.beneficiarios.forEach(b => {
          this.beneficiarios.push(b);
        })
      })
    });
  }



}

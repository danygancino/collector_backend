import { Component, OnInit } from '@angular/core';
import { BreadcrumbService } from 'src/app/app.breadcrumb.service';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.scss']
})
export class FormComponent implements OnInit {

  constructor(private breadcrumbService: BreadcrumbService) {
    this.breadcrumbService.setItems([
      { label: 'Búsqueda beneficiarios', routerLink: ['/pages/form'] }
    ]);
  }

  ngOnInit(): void {
  }

}

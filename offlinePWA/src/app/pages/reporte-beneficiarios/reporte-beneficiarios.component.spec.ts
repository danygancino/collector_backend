import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReporteBeneficiariosComponent } from './reporte-beneficiarios.component';

describe('ReporteBeneficiariosComponent', () => {
  let component: ReporteBeneficiariosComponent;
  let fixture: ComponentFixture<ReporteBeneficiariosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReporteBeneficiariosComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ReporteBeneficiariosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

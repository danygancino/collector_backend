import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IntegracionSocioeconomicaDetalleComponent } from './integracion-socioeconomica-detalle.component';

describe('IntegracionSocioeconomicaDetalleComponent', () => {
  let component: IntegracionSocioeconomicaDetalleComponent;
  let fixture: ComponentFixture<IntegracionSocioeconomicaDetalleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ IntegracionSocioeconomicaDetalleComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(IntegracionSocioeconomicaDetalleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

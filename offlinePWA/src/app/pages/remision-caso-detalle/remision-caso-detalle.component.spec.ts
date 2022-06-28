import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RemisionCasoDetalleComponent } from './remision-caso-detalle.component';

describe('RemisionCasoDetalleComponent', () => {
  let component: RemisionCasoDetalleComponent;
  let fixture: ComponentFixture<RemisionCasoDetalleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RemisionCasoDetalleComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RemisionCasoDetalleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

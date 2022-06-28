import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ApoyoDetalleComponent } from './apoyo-detalle.component';

describe('ApoyoDetalleComponent', () => {
  let component: ApoyoDetalleComponent;
  let fixture: ComponentFixture<ApoyoDetalleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ApoyoDetalleComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ApoyoDetalleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

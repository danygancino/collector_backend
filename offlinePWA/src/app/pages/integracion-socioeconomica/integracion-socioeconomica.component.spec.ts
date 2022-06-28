import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IntegracionSocioeconomicaComponent } from './integracion-socioeconomica.component';

describe('IntegracionSocioeconomicaComponent', () => {
  let component: IntegracionSocioeconomicaComponent;
  let fixture: ComponentFixture<IntegracionSocioeconomicaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ IntegracionSocioeconomicaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(IntegracionSocioeconomicaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

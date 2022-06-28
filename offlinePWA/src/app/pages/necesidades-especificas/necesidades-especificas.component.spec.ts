import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NecesidadesEspecificasComponent } from './necesidades-especificas.component';

describe('NecesidadesEspecificasComponent', () => {
  let component: NecesidadesEspecificasComponent;
  let fixture: ComponentFixture<NecesidadesEspecificasComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NecesidadesEspecificasComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NecesidadesEspecificasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

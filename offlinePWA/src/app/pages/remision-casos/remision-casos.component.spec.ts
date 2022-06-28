import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RemisionCasosComponent } from './remision-casos.component';

describe('RemisionCasosComponent', () => {
  let component: RemisionCasosComponent;
  let fixture: ComponentFixture<RemisionCasosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RemisionCasosComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RemisionCasosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

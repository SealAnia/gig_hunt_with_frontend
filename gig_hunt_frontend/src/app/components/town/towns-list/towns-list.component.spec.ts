import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TownsListComponent } from './towns-list.component';

describe('TownsListComponent', () => {
  let component: TownsListComponent;
  let fixture: ComponentFixture<TownsListComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [TownsListComponent]
    });
    fixture = TestBed.createComponent(TownsListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

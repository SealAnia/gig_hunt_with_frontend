import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TownDetailsComponent } from './town-details.component';

describe('TownDetailsComponent', () => {
  let component: TownDetailsComponent;
  let fixture: ComponentFixture<TownDetailsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [TownDetailsComponent]
    });
    fixture = TestBed.createComponent(TownDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

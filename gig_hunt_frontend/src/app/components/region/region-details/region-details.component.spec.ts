import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegionDetailsComponent } from './region-details.component';

describe('RegionDetailsComponent', () => {
  let component: RegionDetailsComponent;
  let fixture: ComponentFixture<RegionDetailsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [RegionDetailsComponent]
    });
    fixture = TestBed.createComponent(RegionDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

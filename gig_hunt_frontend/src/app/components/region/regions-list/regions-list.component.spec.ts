import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegionsListComponent } from './regions-list.component';

describe('RegionsListComponent', () => {
  let component: RegionsListComponent;
  let fixture: ComponentFixture<RegionsListComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [RegionsListComponent]
    });
    fixture = TestBed.createComponent(RegionsListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

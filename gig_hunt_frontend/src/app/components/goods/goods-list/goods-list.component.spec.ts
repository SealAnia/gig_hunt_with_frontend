import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GoodsListComponent } from './goods-list.component';

describe('GoodsListComponent', () => {
  let component: GoodsListComponent;
  let fixture: ComponentFixture<GoodsListComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [GoodsListComponent]
    });
    fixture = TestBed.createComponent(GoodsListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

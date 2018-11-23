import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchStatisticsComponent } from './search-statistics.component';

describe('SearchStatisticsComponent', () => {
  let component: SearchStatisticsComponent;
  let fixture: ComponentFixture<SearchStatisticsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchStatisticsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchStatisticsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

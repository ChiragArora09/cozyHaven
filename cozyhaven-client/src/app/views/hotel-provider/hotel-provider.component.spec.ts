import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HotelProviderComponent } from './hotel-provider.component';

describe('HotelProviderComponent', () => {
  let component: HotelProviderComponent;
  let fixture: ComponentFixture<HotelProviderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HotelProviderComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(HotelProviderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

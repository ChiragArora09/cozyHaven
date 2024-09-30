import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FlightProviderComponent } from './flight-provider.component';

describe('FlightProviderComponent', () => {
  let component: FlightProviderComponent;
  let fixture: ComponentFixture<FlightProviderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FlightProviderComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(FlightProviderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

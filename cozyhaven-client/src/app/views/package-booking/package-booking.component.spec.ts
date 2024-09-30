import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PackageBookingComponent } from './package-booking.component';

describe('PackageBookingComponent', () => {
  let component: PackageBookingComponent;
  let fixture: ComponentFixture<PackageBookingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PackageBookingComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PackageBookingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

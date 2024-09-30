import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-service-options',
  standalone: true,
  imports: [],
  templateUrl: './service-options.component.html',
  styleUrl: './service-options.component.css'
})
export class ServiceOptionsComponent {

  constructor (private router: Router) {}

  onFlightClick(){
    this.router.navigateByUrl('/flight-booking')
  }
  onBusClick(){
    this.router.navigateByUrl('/bus-booking')
  }
  onHotelClick(){
    this.router.navigateByUrl('/hotel-booking')
  }
  onHolidayPackageClick(){
    this.router.navigateByUrl('/holiday-packages')
  }

}

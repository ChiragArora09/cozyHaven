import { Component } from '@angular/core';
import { NavbarComponent } from '../../components/navbar/navbar.component';
import { ServiceOptionsComponent } from '../../components/service-options/service-options.component';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [NavbarComponent, ServiceOptionsComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {

}

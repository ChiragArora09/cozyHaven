import { Routes } from '@angular/router';
import { LoginPageComponent } from './auth/login/login-page/login-page.component';
import { HomeComponent } from './views/home/home.component';
import { BusProviderComponent } from './views/bus-provider/bus-provider.component';
import { FlightProviderComponent } from './views/flight-provider/flight-provider.component';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';
import { BusBookingComponent } from './views/bus-booking/bus-booking.component';
import { FlightBookingComponent } from './views/flight-booking/flight-booking.component';
import { HotelBookingComponent } from './views/hotel-booking/hotel-booking.component';
import { PackageBookingComponent } from './views/package-booking/package-booking.component';


export const routes: Routes = [
    {
        "path":"", component: LoginPageComponent
    },
    {
        "path":"home", component: HomeComponent
    },
    {
        "path":"bus-provider", component: BusProviderComponent
    },
    {
        "path":"flight-provider", component: FlightProviderComponent
    },
    {
        "path":"bus-booking", component: BusBookingComponent
    },
    {
        "path":"flight-booking", component: FlightBookingComponent
    },
    {
        "path":"hotel-booking", component: HotelBookingComponent
    },
    {
        "path":"holiday-packages", component: PackageBookingComponent
    },
    {
        "path":"**", component: PageNotFoundComponent
    }
];

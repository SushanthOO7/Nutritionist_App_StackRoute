import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../services/authentication.service';
import { RouteService } from '../services/route.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(public authService: AuthenticationService, public routeService: RouteService) { }

  isLoggedIn: boolean;

  ngOnInit() {
    this.isLoggedIn = this.authService.isUserLoggedIn();
  }

  logout() {
    this.authService.logOut();
    sessionStorage.removeItem("logged");
    this.routeService.routeToHome();
  }
}

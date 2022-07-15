import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Food } from '../classes/food';
import { Location } from '@angular/common';

@Injectable({
  providedIn: 'root'
})
export class RouteService {

  constructor(private router: Router, private location: Location) { }

  routeToDisplay() {
    this.router.navigate(['/display']);
  }

  routeToLogin() {
    this.router.navigate(['/login']);
  }
  
  routeToHome() {
    this.router.navigate(['/home']);
  }

  routeToFavorite() {
    this.router.navigate(['/favorite']);
  }

  routeToSearch() {
    this.router.navigate(['/searchResult']);
  }

  routeToDetailsDialog() {
    this.router.navigate(['/detailsDialog']);
  }

  routeBack() {
    this.location.back();
  }

}

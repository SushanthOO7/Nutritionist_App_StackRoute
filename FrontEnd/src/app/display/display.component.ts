import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Food, Nutrients } from '../classes/food';
import { PersistanceService } from '../services/persistance.service';
import { AuthenticationService } from '../services/authentication.service';
import { RouteService } from '../services/route.service';

@Component({
  selector: 'app-display',
  templateUrl: './display.component.html',
  styleUrls: ['./display.component.css']
})
export class DisplayComponent implements OnInit {

  foodDetails: Food ;
  displayedColumns : string[] = ["Name", "Amount", "Unit", "Dervied By."];
  datasource: Array<Nutrients>;
  favoriteClicked: boolean = false;
  mealClicked: boolean = false;
  public navbarCollapsed = true;

  constructor(public persistService: PersistanceService, public authService: AuthenticationService, public routerService: RouteService) { 
    this.foodDetails = null;
    this.favoriteClicked = false;
  }

  ngOnInit() {
    if(this.persistService.selectedFood ==  null)
      alert("No food selected");
    else {
      this.foodDetails = this.persistService.selectedFood;
      this.datasource = this.foodDetails.foodNutrients;
    }
  }


  saveFavorite() {
    
   
      this.persistService.saveFavorite(this.foodDetails);
      this.favoriteClicked = true;  
    
  }


}

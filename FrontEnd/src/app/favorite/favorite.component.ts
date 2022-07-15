import { Component, OnInit } from '@angular/core';
import { PersistanceService } from '../services/persistance.service';
import { Food } from '../classes/food';
import { RouteService } from '../services/route.service';

@Component({
  selector: 'app-favorite',
  templateUrl: './favorite.component.html',
  styleUrls: ['./favorite.component.css']
})
export class FavoriteComponent implements OnInit {

  // foodList: Array<Food> = [];
  categorise: boolean = false;
  brandWise: Array<Food> = [];
  categories: Array<Array<Food>> =[];
  empty: boolean;
  refresh: boolean = false;
  p=0;
  
  constructor(public persistService: PersistanceService, public routerService: RouteService) { }

  ngOnInit() {
    if(this.persistService.getFavorite == null)
      this.empty = true;
    else{
      this.empty=false;
      this.persistService.getFavorite();
    if(this.categorise == true) {
      console.log("true");
      this.categorise = false;
      // this.pers
      this.byCategory();
    }
    }
  }
  
  viewDetails(fdcId) {
    this.persistService.selectedFdcId = fdcId;
    this.routerService.routeToDetailsDialog();
  }
  
  deleteFavorite(fdcId) {
    // if(sessionStorage.getItem('username') != null)
      this.persistService.deleteFavorite(fdcId);
      if(this.categorise ==  true) {
        this.categorise = false;
        this.categories = [];
        this.byCategory();
        // this.persistService.getFavorite();
        // this.ngOnInit();
        // location.reload();
        // this.categorise = true;
        // this.byCategory();
      }
  }

  byCategory() {
    if(this.categorise == false) {
      console.log("byCategory");
      this.categorise = true;
      this.categories = [];
      let n = this.persistService.favoriteFood.length;
      let visited: Array<boolean> = [];
      for(var i=0;i<n;i++)
        visited[i]=false;
      // console.log("boolean array created");
      for(i=0;i<n;i++)
      {
        if(visited[i] == false) {
          this.brandWise = new Array();
          this.brandWise.push(this.persistService.favoriteFood[i]);
          visited[i]=true;
          for(var j=i+1;j<n;j++) {
            if(this.persistService.favoriteFood[i].brandOwner == this.persistService.favoriteFood[j].brandOwner) {
              this.brandWise.push(this.persistService.favoriteFood[j]);
              visited[j] = true;
            }
          }
          this.categories.push(this.brandWise);
        }
      }
    }
    else  
      this.categorise = false;
    console.log(this.categories);
  }

}

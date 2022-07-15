import { Injectable } from '@angular/core';
import { Food, Nutrients } from '../classes/food';
import { HttpClient, HttpParams } from '@angular/common/http';
import { RouteService } from './route.service';
import { MatchFood } from '../classes/matchFood';
import { AuthenticationService } from './authentication.service';

@Injectable({
  providedIn: 'root'
})
export class PersistanceService {

  selectedFdcId: number;
  selectedFood: Food;
  favoriteFood: Array<Food> = [];
 // mealFood: Array<Food> = [];
  //mealNutrients: Array<Nutrients> =[];
  generalSearch: string = "";
  brandOwner: string = "";
  ingredients: string = "";
  required: boolean = false;
  sortField: string = "";
  searchResult: Array<MatchFood>;
  brandRecommendation: Array<MatchFood> = [];
  brands: Array<string> = [];
  description: Array<string> = [];
  baseUrl = "http://localhost:9095/api/v1/food";

  constructor(private httpClient: HttpClient, private routerService:RouteService, private authService: AuthenticationService) { }

  saveFavorite(food) {
    // let params = new HttpParams()
    //   .set('username',username);
    this.httpClient.post(this.baseUrl+"/saveFavorite", food).subscribe(data => {
       console.log(data);
      alert("Added to favorite");
      
    }, err => {
      // alert("Not Logged In");
      this.routerService.routeToLogin();
    });
  }


  getFavorite() {
    // this.authService.isUserAuthenticated();
    // let p =  new HttpParams()
    // .set('username',username);
    return this.httpClient.post<Array<Food>>(this.baseUrl+"/getFavorite",null).subscribe(data => {
      this.favoriteFood = data;
      // console.log(data);
    }, err => {
      // alert("Not Logged In");
      this.routerService.routeToLogin();
    });
  }


  deleteFavorite(fdcId) {
    let p = new HttpParams()
    // .set('username',username)
    .set('fdcId',fdcId);

    return this.httpClient.post<Array<Food>>(this.baseUrl+"/deleteFavorite",null,{params:p}).subscribe(data => {
      this.favoriteFood = data;
    }, err => {
      // alert("Not Logged In");
      this.routerService.routeToLogin();
    });
  }

  

  getBrands() { 
    this.brands = [];
    // let p =  new HttpParams()
    // .set('username',username);
    this.httpClient.post<Array<Food>>(this.baseUrl+"/getFavorite",null).subscribe(data => {
      this.favoriteFood = data;
      let n = this.favoriteFood.length;
      let visited: Array<boolean> = [];
      for(var i =0; i<n; i++) {
        visited[i] = false;
      }
      for(i=0; i<n; i++) {
        if(visited[i] == false) {
          if(this.favoriteFood[i].brandOwner != null)
            this.brands.push(this.favoriteFood[i].brandOwner);
          else
            this.brands.push("");
          for(var j=i+1; j<n; j++) {
            if(this.favoriteFood[i].brandOwner == this.favoriteFood[j].brandOwner) {
              visited[j] = true;
            }
          }
        }
      }
      console.log(this.brands)
    }, err => {
      this.routerService.routeToLogin();
    }); 
  }

  getDescription(username) {
    this.description = [];
    // let p =  new HttpParams()
    // .set('username',username);
    this.httpClient.post<Array<Food>>(this.baseUrl+"/getFavorite",null).subscribe(data => {
      this.favoriteFood = data;
      let n = this.favoriteFood.length;
      let visited: Array<boolean> = [];
      for(var i =0; i<n; i++) {
        visited[i] = false;
      }
      for(i=0; i<n; i++) {
        if(visited[i] == false) {
          this.description.push(this.favoriteFood[i].description);
          for(var j=i+1; j<n; j++) {
            if(this.favoriteFood[i].description == this.favoriteFood[j].description) {
              visited[j] = true;
            }
          }
        }
      }
      console.log(this.description)
    }, err => {
      // alert("Not Logged In");
      this.routerService.routeToLogin();
    }); 
  }

  // getNutrients() {
  //   this.mealNutrients = [];
  //   for(var i =0;i<this.mealFood.length;i++) {
  //     console.log(this.mealFood[i].foodNutrients.length);
  //     for(var j=0;j<this.mealFood[i].foodNutrients.length;j++) {
  //       this.mealNutrients.push(this.mealFood[i].foodNutrients[j]);
  //     }
  //   }
  //   // console.log(this.mealNutrients);
  //   }  
}


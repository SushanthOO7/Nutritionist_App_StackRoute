import { Component, OnInit } from '@angular/core';
import { MatchFood } from '../classes/matchFood';
import { FoodDataCentralService } from '../services/food-data-central.service';
import { PersistanceService } from '../services/persistance.service';
import { RouteService } from '../services/route.service';
import { faSearch} from '@fortawesome/free-solid-svg-icons';
import{ AuthenticationService} from '../services/authentication.service';
@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  faSearch=faSearch;
  
  generalSearch : string = "";
  FoodList: Array<MatchFood>
  totalPages: number
  EachFood: MatchFood
  

  constructor(public authService: AuthenticationService,public fetchService: FoodDataCentralService, public persistService: PersistanceService, public routerService: RouteService) { }

  ngOnInit() {
   
  }

  fetch() {
    console.log(this.generalSearch);
    this.persistService.generalSearch = this.generalSearch;
    this.persistService.searchResult = [];
    this.fetchService.fetchTotalMatch(this.generalSearch,"","",false,"","").subscribe(data => {
      this.totalPages = data.totalPages;
      if(this.totalPages>2)
        this.totalPages = 2;
      for(var i =1;i<=this.totalPages;i++) {
        this.fetchService.fetchMatchFoodPageWise(this.generalSearch,"","",i,false,"","").subscribe(data => {
          data.foods.forEach(element => {
            this.EachFood = new MatchFood;
            this.EachFood.fdcId = element.fdcId;
            this.EachFood.description = element.description;
            this.EachFood.additionalDescription = element.additionalDescription;
            this.EachFood.dataType = element.dataType;
            this.EachFood.publishedDate = element.publishedDate;
            this.EachFood.allHighlightFields = element.allHighlightFields;
            this.EachFood.brandOwner = element.brandOwner;
            this.EachFood.ingredients = element.ingredients;
            this.persistService.searchResult.push(this.EachFood);
          });
          //  = this.FoodList;
          this.routerService.routeToSearch();
        }, err => {
          console.log(err.message);
        });
      }
    },err => {});
    
  }
}
  

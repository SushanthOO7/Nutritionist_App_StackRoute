//import { Component, OnInit, Input, SystemJsNgModuleLoader } from '@angular/core';
import { Component, OnInit, Input} from '@angular/core';

import { MatchFood } from '../classes/matchFood';
import { FoodDataCentralService } from '../services/food-data-central.service';
import { Food, Nutrients } from '../classes/food';
import { RouteService } from '../services/route.service';
import { PersistanceService } from '../services/persistance.service';
import { FormGroup, FormControl, Validators, ControlContainer, Form } from '@angular/forms';

@Component({
  selector: 'app-search-result',
  templateUrl: './search-result.component.html',
  styleUrls: ['./search-result.component.css']
})
export class SearchResultComponent implements OnInit {

  @Input()
  list: Array<MatchFood>;
  sortSelected: boolean = false;
  sortDirection: String = "desc";
  dispayedColumns: string[] = ["Description","Type","Brand","Published Date"]
  // dataSource: Array<MatchFood>;
  showSpinner: boolean = false;
  empty: boolean;
  
  food: Food;
  nutrients: Nutrients;
  nutrientList: Array<Nutrients>;
  brandOwner_val: String;
  ingredients_val: String;
  generalSearch_val: String;
  required_val: boolean;
  sortField_val: String;
  totalPages: number
  EachFood: MatchFood

  filterForm: FormGroup;
  generalSearch: FormControl;
  ingredients: FormControl;
  brandOwner: FormControl;
  required: FormControl;
  sortField: FormControl;
  p:any=0;

  constructor(private fetchService: FoodDataCentralService, private routerService: RouteService, public persistService : PersistanceService) { 
    this.food = new Food();
    
    this.generalSearch = new FormControl('');
    this.ingredients = new FormControl('');
    this.brandOwner = new FormControl('');
    this.required =  new FormControl('');
    this.sortField = new FormControl('');
    this.filterForm = new FormGroup({
      generalSearch: this.generalSearch,
      ingredients: this.ingredients,
      brandOwner: this.brandOwner,
      required: this.required,
      sortField: this.sortField
    })
  }

  ngOnInit() {
    this.generalSearch.setValue(this.persistService.generalSearch);
    this.brandOwner.setValue(this.persistService.brandOwner);
    this.ingredients.setValue(this.persistService.ingredients);
    this.required_val = this.persistService.required;
    this.sortField_val = this.persistService.sortField;
    // console.log(this.persistService.searchResult);
  }

  
  fetchNutrients(fdcId) {
    this.fetchService.fetchFood(fdcId).subscribe(data => {
      this.food.fdcId = data.fdcId;
      this.food.description = data.description;
      this.food.dataType = data.dataType;
      this.food.brandOwner = data.brandOwner;
      this.food.brandedFoodCategory = data.brandedFoodCategory;
      this.food.ingredients = data.ingredients;
      this.food.publicationDate = data.publicationDate;
      this.food.scientificName = data.scientificName;
      this.food.foodNutrients = [];
      data.foodNutrients.forEach(element => {
        if(element.amount > 0 ) {
          // console.log(element.nutrient.name);
          this.nutrients = new Nutrients();
          this.nutrients.id = element.nutrient.id;
          this.nutrients.name = element.nutrient.name;
          this.nutrients.unitName = element.nutrient.unitName;
          if(element.foodNutrientDerivation)
            this.nutrients.description = element.foodNutrientDerivation.description;
          else
            this.nutrients.description = "";
          this.nutrients.amount = element.amount;
          this.food.foodNutrients.push(this.nutrients);
          }
        else if (element.amount == 0) {
          if(element.foodNutrientDerivation) {
          this.nutrients = new Nutrients();
          this.nutrients.id = element.nutrient.id;
          this.nutrients.name = element.nutrient.name;
          this.nutrients.unitName = element.nutrient.unitName;
          this.nutrients.description = element.foodNutrientDerivation.description;
          this.nutrients.amount = element.amount;
          this.food.foodNutrients.push(this.nutrients);
        }
      }
      });
      // this.food.foodNutrients = this.nutrientList;
      this.persistService.selectedFood = this.food;
      console.log(this.food); 
      this.routerService.routeToDisplay();
    },err => {
      console.log(err);
    });
  }

  filterSearch() {
    this.showSpinner = true;
    this.empty = false;
    console.log(this.filterForm.value);
    this.persistService.generalSearch = this.filterForm.value.generalSearch;
    this.persistService.brandOwner = this.filterForm.value.brandOwner;
    this.persistService.ingredients = this.filterForm.value.ingredients;
    this.persistService.sortField = this.filterForm.value.sortField;
    if(this.filterForm.value.required)
      this.persistService.required = true;
    // console.log(this.persistService.generalSearch);
    this.generalSearch_val = this.persistService.generalSearch;
    this.brandOwner_val = this.filterForm.value.brandOwner;
    this.ingredients_val = this.filterForm.value.ingredients;
    this.required_val = this.persistService.required;
    this.sortField_val = this.persistService.sortField;
    this.persistService.searchResult = [];
    this.fetchService.fetchTotalMatch(this.generalSearch_val,this.brandOwner_val,this.ingredients_val, this.required_val,this.sortField_val,this.sortDirection).subscribe(data => {
      this.totalPages = data.totalPages;
      if(this.totalPages == 0)
        this.empty = true;
      console.log(data.totalHits);
      if(this.totalPages>2)
        this.totalPages = 2;
      for(var i =1;i<=this.totalPages;i++) {
        this.fetchService.fetchMatchFoodPageWise(this.generalSearch_val,this.brandOwner_val,this.ingredients_val,i, this.required_val,this.sortField_val,this.sortDirection).subscribe(data => {
          
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
          // this.ngOnInit();
          // console.log(this.FoodList.length);
        }, err => {
          console.log(err.message);
        });
      }
    },err => {});
    this.showSpinner = false;
   }

   sort() {
     this.sortSelected = true;
     this.filterSearch();
   }

   sortDirec() {
    if(this.sortDirection.includes("asc"))
      this.sortDirection = "desc";
    else
      this.sortDirection = "asc";
    this.filterSearch();
   }

   resetSearch() {
     this.generalSearch.setValue("");
     this.ingredients.setValue("");
     this.brandOwner.setValue("");
     this.required_val=false;
     this.sortField.setValue("");
     this.sortSelected = false;
     this.sortDirection = "asc";
     this.persistService.generalSearch = "";
     this.persistService.brandOwner = "";
     this.persistService.ingredients = "";
     this.persistService.required = false;
     this.persistService.sortField = "";
     this.persistService.searchResult = new Array();
   }


}



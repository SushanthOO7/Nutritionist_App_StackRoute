import { Component, OnInit, Inject } from '@angular/core';
import { PersistanceService } from '../services/persistance.service';
import { FoodDataCentralService } from '../services/food-data-central.service';
import { Food, Nutrients } from '../classes/food';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-details-view-dialog',
  templateUrl: './details-view-dialog.component.html',
  styleUrls: ['./details-view-dialog.component.css']
})
export class DetailsViewDialogComponent implements OnInit {
  food: Food = new Food;
  nutrients: Nutrients = new Nutrients;
  nutrientList: Array<Nutrients>;

  constructor(public persistService: PersistanceService, public fetchService: FoodDataCentralService,
    public dialogRef: MatDialogRef<DetailsViewDialogComponent>, @Inject(MAT_DIALOG_DATA)private data: any,) { }

  ngOnInit() {
    this.fetchService.fetchFood(this.persistService.selectedFdcId).subscribe(data => {
      // console.log(data.foo)
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
        // console.log
        this.nutrients = new Nutrients();
        this.nutrients.id = element.nutrient.id;
        this.nutrients.name = element.nutrient.name;
        this.nutrients.unitName = element.nutrient.unitName;
        this.nutrients.amount = element.amount;
        if(element.foodNutrientDerivation)
          this.nutrients.description = element.foodNutrientDerivation.description;
        else
          this.nutrients.description = "";
        this.food.foodNutrients.push(this.nutrients);
      });
      // this.food.foodNutrients = this.nutrientList;
      this.persistService.selectedFood = this.food;
      console.log(this.food); 
      // this.routerService.routeToDisplay();
    },err => {
      console.log(err);
    });
  }

}


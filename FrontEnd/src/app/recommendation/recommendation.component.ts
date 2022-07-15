import { Component, OnInit } from '@angular/core';
import { PersistanceService } from '../services/persistance.service';

@Component({
  selector: 'app-recommendation',
  templateUrl: './recommendation.component.html',
  styleUrls: ['./recommendation.component.css']
})
export class RecommendationComponent implements OnInit {
  
  brands: Array<String> = [];

  constructor(public persistService: PersistanceService) { }

  ngOnInit() {
    // if(sessionStorage.getItem('username') != null) {
      this.persistService.getBrands();
      // this.persistService.getDescription(sessionStorage.getItem('username'));
    // }
  }
}

import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

const api_key  ="gyDAwOoheDsjw0c3eaoUuqn7uZafkg8RxgP7gbHj";

@Injectable({
  providedIn: 'root'
})
export class FoodDataCentralService {

  constructor(private httpClient: HttpClient) { }

  fetchTotalMatch(generalSearch,brandOwner,ingredients, required, sortField, sortDirection): Observable<any> {
    // console.log(sortDirection);
    return this.httpClient.get<any>(`https://api.nal.usda.gov/fdc/v1/search?api_key=${api_key}&generalSearchInput=${generalSearch}&brandOwner=${brandOwner}&ingredients=${ingredients}&requireAllWords=${required}&sortField=${sortField}&sortDirection=${sortDirection}`);
  }

  fetchMatchFoodPageWise(generalSearch, brandOwner, ingredients,pageNumber, required, sortField, sortDirection): Observable<any> {
    // console.log(sortDirection);
    return this.httpClient.get<any>(`https://api.nal.usda.gov/fdc/v1/search?api_key=${api_key}&generalSearchInput=${generalSearch}&pageNumber=${pageNumber}&brandOwner=${brandOwner}&ingredients=${ingredients}&requireAllWords=${required}&sortField=${sortField}&sortDirection=${sortDirection}`);
  }

  fetchFood(fdcId): Observable<any> {
    return this.httpClient.get<any>(`https://api.nal.usda.gov/fdc/v1/${fdcId}?api_key=${api_key}`);
  }
}

import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { HeaderComponent } from './header/header.component';
import { SearchComponent } from './search/search.component';
import { SearchResultComponent } from './search-result/search-result.component';
import { DisplayComponent } from './display/display.component';
import { FavoriteComponent } from './favorite/favorite.component';
// import { MealComponent } from './meal/meal.component';
import { RecommendationComponent } from './recommendation/recommendation.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { AuthenticationService } from './services/authentication.service';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgxPaginationModule } from 'ngx-pagination';
import { FoodDataCentralService } from './services/food-data-central.service';
import { Routes, RouterModule } from '@angular/router';
import { RouteService } from './services/route.service';
import { PersistanceService } from './services/persistance.service';
import { FlexLayoutModule } from '@angular/flex-layout';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatListModule } from '@angular/material/list';
import { MatDialogModule } from '@angular/material/dialog';
import { DetailsDialogOpenerComponent } from './details-dialog-opener/details-dialog-opener.component';
import { DetailsViewDialogComponent } from './details-view-dialog/details-view-dialog.component';
import { BrandRecommendationComponent } from './brand-recommendation/brand-recommendation.component'; 
import { TokenIterceptor } from './token-iterceptor';
import { MatTableModule } from '@angular/material/table';
import { MatGridListModule } from '@angular/material/grid-list';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import {MatTabsModule} from '@angular/material/tabs';
import { MatButtonToggleModule } from '@angular/material/button-toggle';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import { CanActivateGuard } from './can-activate.guard';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';





const route: Routes = [
  { path: '', redirectTo: 'home', pathMatch:'full' },
  { path:'home', component: SearchComponent },
  { path: 'searchResult', component: SearchResultComponent },
  { path: 'display', component: DisplayComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'favorite', component: FavoriteComponent },
  { path: 'detailsDialog', component: DetailsDialogOpenerComponent },
  { path: 'recommendation', component: RecommendationComponent, children: [] },
  { path: 'brandRecommendation/:brand', component: BrandRecommendationComponent },
  { path: "**",redirectTo:"home" }
]

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    HeaderComponent,
    SearchComponent,
    SearchResultComponent,
    DisplayComponent,
    FavoriteComponent,
    RecommendationComponent,
    DetailsDialogOpenerComponent,
    DetailsViewDialogComponent,
    BrandRecommendationComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    NgxPaginationModule,
    RouterModule.forRoot(route),
    MatButtonToggleModule,
    FlexLayoutModule,
    MatToolbarModule,
    MatSidenavModule,
    MatFormFieldModule,
    MatInputModule,
    MatCardModule,
    FontAwesomeModule,
    MatIconModule,
    MatButtonModule,
    MatListModule,
    MatDialogModule,
    MatTableModule,
    MatGridListModule,
    NgbModule,
    MatButtonModule,
    MatTabsModule,
    MatProgressSpinnerModule,
    MatSlideToggleModule
  ],
  exports: [ RouterModule ],
  providers: [AuthenticationService, FoodDataCentralService, CanActivateGuard, RouteService, PersistanceService, 
    { provide: HTTP_INTERCEPTORS, useClass: TokenIterceptor, multi: true } ],
  bootstrap: [AppComponent],
  entryComponents: [ DetailsViewDialogComponent ]
})
export class AppModule { }

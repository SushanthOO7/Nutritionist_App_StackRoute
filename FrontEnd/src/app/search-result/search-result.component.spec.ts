import { async, ComponentFixture, TestBed, inject } from '@angular/core/testing';
import { ReactiveFormsModule, FormGroupDirective } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatIconModule } from '@angular/material/icon';
import { MatTooltipModule } from '@angular/material/tooltip';
import { HttpClientModule } from '@angular/common/http';
import { By } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule, MatSnackBar, MatSnackBarModule } from '@angular/material';
import { MatCardModule } from '@angular/material/card';
import { MatButtonToggleModule } from '@angular/material/button-toggle';
import { MatDialogModule } from '@angular/material';
import { RouterTestingModule } from '@angular/router/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { RouteService } from 'src/app/services/route.service';
import { SearchResultComponent } from './search-result.component';
import { NgxPaginationModule } from 'ngx-pagination';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { PersistanceService } from '../services/persistance.service';

describe('SearchResultComponent', () => {
  let searchcomponent: SearchResultComponent;
  let fixture: ComponentFixture<SearchResultComponent>;
  let routerService: RouteService;
  let persistanceService: PersistanceService;
  let mySpy: any;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchResultComponent ],
      imports: [ ReactiveFormsModule,
        BrowserAnimationsModule,
        MatIconModule,
        MatTooltipModule,
        FormsModule,
        RouterTestingModule,
        MatTooltipModule,
        MatFormFieldModule,
        HttpClientModule,
        MatInputModule,
        ReactiveFormsModule,
        MatButtonModule,
        MatCardModule,
        ReactiveFormsModule,
        MatButtonToggleModule,
        MatDialogModule,
        NgxPaginationModule,
        MatSnackBarModule,
        MatSlideToggleModule,    
        HttpClientTestingModule ],
        providers: [ RouteService, PersistanceService ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchResultComponent);
    searchcomponent = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(searchcomponent).toBeTruthy();
  });

  it('should set submitted to true', async(() => {
    searchcomponent.filterForm;
    expect(searchcomponent.filterForm).toBeTruthy();
  }));

  it('should contain div tag', () => {
    let element = fixture.debugElement.query(By.css('div'));
    expect(element).toBeTruthy();
  });

  it('form valid when ingredients is empty', () => {
    expect(searchcomponent.ingredients.valid).toBeTruthy();
  });
  it('form valid when brandowner is empty', () => {
    expect(searchcomponent.brandOwner.valid).toBeTruthy();
  });
  it('form valid when required all fields is unchecked', () => {
    expect(searchcomponent.required.valid).toBeTruthy();
  });
  it('form valid when sort field is not selected', () => {
    expect(searchcomponent.sortField.valid).toBeTruthy();
  });

});

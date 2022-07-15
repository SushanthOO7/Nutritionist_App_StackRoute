import { async, ComponentFixture, TestBed, inject } from '@angular/core/testing';
import { SearchComponent } from './search.component';
import { ReactiveFormsModule, FormGroupDirective } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatPaginatorModule } from '@angular/material/paginator';
import { By } from '@angular/platform-browser';
import { RouterTestingModule } from '@angular/router/testing';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { Observable } from 'rxjs';
import { RouteService } from 'src/app/services/route.service';
import { PersistanceService } from '../services/persistance.service';
import { FoodDataCentralService } from '../services/food-data-central.service';
describe('SearchComponent', () => {
    let searchcomponent: SearchComponent;
    let fixture: ComponentFixture<SearchComponent>
    let persistanceService: PersistanceService;
    let foodDataCentralService: FoodDataCentralService;
    let routerService: RouteService;
    let mySpy: any;
    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [SearchComponent],
            imports: [ReactiveFormsModule,
                FormsModule,
                RouterTestingModule,
                MatFormFieldModule,
                HttpClientModule,
                MatPaginatorModule,
                ReactiveFormsModule,
                ReactiveFormsModule,
                HttpClientTestingModule
            ],
            providers: [ FoodDataCentralService, PersistanceService, RouteService ]
        })
            .compileComponents();
    }));
    beforeEach(() => {
        fixture = TestBed.createComponent(SearchComponent);
        searchcomponent = fixture.componentInstance;
        fixture.detectChanges();
    });
    it('should create', () => {
        expect(searchcomponent).toBeTruthy();
    });
    it('should set submitted to true', async(() => {
        searchcomponent.fetch;
        expect(searchcomponent.fetch).toBeTruthy();
    }));
    it('should contain div tag', () => {
        let element = fixture.debugElement.query(By.css('div'));
        expect(element).toBeTruthy();
    });
    it('empty bar when input is false', async(() => {
        expect(searchcomponent.generalSearch.valueOf).toBeTruthy();
       //expect(component.password.valid).toBeFalsy();
    }));

    // it('should handle call fetch method', () => {
    //     inject([routerService], (injectService: RouteService) => {
    //         expect(injectService).toBe(routerService);
    //     });
    //     foodDataCentralService.fetchFood;
    //     // expect(mySpy).toHaveBeenCalled();
    // });
});

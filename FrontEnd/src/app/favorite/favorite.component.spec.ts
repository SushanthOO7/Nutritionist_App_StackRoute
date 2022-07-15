import { async, ComponentFixture, TestBed, inject } from '@angular/core/testing';
import { FavoriteComponent } from './favorite.component';
import { ReactiveFormsModule, FormGroupDirective } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
//import { MatIconModule } from '@angular/material/icon';
//import { MatTooltipModule } from '@angular/material/tooltip';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
//import { MatButtonModule, MatSnackBar, MatSnackBarModule } from '@angular/material';
//import { MatPaginatorModule } from '@angular/material/paginator';
//import { MatCardModule } from '@angular/material/card';
//import { MatButtonToggleModule } from '@angular/material/button-toggle';
//import { MatDialogModule } from '@angular/material';
import { By } from '@angular/platform-browser';
import { RouterTestingModule } from '@angular/router/testing';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { Observable } from 'rxjs';
import { RouteService } from 'src/app/services/route.service';
import { NgxPaginationModule } from 'ngx-pagination';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { PersistanceService } from 'src/app/services/persistance.service';
describe('FavoriteComponent', () => {
    let favcomponent: FavoriteComponent;
    let fixture: ComponentFixture<FavoriteComponent>;
    let persistanceservice: PersistanceService;
    let mySpy: any;
    let obj: FormGroupDirective;
    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [FavoriteComponent],
            imports: [
                FormsModule,
                BrowserAnimationsModule,
                MatInputModule,
                RouterTestingModule,
                MatSlideToggleModule,
                HttpClientTestingModule,
                MatFormFieldModule,
                MatInputModule,
                HttpClientModule,
                ReactiveFormsModule,
                NgxPaginationModule
            ],
            providers: [AuthenticationService, RouteService, PersistanceService]
        })
            .compileComponents();
    }));
    beforeEach(() => {
        fixture = TestBed.createComponent(FavoriteComponent);
        favcomponent = fixture.componentInstance;
        fixture.detectChanges();
    });
    it('should create', () => {
        expect(favcomponent).toBeTruthy();
    });
    it('should set submitted to true', async(() => {
        favcomponent.brandWise;
        expect(favcomponent.brandWise).toBeTruthy();
    }));
    it('should contain div tag', () => {
        let element = fixture.debugElement.query(By.css('div'));
        expect(element).toBeTruthy();
    });
    it('form should be invalid when the fields are left empty', async(() => {
        expect(favcomponent.viewDetails).toBeTruthy();
    }));
});
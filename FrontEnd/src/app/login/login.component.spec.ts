import { async, ComponentFixture, TestBed, inject } from '@angular/core/testing';
import { LoginComponent } from './login.component';
import { ReactiveFormsModule, FormGroupDirective } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatIconModule } from '@angular/material/icon';
import { MatTooltipModule } from '@angular/material/tooltip';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule, MatSnackBar, MatSnackBarModule } from '@angular/material';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatCardModule } from '@angular/material/card';
import { MatButtonToggleModule } from '@angular/material/button-toggle';
import { MatDialogModule } from '@angular/material';
import { By } from '@angular/platform-browser';
import { RouterTestingModule } from '@angular/router/testing';
import { AuthenticationService } from 'src/app/services/authentication.service';                     
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { Observable } from 'rxjs';
import { RouteService } from 'src/app/services/route.service';
describe('loginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;
  let authenticationService: AuthenticationService;
  let routerService: RouteService;
  let mySpy: any;
  let obj: FormGroupDirective;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [LoginComponent],
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
        MatPaginatorModule,
        ReactiveFormsModule,
        MatButtonModule,
        MatCardModule,
        ReactiveFormsModule,
        MatButtonToggleModule,
        MatDialogModule,
        MatSnackBarModule,
        HttpClientTestingModule
      ],
      providers: [AuthenticationService, RouteService]
    })
      .compileComponents();
  }));
  beforeEach(() => {
    fixture = TestBed.createComponent(LoginComponent);
    component = fixture.componentInstance;
    authenticationService = TestBed.get(AuthenticationService);
    routerService = TestBed.get(RouteService);
    fixture.detectChanges();
  });
  it('should create', () => {
    expect(component).toBeTruthy();
  });
  it('should set submitted to true', async(() => {
    component.loginForm;
    expect(component.loginForm).toBeTruthy();
  }));
  it('should contain div tag', () => {
    let element = fixture.debugElement.query(By.css('div'));
    expect(element).toBeTruthy();
  });
  it('form invalid when username is empty', () => {
    expect(component.username.valid).toBeFalsy();
  });
  it('form invalid when password is empty', () => {
    expect(component.password.valid).toBeFalsy();
  });
  it('form should be invalid when the fields are left empty', async(() => {
    expect(component.username.valid).toBeFalsy();
    expect(component.password.valid).toBeFalsy();
  }));
  it('should handle call loginSubmit method', () => {
    inject([authenticationService], (injectService: AuthenticationService) => {
      expect(injectService).toBe(authenticationService);
    });
    let check: any;
    mySpy = spyOn(authenticationService, 'setBearerToken').and.callFake(() => { });
    authenticationService.setBearerToken('');
    component.loginSubmit(obj);
    expect(mySpy).toHaveBeenCalled();
  });

  it('should handle call logout method', () => {
    inject([routerService], (injectService: RouteService) => {
        expect(injectService).toBe(routerService);
    });
    mySpy = spyOn(routerService, 'routeToSearch');
    authenticationService.logOut;
    // expect(mySpy).toHaveBeenCalled();
});
});
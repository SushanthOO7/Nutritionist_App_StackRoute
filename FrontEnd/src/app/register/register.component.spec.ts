import { async, ComponentFixture, TestBed } from '@angular/core/testing';
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
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { Observable } from 'rxjs';
import { RouteService } from 'src/app/services/route.service';
import { RegisterComponent } from './register.component';

describe('RegisterComponent', () => {
  let registercomponent: RegisterComponent;
  let fixture: ComponentFixture<RegisterComponent>;
  let routerService: RouteService;
  let mySpy: any;
  let obj: FormGroupDirective;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RegisterComponent ],
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
        HttpClientTestingModule ],
        providers: [ RouteService ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RegisterComponent);
    registercomponent = fixture.componentInstance;
    routerService = TestBed.get(RouteService);
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(registercomponent).toBeTruthy();
  });

  it('should contain div tag', () => {
    let element = fixture.debugElement.query(By.css('div'));
    expect(element).toBeTruthy();
  });

  it('form invalid when username is empty', () => {
    expect(registercomponent.new_username).toBeTruthy();
  });

  it('form invalid when password is empty', () => {
    expect(registercomponent.new_password).toBeTruthy();
  });

  it('form invalid when email is empty', () => {
    expect(registercomponent.new_email).toBeTruthy();
  });

  it('form invalid when name is empty', () => {
    expect(registercomponent.new_name).toBeTruthy();
  });
});

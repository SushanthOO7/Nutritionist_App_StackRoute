import { async, ComponentFixture, TestBed, inject } from '@angular/core/testing';

import { HeaderComponent } from './header.component';
import { By } from '@angular/platform-browser';
import { RouterTestingModule } from '@angular/router/testing';
import { AuthenticationService } from 'src/app/services/authentication.service';                     
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { RouteService } from 'src/app/services/route.service';
describe('HeaderComponent', () => {
  let component: HeaderComponent;
  let fixture: ComponentFixture<HeaderComponent>;
  let routerService: RouteService;
  let authenticationService: AuthenticationService;
  let mySpy: any;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HeaderComponent ],
      imports: [ RouterTestingModule,
        HttpClientTestingModule ],
      providers: [ AuthenticationService, RouteService ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HeaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

});
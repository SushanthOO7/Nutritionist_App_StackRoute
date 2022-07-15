import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators, FormGroupDirective } from '@angular/forms';
import { AuthenticationService } from '../services/authentication.service';
import { RouteService } from '../services/route.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  username: FormControl;
  password: FormControl;
  hide = true;
  visibility_off =true;

  constructor(private authService: AuthenticationService, private routerService: RouteService) { }

  ngOnInit() {
    if(sessionStorage.getItem("logged") == "true")
      this.routerService.routeToHome();
    this.username = new FormControl('', [Validators.required]);
    this.password = new FormControl('', [Validators.required]);

    this.loginForm =  new FormGroup({
      username: this.username,
      password: this.password,
    });

  }

  loginSubmit(formDirective: FormGroupDirective) {
    console.log(this.loginForm.value);
    this.authService.authenticate(this.loginForm.value).subscribe(data => {
      console.log(data);
      // sessionStorage.setItem('username',this.loginForm.value.username);
      this.authService.setBearerToken(data["token"]);
      sessionStorage.setItem("logged","true");
      this.routerService.routeToHome();
    }, err => { 
      
      formDirective.resetForm();
      this.loginForm.reset();  
      alert("Login Failure");
    });
       
  }

  
}

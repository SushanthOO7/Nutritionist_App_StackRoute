import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators, FormGroupName, FormGroupDirective } from '@angular/forms';
import { AuthenticationService } from '../services/authentication.service';
import { User } from '../classes/user';
import { concat, VirtualTimeScheduler } from 'rxjs';
import { RouteService } from '../services/route.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  new_user: User;
  registerForm : FormGroup;
  new_name: FormControl;
  new_email: FormControl;
  new_username: FormControl;
  new_password: FormControl;
  confirm_password: FormControl;


  constructor(private authService: AuthenticationService, private routerService: RouteService) {
    this.new_user = new User();
   }

  ngOnInit() {

    if(sessionStorage.getItem("logged") == "true")
      this.routerService.routeToHome();

    this.new_username =  new FormControl('', [Validators.required]);
    this.new_password = new FormControl('',[Validators.required, Validators.minLength(8)]);
    this.confirm_password = new FormControl('',[Validators.required, Validators.minLength(8)]);
    this.new_name = new FormControl('',[Validators.required]);
    this.new_email = new FormControl('',[Validators.email]);

    this.registerForm = new FormGroup({
      new_username: this.new_username,
      new_password: this.new_password,
      confirm_password: this.confirm_password,
      new_name: this.new_name,
      new_email: this.new_email
    })
  }


  registerSubmit(formDirective: FormGroupDirective) {
    console.log("entered");
    if(this.registerForm.value.new_password != this.registerForm.value.confirm_password) {
      this.new_password.reset();
      this.confirm_password.reset();
      alert("Passwords do not match");
    }
    else {
      console.log(this.registerForm.value);
      this.new_user.name = this.registerForm.value.new_name;
      this.new_user.username = this.registerForm.value.new_username;
      this.new_user.password = this.registerForm.value.new_password;
      this.new_user.email = this.registerForm.value.new_email;
      this.authService.register(this.new_user).subscribe(data => {
        alert("Registration Succesful!");
        this.routerService.routeToLogin();
      }, err => {
        formDirective.resetForm();
        this.registerForm.reset();
        if(err.status == 409)
          alert("Username Already exists");
        else
          alert("Registration failed");
      });
      
    }
  }

}

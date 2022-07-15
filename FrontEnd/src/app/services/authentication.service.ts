import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams, HttpRequest, HttpInterceptor, HttpHandler, HttpEvent } from '@angular/common/http';
import { User } from '../classes/user';
import { map } from 'rxjs/operators';
import { Observable, BehaviorSubject } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  isLoggedIn: boolean = false;
  private serverURL = "http://localhost:9095/user";

  constructor(private httpClient: HttpClient) { }

  register(user: User) {
     return this.httpClient.post(this.serverURL+"/register", user, {responseType:"text"});
  }

  authenticate (formdata) {
    let headers = new HttpHeaders({"Access-Control-Allow-Origin":"*"});
    let params =  new HttpParams()
      .set('username',formdata.username)
      .set('password',formdata.password);
    return this.httpClient.post(this.serverURL+"/login",null,({params:params, headers: headers}));
    // this.httpClient.get()
  }

  logOut() {
    return this.setBearerToken("");
  }

  setBearerToken(token: string) {
    sessionStorage.setItem('authToken', token);
  }

  getBearerToken() {
    return sessionStorage.getItem('authToken');
  }
  isUserLoggedIn() {
    if(sessionStorage.getItem("logged")==null)
      return false;
    else  
      return true;
  }

  isUserAuthenticated(){
    // console.log(this.getBearerToken());
    return this.httpClient.get('http://localhost:8001/api/v1/food/isAuthenticated')
    .pipe(map(response => response["isAuthenticated"])).toPromise();
  }

}




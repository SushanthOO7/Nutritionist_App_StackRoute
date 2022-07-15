import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';
import { AuthenticationService } from './services/authentication.service';
import { Observable } from 'rxjs';

@Injectable()
export class TokenIterceptor implements HttpInterceptor{
    constructor(public auth: AuthenticationService) {}
    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
      // console.log(request);
      // console.log("---------------");
      request = request.clone({ 
        headers: request.headers.set('Authorization', 'Bearer ' + this.auth.getBearerToken()).set("Access-Control-Allow-Origin","*") 
      });
      // console.log(request);
      return next.handle(request);
    }
  }

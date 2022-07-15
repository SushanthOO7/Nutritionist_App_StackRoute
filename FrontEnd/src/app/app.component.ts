import { Component } from '@angular/core';
import { Router, NavigationStart } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  // showHead: boolean  = false;
  
  title = 'Nutritionist';

  constructor(public router: Router) {
    // route.events.forEach(event => {
    //   if(event instanceof NavigationStart) {
    //     if(event['url'] == '/register') 
    //       this.showHead = false;
        
    //   }
    //   else
    //     this.showHead = true;
    // })
  }
}

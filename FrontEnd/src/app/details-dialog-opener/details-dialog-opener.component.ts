import { Component, OnInit } from '@angular/core';
import { RouteService } from '../services/route.service';
import { MatDialog } from '@angular/material/dialog';
import { DetailsViewDialogComponent } from '../details-view-dialog/details-view-dialog.component';
import { PersistanceService } from '../services/persistance.service';

@Component({
  selector: 'app-details-dialog-opener',
  templateUrl: './details-dialog-opener.component.html',
  styleUrls: ['./details-dialog-opener.component.css']
})
export class DetailsDialogOpenerComponent implements OnInit {

  constructor(public dialog: MatDialog, public routerservice: RouteService, public persistService: PersistanceService) { 
    this.dialog.open(DetailsViewDialogComponent, {
      data: {
        fdcId: this.persistService.selectedFdcId
      }
    }).afterClosed().subscribe( result => {
      this.routerservice.routeBack();
      // if (result === "selected")
      //   this.routerservice.routeToFavorite();
      // else
      console.log("closed");
        // this.routerservice.routeBack();
    });
  }

  ngOnInit() {
  }

}

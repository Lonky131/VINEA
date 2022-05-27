import { Component, Inject, OnInit } from "@angular/core";
import { MatDialogRef, MAT_DIALOG_DATA } from "@angular/material/dialog";
import { winery } from "src/app/classes/winery";
import { WineriesService } from "src/app/services/wineries.service";
import { category } from "../../classes/category";
import { DialogOverviewExampleDialog } from "../dialogYesNo/dialog-overview-example-dialog";

export interface DialogData {
  name : string;
  productionYear : number;
  alcoholPercentage : number;
  volume : number;
  price : number;
  pictureUrl : string;
  wineryId : number;
  categories : category[];
}

@Component({
  selector: 'dialog-add-wine',
  templateUrl: 'dialogAddWine.html',
})
export class DialogAddWine implements OnInit {
  public wineries : Array<winery>;
  constructor(
    public dialogRef: MatDialogRef<DialogAddWine>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData,
    public wineriesService : WineriesService
  ) {}

  ngOnInit(): void {
    this.wineriesService.getAllWineries().subscribe((res) => {
      this.wineries = res;
    },
    error => {
      console.log(error);
    })

  }

  onNoClick(): void {
    this.dialogRef.close();
  }
  onYesClick() : void {
    this.dialogRef.close(this.data);
  }
}

import { Component, Inject, OnInit } from "@angular/core";
import { MatDialogRef, MAT_DIALOG_DATA } from "@angular/material/dialog";
import { wine } from "src/app/classes/wine";
import { winery } from "src/app/classes/winery";
import { WineriesService } from "src/app/services/wineries.service";
import { category } from "../../classes/category";
import { DialogOverviewExampleDialog } from "../dialogYesNo/dialog-overview-example-dialog";

export interface DialogData {
  wine : wine;
}

@Component({
  selector: 'dialog-edit-wine',
  templateUrl: 'dialogEditWine.html',
})
export class DialogEditWine implements OnInit {
  public wineries : Array<winery>;
  constructor(
    public dialogRef: MatDialogRef<DialogEditWine>,
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

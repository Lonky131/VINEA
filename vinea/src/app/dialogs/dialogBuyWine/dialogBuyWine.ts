import { Component, Inject, OnInit } from "@angular/core";
import { MatDialogRef, MAT_DIALOG_DATA } from "@angular/material/dialog";
import { buyForm } from "src/app/classes/buyForm";
import { wine } from "src/app/classes/wine";
import { winery } from "src/app/classes/winery";
import { WineriesService } from "src/app/services/wineries.service";
import { DialogOverviewExampleDialog } from "../dialogYesNo/dialog-overview-example-dialog";

export interface DialogData {
  buyForm : buyForm;
}

@Component({
  selector: 'dialog-buy-wine',
  templateUrl: 'dialogBuyWine.html',
})
export class DialogBuyWine implements OnInit {
  constructor(
    public dialogRef: MatDialogRef<DialogBuyWine>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData,
  ) {}

  ngOnInit(): void {
  }

  onNoClick(): void {
    this.dialogRef.close();
  }
  onYesClick() : void {
    this.dialogRef.close(this.data);
  }
}

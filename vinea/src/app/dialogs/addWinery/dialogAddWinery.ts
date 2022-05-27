import { Component, Inject, OnInit } from "@angular/core";
import { MatDialogRef, MAT_DIALOG_DATA } from "@angular/material/dialog";
import { WineriesService } from "src/app/services/wineries.service";
import { region } from "src/app/classes/region";


export interface DialogData {
  name : string;
  foundingYear : number;
  regionId : number;
}

@Component({
  selector: 'dialog-add-winery',
  templateUrl: 'dialogAddWinery.html',
})
export class DialogAddWinery implements OnInit{
  public regions : Array<region>;
  constructor(
    public dialogRef: MatDialogRef<DialogAddWinery>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData,
    public wineriesService : WineriesService
  ) {}

    ngOnInit() : void {
      this.wineriesService.getAllRegions().subscribe((res) => {
        this.regions = res;
        console.log(this.regions);
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

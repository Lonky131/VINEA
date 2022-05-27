import { Component, Inject } from "@angular/core";
import { MatDialogRef, MAT_DIALOG_DATA } from "@angular/material/dialog";
import { category } from "../../classes/category";
import { DialogOverviewExampleDialog } from "../dialogYesNo/dialog-overview-example-dialog";

export interface DialogData {
  categories : category[];
  selectedCategory : category;
  newValue : string;
}

@Component({
  selector: 'dialog-add-category',
  templateUrl: 'dialog-add-category.html',
})
export class DialogAddCategory {
  public choosenCategory : category;
  constructor(
    public dialogRef: MatDialogRef<DialogAddCategory>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData,
  ) {}

  onNoClick(): void {
    this.dialogRef.close();
  }
  onYesClick() : void {
    this.dialogRef.close(this.data);
  }
}

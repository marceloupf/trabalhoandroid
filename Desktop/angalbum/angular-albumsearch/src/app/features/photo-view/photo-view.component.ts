import { Component, Inject } from "@angular/core";

import { MAT_DIALOG_DATA, MatDialogRef } from "@angular/material/dialog";

@Component({
 selector: "app-photo-view",
 templateUrl: "./photo-view.component.html",
 styleUrls: ["./photo-view.component.scss"]
})
export class PhotoViewComponent {
 constructor(
 private dialogRef: MatDialogRef<PhotoViewComponent>,
 @Inject(MAT_DIALOG_DATA) public data: any
 ) {}
 close() {
 this.dialogRef.close();
 }
}

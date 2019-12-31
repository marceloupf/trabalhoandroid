import { Component, Input } from "@angular/core";
import { Photo } from "../../classes/Photo";
import { MatDialog, MatDialogConfig } from "@angular/material";
import { PhotoViewComponent } from "../photo-view/photo-view.component";
@Component({
 selector: "app-photo-list",
 templateUrl: "./photo-list.component.html",
 styleUrls: ["./photo-list.component.scss"]
})
export class PhotoListComponent {
 @Input() photos: Photo[];
 constructor(private dialog: MatDialog) {}
 openDialog(e, photo) {
 e.preventDefault();
 const dialogConfig = new MatDialogConfig();
 dialogConfig.disableClose = true;
 dialogConfig.autoFocus = true;
 dialogConfig.data = {
 photo
 };
 this.dialog.open(PhotoViewComponent, dialogConfig);
 }
}

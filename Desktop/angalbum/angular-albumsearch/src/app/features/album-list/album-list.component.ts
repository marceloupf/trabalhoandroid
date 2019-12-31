import { Component, Input } from "@angular/core";
import { Album } from "../../classes/Album";
@Component({
 selector: "app-album-list",
 templateUrl: "./album-list.component.html",
 styleUrls: ["./album-list.component.scss"]
})
export class AlbumListComponent {
 @Input() albums: Album[];
}
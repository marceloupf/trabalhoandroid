import { Component, Input } from "@angular/core";
import { Album } from "../../classes/Album";
@Component({
 selector: "app-album-box",
 templateUrl: "./album-box.component.html",
 styleUrls: ["./album-box.component.scss"]
})
export class AlbumBoxComponent {
 @Input() album: Album;
}
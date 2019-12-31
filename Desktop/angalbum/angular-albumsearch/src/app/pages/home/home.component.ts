import { Component, OnInit } from "@angular/core";
import { AlbumsService } from "../../services/albums.service"; // 1
import { Album } from "../../classes/Album"; // 2
@Component({
 selector: "app-home",
 templateUrl: "./home.component.html",
 styleUrls: ["./home.component.scss"]
})
export class HomeComponent implements OnInit {
 albums: Album[] = [];
 filteredAlbuns: Album[] = this.albums;
 filter: String = "";
 constructor(private albumsService: AlbumsService) {}

ngOnInit() {
 this.albumsService.getAlbums().then(res => {
 console.log(res);
 this.albums = this.filteredAlbuns = res;
 });
 }
 filterAlbuns = (word: string) => {
 this.filteredAlbuns = this.albums.filter(album =>
 album.title.includes(word)
 );
 };
}
import { Component, OnInit } from "@angular/core";
import { ActivatedRoute } from "@angular/router";

import { AlbumsService } from "../../services/albums.service";
import { Album } from "../../classes/Album";
import { Photo } from "../../classes/Photo";
import { User } from "../../classes/User";

@Component({
 selector: "app-detail",
 templateUrl: "./detail.component.html",
 styleUrls: ["./detail.component.scss"]
})
export class DetailComponent implements OnInit {
 albumId: Number;
 album: Album = new Album();
 user: User = new User();
 photos: Photo[] = [];
 constructor(
 private route: ActivatedRoute,
 private albumsService: AlbumsService
 ) {}
 ngOnInit() {
 this.albumId = this.route.snapshot.params.albumId;
 this.getData(this.albumId);
 }
 getData = async albumId => {
 this.album = (await this.albumsService.getAlbum(albumId))[0];
 this.user = (await this.albumsService.getUser(this.album.userId))[0];
 this.photos = await this.albumsService.getAlbumPhotos(albumId);
 };
}
import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Album } from "../classes/Album";
import { Photo } from "../classes/Photo";
import { User } from "../classes/User";
@Injectable({
 providedIn: "root"
})
export class AlbumsService {
 private _url: String = "https://jsonplaceholder.typicode.com";
 constructor(private http: HttpClient) {}
 getAlbums = () => this.http.get<Album[]>(`${this._url}/albums`).toPromise();
 getAlbum = albumId =>
 this.http.get<Album>(`${this._url}/albums?id=${albumId}`).toPromise();
 getUser = userId =>
 this.http.get<User>(`${this._url}/users?id=${userId}`).toPromise();
 getAlbumPhotos = albumId =>
 this.http
 .get<Photo[]>(`${this._url}/photos?albumId=${albumId}`)
 .toPromise();
}
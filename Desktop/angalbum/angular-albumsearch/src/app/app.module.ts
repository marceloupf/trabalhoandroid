import { BrowserModule } from "@angular/platform-browser";
import { NgModule } from "@angular/core";
import { HttpClientModule } from "@angular/common/http";
import { AppRoutingModule } from "./app-routing.module";
import { AppComponent } from "./app.component";
import { HomeComponent } from "./pages/home/home.component";
import { DetailComponent } from "./pages/detail/detail.component";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { HeaderComponent } from "./features/header/header.component";
import { AlbumListComponent } from "./features/album-list/album-list.component";
import { AlbumBoxComponent } from "./features/album-box/album-box.component";
import { UserInfoComponent } from "./features/user-info/user-info.component";
import { PhotoListComponent } from "./features/photo-list/photo-list.component";
import { PhotoViewComponent } from "./features/photo-view/photo-view.component";
import { SearchComponent } from "./features/search/search.component";

// Angular Material
import { MatIconModule } from "@angular/material/icon";
import { MatButtonModule } from "@angular/material/button";
import { MatToolbarModule } from "@angular/material/toolbar";
import { MatListModule } from "@angular/material/list";
import { MatCardModule } from "@angular/material/card";
import { MatDialogModule } from "@angular/material/dialog";
import { MatInputModule } from "@angular/material";
import { MatFormFieldModule } from "@angular/material/form-field";

@NgModule({
 declarations: [
 AppComponent,
 HomeComponent,
 DetailComponent,
 HeaderComponent,
 AlbumListComponent,
 AlbumBoxComponent,
 UserInfoComponent,
 PhotoListComponent,
 PhotoViewComponent,
 SearchComponent
 ],
 imports: [
 BrowserModule,
 AppRoutingModule,
 HttpClientModule,
 BrowserAnimationsModule,
 MatIconModule,
 MatButtonModule,
 MatToolbarModule,
 MatListModule,
 MatCardModule,
 MatDialogModule,
 MatInputModule,
 MatFormFieldModule
 ],
 providers: [],
 bootstrap: [AppComponent],
 entryComponents: [PhotoViewComponent]
})
export class AppModule {}

import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { AppRoutingModule } from "./app-routing.module";
import { HeaderComponent } from './element/header/header.component';
import { FooterComponent } from './element/footer/footer.component';
import { HomeComponent } from './home/home.component';
import { SharedModule } from './shared.module';
import { AboutComponent } from './about/about.component';
import { UserModule } from './user/user.module';
import { MapComponent } from './element/map/map.component';
import { MapService } from './service/map.service';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    HomeComponent,
    MapComponent,
    AboutComponent
  ],
  imports: [
    BrowserModule,
    SharedModule,
    AppRoutingModule,
    UserModule
  ],
  providers: [MapService],
  bootstrap: [AppComponent]
})
export class AppModule { }

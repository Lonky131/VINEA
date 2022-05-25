import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {HttpClientModule } from '@angular/common/http';
import { FormsModule,ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';


// My components
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './components/header/header.component';
import { HomeComponent } from './components/home/home.component';
import { WineComponent } from './components/wine/wine.component';
import { WineriesComponent } from './components/wineries/wineries.component';
import { NotfoundComponent } from './components/notfound/notfound.component';

//Material modules
import { FlexLayoutModule } from '@angular/flex-layout'
import { MatButtonModule } from  '@angular/material/button';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatListModule } from '@angular/material/list';
import { MatInputModule } from '@angular/material/input';
import { MatCardModule } from '@angular/material/card';
import { MatMenuModule} from '@angular/material/menu';
import {MatPaginatorModule } from '@angular/material/paginator';
import {MatTableModule } from '@angular/material/table';
import {MatSortModule} from '@angular/material/sort';

//Services
import {WineService} from './services/wine.service'

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    HomeComponent,
    WineComponent,
    WineriesComponent,
    NotfoundComponent
  ],
  imports: [
    HttpClientModule,
    BrowserAnimationsModule,
    FormsModule,
    MatPaginatorModule,
    MatTableModule,
    MatSortModule,
    ReactiveFormsModule,
    MatButtonModule,
    MatToolbarModule,
    MatIconModule,
    MatSidenavModule,
    MatListModule,
    MatInputModule,
    MatCardModule,
    MatMenuModule,
    FlexLayoutModule,
    AppRoutingModule
  ],
  providers: [WineService],
  bootstrap: [AppComponent]
})
export class AppModule { }

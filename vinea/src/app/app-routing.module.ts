import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { NotfoundComponent } from './components/notfound/notfound.component'
import { WineComponent } from './components/wine/wine.component';
import { WineriesComponent } from './components/wineries/wineries.component';
import { WineDetailsComponent } from './components/wine-details/wine-details.component';

const routes: Routes = [
  {
    path: 'home',
    component: HomeComponent

  },
  {
    path: 'wine',
    children: [
      {
        path: '',
        component: WineComponent
      },
      {
        path: ':id',
        component: WineDetailsComponent
      }
    ]
  },
  {
    path: 'wineries',
    component: WineriesComponent
  }
  ,
  {
    path: '',
    pathMatch: 'full',
    redirectTo: 'wine'
  },
  {
    path: '**',
    component: NotfoundComponent
  }



];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

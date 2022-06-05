import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { NotfoundComponent } from './components/notfound/notfound.component'
import { WineComponent } from './components/wine/wine.component';
import { WineriesComponent } from './components/wineries/wineries.component';
import { WineDetailsComponent } from './components/wine-details/wine-details.component';
import { AdminComponent } from './components/admin/admin.component';
import { PeroComponent } from './components/pero/pero.component';

const routes: Routes = [
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
    path: 'admin',
    component: AdminComponent
  },
  {
    path: 'pero',
    component: PeroComponent
  },
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

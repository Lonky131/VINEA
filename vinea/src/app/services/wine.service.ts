import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { wine } from '../classes/wine'
import { wineCategory } from '../classes/wine-category';
import { category } from '../classes/category';
import { buyForm, orderForm } from '../classes/buyForm';

@Injectable({
  providedIn: 'root'
})
export class WineService {
  constructor(
    private http: HttpClient
  ) { }


    getAllWines (): Observable<wine[]>{
      return this.http.get<wine[]>("/api/wine", {responseType: 'json'});
    }

    getWineById(id : number) : Observable<wine>{
      return this.http.get<wine>(`/api/wine/${id}`, {responseType : 'json'});
    }

    getWineCategoriesById(id: number) : Observable<wineCategory[]>{
      return this.http.get<wineCategory[]>(`/api/wine-category/${id}`, {responseType: 'json'});
    }

    addWine(name : string, productionYear : number, alcoholPercentage : number, volume : number, price : number, pictureUrl : string, wineryId : number, categories : category[]){
      return this.http.post(`/api/wine`, {name, productionYear, alcoholPercentage, volume, price, pictureUrl, wineryId, categories});
    }

    editWine(id: number, name : string, productionYear : number, alcoholPercentage : number, volume : number, price : number, pictureUrl : string, wineryId : number){
      return this.http.put(`/api/wine/${id}`, {name, productionYear, alcoholPercentage, volume, price, pictureUrl, wineryId});
    }

    deleteWine(id : number){
      return this.http.delete(`/api/wine/${id}`);
    }

    addCategoryToWine(id : number, categoryId :number, value : string){
      let urlSearchParams = new URLSearchParams();
      urlSearchParams.append('categoryId', categoryId.toString());
      urlSearchParams.append('value', value);
      return this.http.post(`api/wine-category/${id}?${urlSearchParams.toString()}`, null);
    }

    deleteCategoryOfWine(id: number, wineCategoryId: number){
      let urlSearchParams = new URLSearchParams();
      urlSearchParams.append('wineCategoryId', wineCategoryId.toString());
      return this.http.delete(`api/wine-category/${id}?${urlSearchParams.toString()}`);
    }

    buyWine(orderForm: orderForm){
      return this.http.post(`http://localhost:8080/engine-rest/process-definition/key/AgeCheck/start`, orderForm);
    }
}

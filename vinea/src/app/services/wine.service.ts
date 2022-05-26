import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable, Subject, throwError} from 'rxjs';
import {catchError, retry} from 'rxjs/operators';
import {HttpParams} from '@angular/common/http';
import {wine} from '../classes/wine'
import { wineCategory } from '../classes/wine-category';


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
      return this.http.get<wine>(`/api/wine/${id}`, {
        responseType : 'json'
      })
    }

    getWineCategoriesById(id: number) : Observable<wineCategory[]>{
      return this.http.get<wineCategory[]>(`/api/wine-category/${id}`, {responseType: 'json'});
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


}

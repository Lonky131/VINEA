import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable, Subject, throwError} from 'rxjs';
import {winery} from '../classes/winery';
import { region } from '../classes/region';

@Injectable({
  providedIn: 'root'
})
export class WineriesService {
  constructor(
    private http: HttpClient
  ) { }

    getAllWineries (): Observable<winery[]>{
      return this.http.get<winery[]>("/api/winery", {responseType: 'json'});
    }

    deleteWineryById(id:number){
      return this.http.delete(`/api/winery/${id}`);
    }

    addWinery(name: string, foundingYear: number, regionId : number){
      return this.http.post(`/api/winery`, {name, foundingYear, regionId});
    }

    getAllRegions() : Observable<region[]>{
      return this.http.get<region[]>(`/api/region`, {responseType : 'json'});
    }

}

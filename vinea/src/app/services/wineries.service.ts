import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable, Subject, throwError} from 'rxjs';
import {catchError, retry} from 'rxjs/operators';
import {HttpParams} from '@angular/common/http';
import {winery} from '../classes/winery';

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

}

import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable, Subject, throwError} from 'rxjs';
import {catchError, retry} from 'rxjs/operators';
import {HttpParams} from '@angular/common/http';
import {wine} from '../classes/wine'


@Injectable({
  providedIn: 'root'
})
export class WineService {

  constructor(
    private http: HttpClient,
    //private httpParams: HttpParams

  ) { }
    getAllWines (): Observable<wine[]>{

      return this.http.get<wine[]>("/api/wine", {responseType: 'json'});

    }



}

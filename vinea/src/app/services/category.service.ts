import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable, Subject, throwError} from 'rxjs';
import {catchError, retry} from 'rxjs/operators';
import { category } from '../classes/category';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  constructor(
    private http: HttpClient
  ) { }
    getAllCategories(): Observable<category[]>{
      return this.http.get<category[]>("/api/category", {responseType: 'json'});
    }

    getCategoryById(id : number) : Observable<category>{
      return this.http.get<category>(`/api/category/${id}`, {
        responseType : 'json'
      });
    }
}

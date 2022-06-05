import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { order } from '../classes/order';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  constructor(
    private http: HttpClient
  ) { }

    getAllOrders(): Observable<order[]> {
      return this.http.get<order[]>("/api/order", {responseType : 'json'});
    }

    getAllOrdersByIdNumber(idNumber: string) : Observable<order[]> {
      return this.http.get<order[]>(`/api/order/${idNumber}`, {responseType : 'json'});
    }

    changeOrderStatus(orderId : number, status: string) {
      return this.http.put(`/api/order/${orderId})`, {status});
    }
}

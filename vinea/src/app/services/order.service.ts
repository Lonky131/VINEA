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

    // getAllOrders(): Observable<order[]> {
    //   return this.http.get<order[]>("/api/order", {responseType : 'json'});
    // }

    getAllOrders(): any{
      return this.http.get<any[]>("http:localhost:8080/engine-rest/task?processDefinitionKey=AgeCheck", {responseType : 'json'});
    }

    getAllOrdersByIdNumber(idNumber: string) : Observable<order[]> {
      return this.http.get<order[]>(`/api/order/${idNumber}`, {responseType : 'json'});
    }

    completeOrder(taskId : number, status: string) {
      return this.http.put(`http:localhost:8080/engine-rest/task/${taskId}/complete`, {variables: {
        orderStatus: {
          value: status
        }
      }});
    }
}

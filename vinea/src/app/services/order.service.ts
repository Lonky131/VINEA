import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { approval } from '../classes/approval';
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

    getAllTasks(): any{
      return this.http.get<any[]>("http://localhost:8080/engine-rest/task?processDefinitionKey=AgeCheck&name=Check user ID card for age", {responseType : 'json'});
    }

    getTaskFirstname(id: string){
      return this.http.get<any>(`http://localhost:8080/engine-rest/task/${id}/variables/firstName`);
    }
    getTaskLastname(id: string){
      return this.http.get<any>(`http://localhost:8080/engine-rest/task/${id}/variables/lastName`);

    }
    getTaskIdCardNumber(id: string){
      return this.http.get<any>(`http://localhost:8080/engine-rest/task/${id}/variables/idCardNumber`);
    }


    getAllOrdersByIdNumber(idNumber: string) : Observable<order[]> {
      return this.http.get<order[]>(`/api/order/${idNumber}`, {responseType : 'json'});
    }

    completeOrder(taskId : string, status : boolean) {
      return this.http.post(`http://localhost:8080/engine-rest/task/${taskId}/complete`, {variables: {
        UserOldEnough: {
          value: status
        }
      }});
    }
}

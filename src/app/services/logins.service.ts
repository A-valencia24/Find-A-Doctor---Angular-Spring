import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { Doctor } from '../models/doctor';

const doctorURL = "http://localhost:5050/doctor"
@Injectable({
  providedIn: 'root'
})
export class LoginsService {

  httpOptions = {
    headers : new HttpHeaders({
        'Content-Type':'application/json'
      })
    }

  constructor(public httpClient:HttpClient) { }

  // need to get username/password confirm (maybe Id and last name?)
  // confirm doctor exists
  // loginStatus(doctorId: number) : Observable<Doctor> {
  //   return this.httpClient.get<Doctor>(`${doctorURL}/${doctorId}`)
  //   .pipe(
  //     retry(1),
  //     catchError(this.errorHandler)
  //   );
  // }

  errorHandler(error: { error: { message: string; }; status: any; message: any; }) {
    let errorMessage = '';
    if (error.error instanceof ErrorEvent) {
      // Get client-side error
      errorMessage = error.error.message;
    } else {
      // Get server-side error
      errorMessage = `Some Error Happened\n Error Details \nError Code: ${error.status}\nMessage: ${error.message}`;
    }
    console.log(errorMessage);
    return throwError(errorMessage);
  }

}

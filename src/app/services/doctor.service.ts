import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, Injectable, OnInit } from '@angular/core';
import { Doctor } from '../models/doctor';
import { catchError,Observable, retry, throwError } from 'rxjs';


const doctorURL = "http://localhost:5050/doctor" 
@Injectable({
    providedIn:'root'
})

export class DoctorService {
    
    httpOptions = {
        headers : new HttpHeaders({
            'Content-Type':'application/json'
          })
        }

          constructor(public httpClient: HttpClient) { }

          //Getting all the doctors
   getDoctors(): Observable<Doctor[]> {
    return this.httpClient.get<Doctor[]>(doctorURL)
    .pipe(
      retry(1),
      catchError(this.errorHandler)
    );
  }

  //Getting a single doctors
  //"http://localhost:3000/doctor/99
  getDoctor(doctorId: number): Observable<Doctor> {
    return this.httpClient.get<Doctor>(`${doctorURL}/${doctorId}`)
    .pipe(
      retry(1),
      catchError(this.errorHandler)
    );
  }

  //Deleting a single doctor
  //"http://localhost:3000/doctor/99 - DELETE
  deleteDoctor(doctorId: number): Observable<Doctor> {
    return this.httpClient.delete<Doctor>(`${doctorURL}/${doctorId}`)
    .pipe(
      retry(1),
      catchError(this.errorHandler)
    );
  }

  //saving a single doctor
  //"http://localhost:3000/doctor
  saveDoctor(doctor:Doctor): Observable<Doctor> {
    return this.httpClient.post<Doctor>(doctorURL,doctor,this.httpOptions)
    .pipe(
      retry(1),
      catchError(this.errorHandler)
    );
  }

    //edit/update a single product
    //Please complete this 
  //localhost:3000/product/71 - PUT and in body we have to pass product object
  updateDoctor(doctorId:number,doctor:Doctor): Observable<Doctor> {
    return this.httpClient.put<Doctor>(`${doctorURL}/${doctorId}`,doctor,this.httpOptions)
    .pipe(
      retry(1),
      catchError(this.errorHandler)
    );
  }



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

import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, retry, throwError } from 'rxjs';
import { Doctor } from '../models/doctor';

const doctorURL ="http://localhost:5050/doctor"

@Injectable({
  providedIn: 'root'
})
export class DoctorService {

  httpOptions = {
    headers : new HttpHeaders({
        'Content-Type':'application/json'
      })
    }

      constructor(public httpClient: HttpClient) { }

      //Getting all the products
getDoctors(): Observable<Doctor[]> {
return this.httpClient.get<Doctor[]>(doctorURL)
.pipe(
  retry(1),
  catchError(this.errorHandler)
);
}

//Getting a single product
//"http://localhost:3000/product/99
getDoctor(doctorId: number): Observable<Doctor> {
return this.httpClient.get<Doctor>(`${doctorURL}/${doctorId}`)
.pipe(
  retry(1),
  catchError(this.errorHandler)
);
}

//Deleting a single product
//"http://localhost:3000/product/99 - DELETE
deleteDoctor(doctorId: number): Observable<Doctor> {
return this.httpClient.delete<Doctor>(`${doctorURL}/${doctorId}`)
.pipe(
  retry(1),
  catchError(this.errorHandler)
);
}

//saving a single product
//"http://localhost:3000/product
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
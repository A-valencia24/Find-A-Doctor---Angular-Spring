import { CurrencyPipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Patient } from 'src/app/models/patient';
import { PatientService } from 'src/app/services/patient.service';

@Component({
  selector: 'app-patient-list',
  templateUrl: './patient-list.component.html',
  styleUrls: ['./patient-list.component.css']
})
export class PatientListComponent implements OnInit {

  patients : Patient[] =[];

  currentDate = new Date();
  

  constructor(public patientService: PatientService) { 

    this.patientService.getPatients().subscribe((data:any)  => {
      this.patients = data; })
    /*
    this.patients = [
      {'patient_id': 1, firstname: 'Joe', lastname : 'Miller', email : 'jmiller@gmail.com' ,
       password:'111111', phone: 5401122684, sex : 'Male',   birthdate: '8/18/1964', address: '560 Maywood Hill',
      city : 'Richmond', state :'Virginia', zip_code : 77257, date_created: this.currentDate},

      {'patient_id': 2, firstname: 'Mary', lastname : 'Smith', email : 'rsmith@gmail.com' ,
      password:'111111', phone: 7178465448, sex : 'Female',   birthdate: '11/9/1984', address: '347 Pawling Drive',
      city : 'Chicago', state :'Illinois', zip_code : 30648, date_created: this.currentDate},

      {'patient_id': 3, firstname: 'Harry', lastname : 'Holland', email : 'hhollandh@gmail.com' ,
      password:'111111', phone: 9824344756, sex : 'Female',   birthdate: '3/29/1938', address: '07 East Road',
      city : 'Hayward', state :'California', zip_code : 66967, date_created: this.currentDate},

      {'patient_id': 4, firstname: 'Tiffny', lastname : 'Smith', email : 'tsmith@gmail.com' ,
      password:'111111', phone: 7178465449, sex : 'Female',   birthdate: '11/19/1984', address: '281 Pawling Drive',
      city : 'Chicago', state :'Illinois', zip_code : 30648, date_created: this.currentDate},

      {'patient_id': 5, firstname: 'Rose', lastname : 'Johnsona', email : 'rjohnsona@gmail.com' ,
       password:'111111', phone: 5401122686, sex : 'Feale',   birthdate: '8/18/1980', address: '562 Maywood Hill',
      city : 'Richmond', state :'Virginia', zip_code : 77257, date_created: this.currentDate},
    ]
    
*/
  
    
  }

  ngOnInit(): void {
  }

}

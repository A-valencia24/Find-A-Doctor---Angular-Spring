import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { RouterEvent } from '@angular/router';
import { Patient } from 'src/app/models/patient';
import { PatientService } from 'src/app/services/patient.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-patient-add',
  templateUrl: './patient-add.component.html',
  styleUrls: ['./patient-add.component.css']
})
export class PatientAddComponent implements OnInit {
  
  public patientForm!: FormGroup;
  currentDate = new Date();
  patients: Patient[] = [];
  public errorMessage!:string;
  public successMessage!:string;

  public sexs = [{
    id: 'male', text: 'Male', value: true
  }, {
    id: 'female', text: 'Female', value: false
  }];

  //public formBuilder !: FormBuilder;

  /*constructor(public formBuilder: FormBuilder) { } */

  
  
  constructor(public formBuilder:FormBuilder, public patientService: PatientService, public router: Router) {

  }
 
/*
  constructor(public patientService:PatientService){
    this.patientService.getPatients().subscribe((data:any) => {
      this.patients = data;

    })  
    }
  */

  ngOnInit(): void {
   

   
    this.patientForm = this.formBuilder.group({
      patientId: -1,
     // patientId: ['', Validators.required],
      //patientId: [''],
      firstname: ['', Validators.required],
      lastname: ['', Validators.required],
      email: [''],
      password: ['', [Validators.required, Validators.minLength(6)]] ,
      phone: [''],
      sex: ['', Validators.required],
      birthdate: ['', [Validators.required, Validators.minLength(10)]] ,
      address:  ['', Validators.required],
      city:['', Validators.required],
      state:[''],
      zip_code :[''],
      date_creted: this.currentDate
     // date_created:  ['', [Validators.required, Validators.minLength(10)]] 
    })
    
  }

  displayPatientInfo(){
    console.log(this.patientForm.value);
    this.patientService.savePatient(this.patientForm.value).subscribe((data:any) => {
     
      this.successMessage = 'Patient with patient id '+this.patientForm.value.patientId+ ' saved successfully';
      //redirect to productlist component
    },
    //err =>this.errorMessage = err)
    err => this.router.navigate(['patientList']))
    
  }

  PatientInfo(){

    
  }
  
/*
 ngOnInit(): void {
  }
  refreshPatients() {
    this.patientService.getPatients().subscribe((data:any) => {
      this.patients = data;
    },err =>this.errorMessage = err)
  }

  deletePatient(patientId:number) {
    this.patientService.deletePatient(patientId).subscribe((data:any) =>{
      this.successMessage = 'Patient with patient Id ' + patientId + ' deleted successfully ';
    }, err => {
      //this.errorMessage = err;
      this.refreshPatients()
    
    })
  }
*/

}

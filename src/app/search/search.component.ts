import { Component, OnInit } from '@angular/core';
import { Doctor } from '../models/doctor';
import { DoctorService } from '../services/doctor.service';
import { Router } from '@angular/router';
import { FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {
  doctors: Doctor[] = [];
  public errorMessage!:string;
  public successMessage!:string;
  doctorForm!:FormGroup;
  

  //DI (Dependency Injection)
  constructor(public formBuilder:FormBuilder, public doctorService:DoctorService,public router:Router) {
    this.doctorService.getDoctors().subscribe((data:any)  => {
      this.doctors = data;
    })
  }
  ngOnInit(): void { 
  }
  refreshDoctors() {
    this.doctorService.getDoctors().subscribe((data: any) => {
      this.doctors = data;
    },err =>this.errorMessage = err)
  }
  displayDoctorInfo() {
    console.log(this.doctorForm.value)
    this.doctorService.saveDoctor(this.doctorForm.value).subscribe((data:any)=> {
        this.successMessage = 'Doctor with doctor Id '+this.doctorForm.value.doctorId+ ' saved successfully';
        //redirect to productlist component
     }, err=> this.router.navigate(['doctors']) )
  }
}



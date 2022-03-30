import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { DoctorService } from '../services/doctor.service';

@Component({
  selector: 'app-appointments',
  templateUrl: './appointments.component.html',
  styleUrls: ['./appointments.component.css']
})
export class AppointmentsComponent implements OnInit {

  doctorForm!:FormGroup;
  successMessage!:string;


  constructor(public formBuilder:FormBuilder, public doctorService:DoctorService,public router:Router) { }

  ngOnInit(): void {
    this.doctorForm = new FormGroup({
      doctorId : new FormControl('',Validators.required),
      lastName : new FormControl('',[Validators.required,Validators.minLength(5)]),
      timeSlot : new FormControl('',[Validators.required,Validators.min(6)]),
      practice : new FormControl('',[Validators.required,Validators.min(1)]),
    })
  }
  displayDoctorInfo() {
    console.log(this.doctorForm.value)
    this.doctorService.saveDoctor(this.doctorForm.value).subscribe((data:any)=> {
        this.successMessage = 'Doctor with doctor Id '+this.doctorForm.value.doctorId+ ' saved successfully';
        //redirect to productlist component
     }, err=> this.router.navigate(['doctors']) )
  }

}

import { Component, OnInit } from '@angular/core';
import { Doctor } from 'src/app/models/doctor';
import { DoctorService } from 'src/app/services/doctor.service';

@Component({
  selector: 'app-doctors',
  templateUrl: './doctors.component.html',
  styleUrls: ['./doctors.component.css']
})
export class DoctorsComponent implements OnInit {

  doctors: Doctor[] = [];
  public errorMessage!:string;
  public successMessage!:string;
  

  //DI (Dependency Injection)
  constructor(public doctorService: DoctorService) {
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

  deleteDoctor(doctorId:number) {
    this.doctorService.deleteDoctor(doctorId).subscribe((data:any) =>{
      this.successMessage = 'Doctor with doctor Id ' + doctorId + ' deleted successfully ';
    }, err => {
      //this.errorMessage = err;
      this.refreshDoctors()
    
    })
  }

}

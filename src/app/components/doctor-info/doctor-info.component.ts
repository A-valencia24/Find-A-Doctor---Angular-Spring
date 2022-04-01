import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-doctor-info',
  templateUrl: './doctor-info.component.html',
  styleUrls: ['./doctor-info.component.css']
})
export class DoctorInfoComponent implements OnInit {

 
  doctors: Doctor[] = [];
  


  constructor(public doctorService: DoctorService) { }

  ngOnInit(): void {

    this.doctorService.getDoctors().subscribe((data:any) => {
      this.doctors = data;
    })
  }

}
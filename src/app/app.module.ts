import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PatientListComponent } from './components/patient-list/patient-list.component';
import { PatientAddComponent } from './components/patient-add/patient-add.component';
import { HttpClient, HttpClientModule } from '@angular/common/http';




import { DoctorsComponent } from './components/doctors/doctors.component';
import { AppointmentsComponent } from './components/appointments/appointments.component';

import { ScheduleAppointmentComponent } from './components/schedule-appointment/schedule-appointment.component';
import { DoctorInfoComponent } from './components/doctor-info/doctor-info.component';

@NgModule({
  declarations: [
    AppComponent,
    PatientListComponent,
    PatientAddComponent,
    DoctorsComponent ,
    AppointmentsComponent,
    ScheduleAppointmentComponent,
    DoctorInfoComponent 

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule , // add into the application
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

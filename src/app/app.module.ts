import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DoctorsComponent } from './components/doctors/doctors.component';
import { AppointmentsComponent } from './appointments/appointments.component';
import { ScheduleAppointmentComponent } from './schedule-appointment/schedule-appointment.component';
import { DoctorInfoComponent } from './doctor-info/doctor-info.component';
import { DoctorService } from './services/doctor.service';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';


@NgModule({
  declarations: [
    AppComponent,
    DoctorsComponent,
    AppointmentsComponent,
    ScheduleAppointmentComponent,
    DoctorInfoComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [DoctorService],
  bootstrap: [AppComponent]
})
export class AppModule { }

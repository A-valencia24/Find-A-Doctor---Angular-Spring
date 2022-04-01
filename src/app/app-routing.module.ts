import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppointmentsComponent } from './components/appointments/appointments.component';
import { DoctorInfoComponent } from './components/doctor-info/doctor-info.component';
import { DoctorsComponent } from './components/doctors/doctors.component';
import { PatientAddComponent } from './components/patient-add/patient-add.component';
import { PatientListComponent } from './components/patient-list/patient-list.component';

const routes: Routes = [
  { path : '', redirectTo : 'patientList', pathMatch :'full'},
  { path: 'addPatient', component: PatientAddComponent },
  { path: 'patientList', component: PatientListComponent},
  { path: 'addDoctor', component: DoctorsComponent },
  { path: 'doctorInfo', component: DoctorInfoComponent },
  { path: 'addAppointment', component: AppointmentsComponent }
  

  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppointmentsComponent } from './appointments/appointments.component';
import { DoctorsComponent } from './components/doctors/doctors.component';
import { DoctorInfoComponent } from './doctor-info/doctor-info.component';
import { LoginComponent } from './login/login.component';

const routes: Routes = [
  {path: '' , redirectTo : 'doctorInfo', pathMatch :'full'},
  {path: 'appointments', component:AppointmentsComponent},
  {path: 'doctors', component:DoctorsComponent},
  {path: 'doctorInfo', component:DoctorInfoComponent},
  {path: 'login', component:LoginComponent}
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

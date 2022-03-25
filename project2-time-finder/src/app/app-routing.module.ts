import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AboutusComponent } from './components/aboutus/aboutus.component';
import { ContactusComponent } from './components/contactus/contactus.component';
import { EmployeeLocationComponent } from './components/employee-location/employee-location.component';
import { PatientAddComponent } from './components/patient-add/patient-add.component';
import { PatientsComponent } from './components/patients/patients.component';
import { ProductAddComponent } from './components/product-add/product-add.component';
import { ProductCreateComponent } from './components/product-create/product-create.component';
import { ProductInfoComponent } from './components/product-info/product-info.component';
import { ProductListComponent } from './components/product-list/product-list.component';
import { UserComponent } from './components/user/user.component';

const routes: Routes = [
  { path:'', redirectTo : 'productList', pathMatch : 'full'},
  // { path:'test', redirectTo : 'aboutus', pathMatch : 'full'},
  { path:'productList', component : ProductListComponent},
  { path:'productInfo', component : ProductInfoComponent},
  { path:'employeeLocation', component : EmployeeLocationComponent},
  { path:'patientInfo', component : PatientsComponent},
  { path:'patientAdd', component : PatientAddComponent},
  { path:'aboutus', component : AboutusComponent},
  { path:'contactus', component : ContactusComponent},
  { path:'userForm', component : UserComponent},
  { path:'productAdd', component : ProductAddComponent},
  { path:'productCreate', component : ProductCreateComponent},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

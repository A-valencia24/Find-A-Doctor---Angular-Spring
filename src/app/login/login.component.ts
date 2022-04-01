import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { LoginsService } from '../services/logins.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm !: FormGroup;
  successMessage !: string;

  constructor(public formBuilder:FormBuilder, public loginsService: LoginsService, public router:Router) { }

  ngOnInit(): void {
    this.loginForm = new FormGroup ({
      username : new FormControl('', [Validators.required, Validators.minLength(5)]),
      password : new FormControl('', [Validators.required, Validators.minLength(5)])
    })
  }

  // checking with username and password for doctor
  enterLoginPage() {
    console.log(this.loginForm.value);
    // this.loginsService.loginStatus(this.loginForm.value).subscribe((data:any) => {}) // need to get this figured out
      this.successMessage = 'Login with username: ' + this.loginForm.value.username + ' and password: ' +this.loginForm.value.password + ' got in successfully.'
      console.log(this.successMessage);
      this.router.navigate(['doctorInfo']);
    
  }

}

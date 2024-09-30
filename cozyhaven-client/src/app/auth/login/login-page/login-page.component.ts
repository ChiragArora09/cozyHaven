import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { UserService } from '../../../service/user.service';
import { NgIf } from '@angular/common';
import { User } from '../../../model/user.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login-page',
  standalone: true,
  imports: [ReactiveFormsModule, NgIf],
  templateUrl: './login-page.component.html',
  styleUrl: './login-page.component.css'
})
export class LoginPageComponent {
  loginForm: FormGroup;
  token: string="";
  loginErrMsg: string="";
  user: User

  constructor(private userService: UserService, private router: Router){
    this.loginForm= new FormGroup({
      username: new FormControl('',Validators.required),
      password: new FormControl('',Validators.required)
    })
  }

  onLogin(){
    this.userService.getToken(this.loginForm.value.username, this.loginForm.value.password)
    .subscribe({
      next: (data) => {
        this.token = data.token
        this.userService.getUserDetails(this.token)
        .subscribe({
          next: (data) => {
            this.user=data
            localStorage.setItem('token',this.token );
            localStorage.setItem('username',this.user.username );
            localStorage.setItem('role',this.user.role );
            switch(this.user.role){
              case 'ROLE_SERVICE_PROVIDER':
                this.router.navigateByUrl('/flight-provider')
              break;
              case 'ROLE_CUSTOMER':
                this.router.navigateByUrl('/home')
              break;
            }
          },
          error: (err) => {
            console.log(err)
          }
        })
      },
      error: (err) => {
        console.log(err)
        this.loginErrMsg = 'Invalid Credentials';
      }
    })
  }


}

import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from 'src/app/models/user.model';
import { AuthenticationServiceService } from 'src/app/services/authentication-service.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-login-component',
  templateUrl: './login-component.component.html',
  styleUrls: ['./login-component.component.css']
})
export class LoginComponentComponent implements OnInit {

  username: string = '';
  password: string = '';
  errorMessage = 'Invalid Credentials';
  successMessage: string = '';
  invalidLogin = false;
  loginSuccess = false;

  currentUser? : User;

  constructor(private authService: AuthenticationServiceService, private router: Router,
    private route : ActivatedRoute, 
    private userService : UserService) { }

  ngOnInit(): void {}

  login() {
    this.authService.retrieveRoles(this.username, this.password).subscribe((result)=> {
      console.log(this.username + " " + this.password);

      this.userService.getByUsername(this.username)
      .subscribe({
        next: (result) => {
          this.currentUser = result;
          console.log('ID : ' + this.currentUser.userId);
          console.log('Role: ' + this.currentUser.role.name);
          this.router.navigate(['/main_page.html']);
        },
        error: (e) => console.log(e)
      });

    }, () => {
      this.invalidLogin = true;
      this.loginSuccess = false;
      this.router.navigate(['/login_page.html']);
    });     
  }

  showRoles() {
    this.authService.retrieveRoles(this.username, this.password).subscribe((result)=> {
      console.log(this.username + " " + this.password);

      this.userService.getByUsername(this.username)
      .subscribe({
        next: (result) => {
          this.currentUser = result;
          console.log('ID : ' + this.currentUser.userId);
          console.log('Role: ' + this.currentUser.role.name);
          console.log(this.currentUser.role.name == 'ROLE_ADMIN')

          if(this.currentUser?.role.name == 'ROLE_ADMIN') {
            this.invalidLogin = false;
            this.loginSuccess = true;
            this.successMessage = 'Login Successful.';
            this.router.navigate(['/roles']);
          }

        },
        error: (e) => console.log(e)
      });

    }, () => {
      this.invalidLogin = true;
      this.loginSuccess = false;
      this.router.navigate(['/login_page.html']);
    });      
  }

  showUsers() {
    this.authService.retrieveUsers(this.username, this.password).subscribe((result)=> {
      console.log(this.username + " " + this.password);

      this.userService.getByUsername(this.username)
      .subscribe({
        next: (result) => {
          this.currentUser = result;
          console.log('ID : ' + this.currentUser.userId);
          console.log('Role: ' + this.currentUser.role.name);
          console.log(this.currentUser.role.name == 'ROLE_ADMIN')
          
          if(this.currentUser?.role.name == 'ROLE_ADMIN') {
            this.invalidLogin = false;
            this.loginSuccess = true;
            this.successMessage = 'Login Successful.';
            this.router.navigate(['/users']);

            //NEW
            console.log('Logged or no? ' + this.authService.isUserLoggedIn());
          }

        },
        error: (e) => console.log(e)
      });

    }, () => {
      this.invalidLogin = true;
      this.loginSuccess = false;
      this.router.navigate(['/login_page.html']);
    });      
  }

}

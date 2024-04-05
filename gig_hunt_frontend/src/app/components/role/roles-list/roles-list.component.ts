import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Role } from 'src/app/models/role.model';
import { AuthenticationServiceService } from 'src/app/services/authentication-service.service';
import { RoleService } from 'src/app/services/role.service';
import { LoginComponentComponent } from '../../login-component/login-component.component';
import { User } from 'src/app/models/user.model';

@Component({
  selector: 'app-roles-list',
  templateUrl: './roles-list.component.html',
  styleUrls: ['./roles-list.component.css']
})
export class RolesListComponent implements OnInit {

  public roles$: Observable<Role[]> = new Observable<Role[]>();
  //public roles : any;

  public loggedUser? : any

  constructor(private roleService: RoleService, 
    private authService : AuthenticationServiceService, 
    private router : Router) {}

  ngOnInit() {
    this.fetchRoles();
  }

  fetchRoles() {
    if(this.authService.isUserLoggedIn()) {
      //console.log(this.authService.isUserLoggedIn()); //true
      //console.log(this.authService.getLoggedInUserName()); //admin

      this.loggedUser = this.authService.username;
      //console.log('Is logged in: ' + this.authService.username); //''

      //this.authService.username = this.authService.getLoggedInUserName();
      //console.log(this.authService.username);

      //this.loggedUser = this.authService.getLoggedInUserName();
      //console.log('Logged user: ' + this.loggedUser); //admin

      //if(this.loggedUser == this.authService.getLoggedInUserName() && 
      //this.loggedUser == this.authService.username) {
        //this.roles$ = this.roleService.getAll();
        //this.router.navigate(['/roles']);
      //}

      this.roles$ = this.roleService.getAll();
      
    }
    else {
      this.router.navigate(['/login_page.html']);
    }
  }

}

import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { User } from 'src/app/models/user.model';
import { AuthenticationServiceService } from 'src/app/services/authentication-service.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-users-list',
  templateUrl: './users-list.component.html',
  styleUrls: ['./users-list.component.css']
})
export class UsersListComponent implements OnInit {

  public users$: Observable<User[]> = new Observable<User[]>;

  constructor(private userService: UserService,
    //NEW
    private authService : AuthenticationServiceService, 
    private router : Router) {}

  ngOnInit() {
    this.fetchUsers();
  }

  fetchUsers() {
    if(this.authService.isUserLoggedIn() && this.authService.isUserAdmin()) {
      console.log('User is admin? ' + this.authService.isUserAdmin());
      this.users$ = this.userService.getAll();
    }
    else {
      this.router.navigate(['/login_page.html']);
    }
    //this.users$ = this.userService.getAll();
  }

}

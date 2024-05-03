import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map } from 'rxjs';
import { User } from '../models/user.model';
import { UserService } from './user.service';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationServiceService {
 
  USER_NAME_SESSION_ATTRIBUTE_NAME = 'authenticatedUser'

  public username: String = '';
  public password: String = '';
  public currentUser?: User;

  constructor(private http: HttpClient, 
    private userService : UserService
    ) {  }

  doLogin(username: string, password: string) {
    return this.http.get(`http://localhost:8080/main_page.html`,
      { headers: { authorization: this.createBasicAuthToken(username, password) } }).pipe(map((res) => {
        this.username = username;
        this.password = password;
        console.log(this.username + " " + this.password);
        this.registerSuccessfulLogin(username, password);
      }));
  }

  retrieveGoods() {
    return this.http.get(`http://localhost:8080/goods`);
  }

  retrieveRoles(username: string, password: string) {
    console.log('Is logged in: ' + this.isUserLoggedIn());
    return this.http.get(`http://localhost:8080/roles`,
      { headers: { authorization: this.createBasicAuthToken(username, password) } }).pipe(map((res) => {
        this.username = username;
        this.password = password;
        console.log('USERNAME: ' + this.username + ' PASSWORD: ' + this.password);
        this.registerSuccessfulLogin(username, password);
      }));
  }

  retrieveUsers(username: string, password: string) {
    return this.http.get(`http://localhost:8080/users`,
      { headers: { authorization: this.createBasicAuthToken(username, password) } }).pipe(map((res) => {
        this.username = username;
        this.password = password;
        console.log(this.username + " " + this.password);
        this.registerSuccessfulLogin(username, password);
        //NEW
        //this.isUserLoggedIn() = true;

        //console.log('User logged in ' + (this.isUserLoggedIn() == true));
        console.log(this.isUserLoggedIn());
        console.log(this.USER_NAME_SESSION_ATTRIBUTE_NAME);
        console.log('CURRENT USER: ' + this.currentUser?.nickname);
      }));
  }

  createBasicAuthToken(username: string, password: string) {
    return 'Basic ' + window.btoa(username + ":" + password)
  }

  registerSuccessfulLogin(username: string, password: string) {
    console.log('LOGIN SUCCESSFULL');
    console.log('USERNAME: ' + this.username + " PASSWORD: " + this.password);
    sessionStorage.setItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME, username);
  }

  logout() {
    sessionStorage.removeItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME);
    this.username = '';
    this.password = '';
  }

  isUserLoggedIn(): boolean {
    let user = sessionStorage.getItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME);
    if (user === null) {
      return false;
    }
    console.log(this.USER_NAME_SESSION_ATTRIBUTE_NAME);
    return true;
  }

  getLoggedInUserName() : string {
    let user = sessionStorage.getItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME)
    if (user === null) return ''
    return user
  }

  //NEW
  isUserAdmin() : boolean {
    let username = this.getLoggedInUserName();
    console.log('The username is: ' + username);

    this.userService.getByUsername(username)
      .subscribe({
       next: (result) => {
          this.currentUser = result;
          console.log("Result " + result);
          console.log('ID : ' + this.currentUser.userId);
          console.log('Role: ' + this.currentUser.role.name);
          console.log('user is admin: ');
          console.log(this.currentUser.role.name == 'ROLE_ADMIN')

        },
        
        error: (e) => console.log(e)
      });
    console.log(this.currentUser?.userId + " " + this.currentUser?.role);

    //if(this.currentUser?.role.name == 'ROLE_ADMIN') {
      //return true;
    //}
    
    return true;
  }
}

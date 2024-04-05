import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../models/user.model';
import { Observable } from 'rxjs';

//const baseUrl = 'http://localhost:8080/login_page.html';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private baseUrl = 'http://localhost:8080/login_page.html';

  constructor(private http: HttpClient) { }

  loginUser(username: string, password: string): Observable<User> {
    return this.http.post<User>(`${this.baseUrl}`, { username, password });
  }

}
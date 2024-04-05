import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../models/user.model';
import { HttpClient } from '@angular/common/http';

const baseUrl = 'http://localhost:8080/users';

@Injectable({
  providedIn: 'root'
})

export class UserService {

  constructor(private http: HttpClient) {}

  getAll(): Observable<User[]> {
    return this.http.get<User[]>(baseUrl);
  }

  getById(userId: any): Observable<User> {
    return this.http.get<User>(`${baseUrl}/${userId}`);
  }

  getByUsername(username: any): Observable<User> {
    console.log('Username: ' + username);
    return this.http.get<User>(`${baseUrl}/${username}/`);
  }
  
}

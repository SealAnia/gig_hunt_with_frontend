import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Role } from '../models/role.model';

const baseUrl = 'http://localhost:8080/roles';

@Injectable({
  providedIn: 'root'
})
export class RoleService {

  constructor(private http: HttpClient) {}

  getAll(): Observable<Role[]> {
    return this.http.get<Role[]>(baseUrl);
  }

  getById(roleId: any): Observable<Role> {
    return this.http.get<Role>(`${baseUrl}/${roleId}`);
  }

  createRole(role: any): Observable<any> {
    return this.http.post(`${baseUrl}/`, role);
  }

  updateRole(roleId: any, role: any): Observable<any> {
    return this.http.put(`${baseUrl}/${roleId}`, role);
  }

  deleteRole(roleId: any): Observable<any> {
    return this.http.delete(`${baseUrl}/${roleId}`);
  }

}

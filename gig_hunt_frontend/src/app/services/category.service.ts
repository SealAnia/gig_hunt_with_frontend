import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Category } from '../models/category.model';

const baseUrl = 'http://localhost:8080/categories';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  constructor(private http: HttpClient) { }
  
  getAll(): Observable<Category[]> {
    return this.http.get<Category[]>(baseUrl);
  }

  getById(categoryId: any): Observable<Category> {
    return this.http.get<Category>(`${baseUrl}/${categoryId}`);
  }

  createCategory(category: any): Observable<any> {
    return this.http.post(`${baseUrl}/`, category);
  }

  updateCategory(categoryId: any, category: any): Observable<any> {
    return this.http.put(`${baseUrl}/${categoryId}`, category);
  }

  deleteCategory(categoryId: any): Observable<any> {
    return this.http.delete(`${baseUrl}/${categoryId}`);
  }

}

import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Goods } from '../models/goods.model';

const baseUrl = 'http://localhost:8080/goods';

@Injectable({
  providedIn: 'root'
})
export class GoodsService {

  constructor(private http: HttpClient) {}

  getAll(): Observable<Goods[]> {
    return this.http.get<Goods[]>(baseUrl);
  }

  getById(goodsId: any): Observable<Goods> {
    return this.http.get<Goods>(`${baseUrl}/${goodsId}`);
  }

}

import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Town} from "../models/town.model";

const baseUrl = 'http://localhost:8080/towns';

@Injectable({
  providedIn: 'root'
})
export class TownService {

  constructor(private http: HttpClient) { }

  getAll(): Observable<Town[]> {
    return this.http.get<Town[]>(baseUrl);
  }

  getById(townId: any): Observable<Town> {
    return this.http.get<Town>(`${baseUrl}/${townId}`);
  }

  createTown(town: any): Observable<any> {
    return this.http.post(`${baseUrl}/`, town);
  }

  updateTown(townId: any, town: any): Observable<any> {
    return this.http.put(`${baseUrl}/${townId}`, town);
  }

  deleteTown(townId: any): Observable<any> {
    return this.http.delete(`${baseUrl}/${townId}`);
  }

}

import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Region} from "../models/region.model";
import {Observable} from "rxjs";

const baseUrl = 'http://localhost:8080/regions';

@Injectable({
  providedIn: 'root'
})
export class RegionService {

  constructor(private http: HttpClient) {}

  getAll(): Observable<Region[]> {
    return this.http.get<Region[]>(baseUrl);
  }

  getById(regionId: any): Observable<Region> {
    return this.http.get<Region>(`${baseUrl}/${regionId}`);
  }

  createRegion(region: any): Observable<any> {
    return this.http.post(`${baseUrl}/`, region);
  }

  updateRegion(regionId: any, region: any): Observable<any> {
    return this.http.put(`${baseUrl}/${regionId}`, region);
  }

  deleteRegion(regionId: any): Observable<any> {
    return this.http.delete(`${baseUrl}/${regionId}`);
  }

}

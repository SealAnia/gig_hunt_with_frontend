import {Component, OnInit} from '@angular/core';
import {Region} from "../../../models/region.model";
import {RegionService} from "../../../services/region.service";
import {Observable} from "rxjs";

@Component({
  selector: 'app-regions-list',
  templateUrl: './regions-list.component.html',
  styleUrls: ['./regions-list.component.css']
})
export class RegionsListComponent implements OnInit {

  public regions$: Observable<Region[]> = new Observable<Region[]>();
  
  constructor(private regionService: RegionService) {}

  ngOnInit() {
    this.fetchRegions();
  }

  fetchRegions(): void {
    this.regions$ = this.regionService.getAll();
  }

}

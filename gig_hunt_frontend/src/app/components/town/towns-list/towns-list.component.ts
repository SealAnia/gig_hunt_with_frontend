import {Component, OnInit} from '@angular/core';
import {Observable} from "rxjs";
import {Town} from "../../../models/town.model";
import {Region} from 'src/app/models/region.model';
import {TownService} from "../../../services/town.service";

@Component({
  selector: 'app-towns-list',
  templateUrl: './towns-list.component.html',
  styleUrls: ['./towns-list.component.css']
})
export class TownsListComponent implements OnInit {

  public towns$: Observable<Town[]> = new Observable<Town[]>;

  constructor(private townService: TownService) {}

  ngOnInit() {
    this.fetchTowns();
  }

  fetchTowns() {
    this.towns$ = this.townService.getAll();
  }
  
}

import {Component, OnInit} from '@angular/core';
import {Town} from "../../../models/town.model";
import {TownService} from "../../../services/town.service";

@Component({
  selector: 'app-add-town',
  templateUrl: './add-town.component.html',
  styleUrls: ['./add-town.component.css']
})
export class AddTownComponent implements OnInit {

  town: Town = {
    name: '',
    region: {}
  };

  constructor(private townService: TownService) {}

  ngOnInit(): void {
  }

  saveTown(): void {
    const data = {
      name: this.town.name,
      region: this.town.region
    };

    this.townService.createTown(data)
      .subscribe({
        next: (result) => {
          console.log(result);
        },
        error: (e) => console.log(e)
      });
  }

  newTown(): void {
    this.town = {
      name: '',
      region: {}
    };
  }
}
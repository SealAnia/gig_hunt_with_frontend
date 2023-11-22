import {Component, Input, OnInit} from '@angular/core';
import {Town} from "../../../models/town.model";
import {TownService} from "../../../services/town.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-town-details',
  templateUrl: './town-details.component.html',
  styleUrls: ['./town-details.component.css']
})
export class TownDetailsComponent implements OnInit {

  @Input() currentTown: Town = {
    townId: 0,
    name: '',
    region: {
      //regionId: 0
    }
  };

  constructor(private townService: TownService,
              private route: ActivatedRoute,
              private router: Router) {}

  ngOnInit(): void {
    this.getTownById(this.route.snapshot.params["townId"]);
  }

  getTownById(townId: number) {
    this.townService.getById(townId)
      .subscribe({
        next: (result) => {
          this.currentTown = result;
          console.log(result);
        },
        error: (e) => console.error(e)
      });
  }

  updateTown(): void {
    this.townService.updateTown(this.currentTown.townId, this.currentTown)
      .subscribe({
        next: (result) => {
          console.log(result);
        },
        error: (e) => console.error(e)
      });
  }

  deleteTown(): void {
    this.townService.deleteTown(this.currentTown.townId)
      .subscribe({
        next: (result) => {
          console.log(result);
          this.router.navigate(['/towns']);
        },
        error: (e) => console.error(e)
      });
  }

}
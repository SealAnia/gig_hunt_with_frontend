import {Component, Input, OnInit} from '@angular/core';
import {Region} from "../../../models/region.model";
import {ActivatedRoute, Router} from "@angular/router";
import {RegionService} from "../../../services/region.service";

@Component({
  selector: 'app-region-details',
  templateUrl: './region-details.component.html',
  styleUrls: ['./region-details.component.css']
})
export class RegionDetailsComponent implements OnInit {

  @Input() currentRegion: Region = {
    regionId: 0,
    name: '',
  };

  constructor(private regionService: RegionService,
              private route: ActivatedRoute,
              private router: Router) {}

  ngOnInit() {
    this.getRegionById(this.route.snapshot.params["regionId"]);
  }

  getRegionById(regionId: number) {
    this.regionService.getById(regionId)
      .subscribe({
        next: (result) => {
          this.currentRegion = result;
          console.log(result);
        },
        error: (e) => console.log(e)
      });
  }

  //NEW
  //getTowns(regionId: any) {
    //this.regionService.getTownsOfRegion(regionId)
    //.subscribe({
      //next: (result) => {
        //this.currentRegion.towns = result;
        //console.log(result);
      //},
      //error: (e) => console.log(e)
    //});
  //}

  updateRegion(): void {
    this.regionService.updateRegion(this.currentRegion.regionId, this.currentRegion)
      .subscribe({
        next: (result) => {
          console.log(result);
        },
        error: (e) => console.error(e)
      });
  }

  deleteRegion(): void {
    this.regionService.deleteRegion(this.currentRegion.regionId)
      .subscribe({
        next: (result) => {
          console.log(result);
          this.router.navigate(['/regions']);
        },
        error: (e) => console.error(e)
      });
  }

}

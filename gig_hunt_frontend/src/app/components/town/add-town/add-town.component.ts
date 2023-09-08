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

//export class AddTownComponent implements OnInit {
  //townForm: FormGroup = new FormGroup({});
  //regions: Region[] = [];

  //constructor(
    //private formBuilder: FormBuilder,
    //private townService: TownService,
    //private regionService: RegionService
  //) {}

  //ngOnInit(): void {
    //this.initForm();
    //this.loadRegions();
  //}

  //initForm(): void {
    //this.townForm = this.formBuilder.group({
      //name: ['', Validators.required],
      //region: ['', Validators.required] // Assuming you have a dropdown/select for regions
    //});
  //}

  //loadRegions(): void {
    //this.regionService.getAll().subscribe(regions => {
      //this.regions = regions;
    //});
  //}

  //onSubmit(): void {
    //if (this.townForm.invalid) {
      //return;
    //}

    //const townData = this.townForm.value;
    //this.townService.createTown(townData).subscribe({
      //next: (result) => {
        //console.log(result);
      //}
    //});
  //}
//}
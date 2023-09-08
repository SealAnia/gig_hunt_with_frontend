import {Component, OnInit} from '@angular/core';
import {Region} from "../../../models/region.model";
import {RegionService} from "../../../services/region.service";

@Component({
  selector: 'app-add-region',
  templateUrl: './add-region.component.html',
  styleUrls: ['./add-region.component.css']
})
export class AddRegionComponent implements OnInit {

  region: Region = {
    name: '',
    towns: []
  };
  constructor(private regionService: RegionService) {}

  ngOnInit(): void {
  }

  saveRegion(): void {
    const data = {
      name: this.region.name,
      towns: this.region.towns
    };

    this.regionService.createRegion(data)
      .subscribe({
        next: (result) => {
          console.log(result);
        },
        error: (e) => console.error(e)
      });
  }

  newRegion(): void {
    this.region = {
      name: '',
      towns: [],
    };
  }

  //addTown() {
    //this.region.towns.push(new Town());
  //}

}

//export class AddRegionComponent implements OnInit {
  //regionForm: FormGroup = new FormGroup({});

  //constructor(private formBuilder: FormBuilder, private regionService: RegionService) {}

  //ngOnInit(): void {
    //this.initForm();
  //}

  //initForm(): void {
    //this.regionForm = this.formBuilder.group({
      //name: ['', Validators.required]
    //});
  //}

  //onSubmit(): void {
    //if (this.regionForm.invalid) {
      //return;
    //}

    //const regionData = this.regionForm.value;
    //this.regionService.createRegion(regionData).subscribe({
      //next : (result) => {
        //console.log(result);
      //}
    //});
  //}
//}
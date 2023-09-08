import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {RegionsListComponent} from "./components/region/regions-list/regions-list.component";
import {AddRegionComponent} from "./components/region/add-region/add-region.component";
import {TownsListComponent} from "./components/town/towns-list/towns-list.component";
import {AddTownComponent} from "./components/town/add-town/add-town.component";
import {RegionDetailsComponent} from "./components/region/region-details/region-details.component";
import {TownDetailsComponent} from "./components/town/town-details/town-details.component";
import { CategoriesListComponent } from './components/category/categories-list/categories-list.component';
import { CategoryDetailsComponent } from './components/category/category-details/category-details.component';
import { AddCategoryComponent } from './components/category/add-category/add-category.component';

const routes: Routes = [
  {path: 'regions', component: RegionsListComponent},
  {path: 'regions/:regionId', component: RegionDetailsComponent},
  {path: 'add_region', component: AddRegionComponent},
  {path: 'towns', component: TownsListComponent},
  {path: 'add_town', component: AddTownComponent},
  {path: 'towns/:townId', component: TownDetailsComponent},
  {path: 'categories', component: CategoriesListComponent},
  {path: 'categories/:categoryId', component: CategoryDetailsComponent},
  {path: 'add_category', component: AddCategoryComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RegionsListComponent } from './components/region/regions-list/regions-list.component';
import {HttpClientModule} from "@angular/common/http";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { AddRegionComponent } from './components/region/add-region/add-region.component';
import { RegionDetailsComponent } from './components/region/region-details/region-details.component';
import { AddTownComponent } from './components/town/add-town/add-town.component';
import { TownDetailsComponent } from './components/town/town-details/town-details.component';
import { TownsListComponent } from './components/town/towns-list/towns-list.component';
import { AddCategoryComponent } from './components/category/add-category/add-category.component';
import { CategoryDetailsComponent } from './components/category/category-details/category-details.component';
import { CategoriesListComponent } from './components/category/categories-list/categories-list.component';

@NgModule({
  declarations: [
    AppComponent,
    RegionsListComponent,
    AddRegionComponent,
    RegionDetailsComponent,
    AddTownComponent,
    TownDetailsComponent,
    TownsListComponent,
    AddCategoryComponent,
    CategoryDetailsComponent,
    CategoriesListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RegionsListComponent } from './components/region/regions-list/regions-list.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { AddRegionComponent } from './components/region/add-region/add-region.component';
import { RegionDetailsComponent } from './components/region/region-details/region-details.component';
import { AddTownComponent } from './components/town/add-town/add-town.component';
import { TownDetailsComponent } from './components/town/town-details/town-details.component';
import { TownsListComponent } from './components/town/towns-list/towns-list.component';
import { AddCategoryComponent } from './components/category/add-category/add-category.component';
import { CategoryDetailsComponent } from './components/category/category-details/category-details.component';
import { CategoriesListComponent } from './components/category/categories-list/categories-list.component';
import { AddUserComponent } from './components/user/add-user/add-user.component';
import { UsersListComponent } from './components/user/users-list/users-list.component';
import { UserDetailsComponent } from './components/user/user-details/user-details.component';
import { AddRoleComponent } from './components/role/add-role/add-role.component';
import { RoleDetailsComponent } from './components/role/role-details/role-details.component';
import { RolesListComponent } from './components/role/roles-list/roles-list.component';
import { GoodsDetailsComponent } from './components/goods/goods-details/goods-details.component';
import { GoodsListComponent } from './components/goods/goods-list/goods-list.component';
import { AddGoodsComponent } from './components/goods/add-goods/add-goods.component';
import { LoginComponentComponent } from './components/login-component/login-component.component';
//NEW
import { LoginService } from './services/login.service';
import { HttpInterceptorServiceService } from './services/http-interceptor-service.service';
import { MainComponent } from './components/main/main.component';

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
    CategoriesListComponent,
    AddUserComponent,
    UsersListComponent,
    UserDetailsComponent,
    AddRoleComponent,
    RoleDetailsComponent,
    RolesListComponent,
    GoodsDetailsComponent,
    GoodsListComponent,
    AddGoodsComponent,
    LoginComponentComponent,
    MainComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  //NEW
  providers: //[LoginService],
  [{
    provide: HTTP_INTERCEPTORS,
    useClass: HttpInterceptorServiceService,
    multi: true
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }

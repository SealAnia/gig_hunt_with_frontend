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
import { UsersListComponent } from './components/user/users-list/users-list.component';
import { UserDetailsComponent } from './components/user/user-details/user-details.component';
import { AddUserComponent } from './components/user/add-user/add-user.component';
import { RolesListComponent } from './components/role/roles-list/roles-list.component';
import { RoleDetailsComponent } from './components/role/role-details/role-details.component';
import { AddRoleComponent } from './components/role/add-role/add-role.component';
import { GoodsListComponent } from './components/goods/goods-list/goods-list.component';
import { GoodsDetailsComponent } from './components/goods/goods-details/goods-details.component';
import { AddGoodsComponent } from './components/goods/add-goods/add-goods.component';
import { LoginComponentComponent } from './components/login-component/login-component.component';
import { MainComponent } from './components/main/main.component';
import { AppComponent } from './app.component';
//import { AuthGuard } from './auth.guard';

const routes: Routes = [
  //{path: '', component: AppComponent},

  {path: 'main_page.html', component: MainComponent},
  {path: 'regions', component: RegionsListComponent},
  {path: 'regions/:regionId', component: RegionDetailsComponent},
  {path: 'add_region', component: AddRegionComponent},
  {path: 'towns', component: TownsListComponent},
  {path: 'add_town', component: AddTownComponent},
  {path: 'towns/:townId', component: TownDetailsComponent},
  {path: 'categories', component: CategoriesListComponent},
  {path: 'categories/:categoryId', component: CategoryDetailsComponent},
  {path: 'add_category', component: AddCategoryComponent},
  {path: 'roles', component: RolesListComponent},
  {path: 'roles/:roleId', component: RoleDetailsComponent}, 
  {path: 'add_role', component: AddRoleComponent},
  {path: 'users', component: UsersListComponent}, 
  {path: 'users/:userId', component: UserDetailsComponent}, 
  {path: 'users/:nickname/', component: UserDetailsComponent}, 

  {path: 'add_user', component: AddUserComponent},
  {path: 'goods', component: GoodsListComponent},

  {path: 'goods/:goodsId', component: GoodsDetailsComponent},
  {path: 'add_goods', component: AddGoodsComponent},
  {path: 'login_page.html', component: LoginComponentComponent},
  {path: "", redirectTo: "login_page.html", pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],

  exports: [RouterModule]
})
export class AppRoutingModule { }

import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Role } from 'src/app/models/role.model';
import { RoleService } from 'src/app/services/role.service';

@Component({
  selector: 'app-roles-list',
  templateUrl: './roles-list.component.html',
  styleUrls: ['./roles-list.component.css']
})
export class RolesListComponent implements OnInit {

  public roles$: Observable<Role[]> = new Observable<Role[]>();

  constructor(private roleService: RoleService) {}

  ngOnInit() {
    this.fetchRoles();
  }

  fetchRoles() {
    this.roles$ = this.roleService.getAll();
  }

}

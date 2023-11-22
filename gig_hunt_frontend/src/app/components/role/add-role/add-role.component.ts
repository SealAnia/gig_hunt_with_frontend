import { Component, OnInit } from '@angular/core';
import { Role } from 'src/app/models/role.model';
import { RoleService } from 'src/app/services/role.service';

@Component({
  selector: 'app-add-role',
  templateUrl: './add-role.component.html',
  styleUrls: ['./add-role.component.css']
})
export class AddRoleComponent implements OnInit {

  role: Role = {
    name: 'ROLE_'+ ''
  };
  constructor(private roleService: RoleService) {}

  ngOnInit(): void {
  }

  saveRole(): void {
    const data = {
      name: this.role.name
    };

    this.roleService.createRole(data)
      .subscribe({
        next: (result) => {
          console.log(result);
        },
        error: (e) => console.error(e)
      });
  }

  newRole(): void {
    this.role = {
      name: '',
    };
  }

}

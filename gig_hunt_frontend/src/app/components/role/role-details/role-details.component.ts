import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Role } from 'src/app/models/role.model';
import { RoleService } from 'src/app/services/role.service';

@Component({
  selector: 'app-role-details',
  templateUrl: './role-details.component.html',
  styleUrls: ['./role-details.component.css']
})
export class RoleDetailsComponent implements OnInit {

  @Input() currentRole: Role = {
    roleId: 0, 
    name: ''
  };

  constructor(private roleService: RoleService,
    private route: ActivatedRoute,
    private router: Router) {}

  ngOnInit(): void {
    this.getRoleById(this.route.snapshot.params["roleId"]);
  }

  getRoleById(roleId: number) {
    this.roleService.getById(roleId)
      .subscribe({
        next: (result) => {
          this.currentRole = result;
          console.log(result);
        },
        error: (e) => console.error(e)
      });
  }

  updateRole(): void {
    this.roleService.updateRole(this.currentRole.roleId, this.currentRole)
      .subscribe({
        next: (result) => {
          console.log(result);
        },
        error: (e) => console.error(e)
      });
  }

  deleteRole(): void {
    this.roleService.deleteRole(this.currentRole.roleId)
      .subscribe({
        next: (result) => {
          console.log(result);
          this.router.navigate(['/roles']);
        },
        error: (e) => console.error(e)
      });
  }

}

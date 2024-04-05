import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from 'src/app/models/user.model';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.css']
})
export class UserDetailsComponent implements OnInit{

  @Input() currentUser : User = {
    userId : 0,
    nickname : ''
  }

  constructor(private userService : UserService,
    private route: ActivatedRoute, 
    private router: Router) {}

  ngOnInit() {
    this.userService.getByUsername(this.route.snapshot.params["nickname"]);
  }

  getUserByUsername(nickname : string) {
    this.userService.getByUsername(nickname)
      .subscribe({
        next: (result) => {
          this.currentUser = result;
          console.log(result);
        },
        error: (e) => console.log(e)
      });
  }
}

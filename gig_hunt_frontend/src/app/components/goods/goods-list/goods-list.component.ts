import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { Router, TitleStrategy } from '@angular/router';
import { Observable, map } from 'rxjs';
import { Goods } from 'src/app/models/goods.model';
import { AuthenticationServiceService } from 'src/app/services/authentication-service.service';
import { GoodsService } from 'src/app/services/goods.service';

@Component({
  selector: 'app-goods-list',
  templateUrl: './goods-list.component.html',
  styleUrls: ['./goods-list.component.css']
})
export class GoodsListComponent implements OnInit {

  public goods$: Observable<Goods[]> = new Observable<Goods[]>();

  constructor(private goodsService: GoodsService, 
    //NEW
    private authService : AuthenticationServiceService,
    private router : Router
    ) { }

  ngOnInit() {
    this.fetchGoods();
  }

  fetchGoods(): void {

    console.log('Nickname ' + this.authService.currentUser?.nickname);
    console.log('Username ' + this.authService.getLoggedInUserName());
    console.log('Logged in ' + this.authService.isUserLoggedIn());
    console.log(this.authService.getLoggedInUserName() == '');

    //NEW
    if(this.authService.isUserLoggedIn() == true || 
    (this.authService.isUserLoggedIn() == false) && this.authService.getLoggedInUserName() == '') {

    this.goods$ = this.goodsService.getAll()
    .pipe(
      map((goods: Goods[]) => {
        return goods.map(good => {
          good.imageData = 'data:image/png;base64,' + good.image;
          return good;
        });
      })
    );

    //NEW
    this.authService.retrieveGoods();

    this.router.navigate(['/goods']);

  }

}

}

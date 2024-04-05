import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { map } from 'rxjs';
import { Goods } from 'src/app/models/goods.model';
import { CategoryService } from 'src/app/services/category.service';
import { GoodsService } from 'src/app/services/goods.service';

@Component({
  selector: 'app-goods-details',
  templateUrl: './goods-details.component.html',
  styleUrls: ['./goods-details.component.css']
})
export class GoodsDetailsComponent implements OnInit {

  @Input() currentGoods: Goods = {
    goodsId : 0,
    price: 0.0,
    description: '',
    image: null,
    //???
    imageData: ''
  }

  constructor(private goodsService: GoodsService,
    private route: ActivatedRoute,
    private router: Router) {}

  ngOnInit(): void {
    this.getGoodsById(this.route.snapshot.params["goodsId"]);
  }

  getGoodsById(goodsId: number): void {
    this.goodsService.getById(goodsId).subscribe({
      next: (goods: Goods) => {
        if (goods.image) {
          goods.imageData = 'data:image/png;base64,' + goods.image;
        }
        this.currentGoods = goods;
        console.log(this.currentGoods);
      },
      error: (error) => {
        console.error(error);
      }
    });
  }

}

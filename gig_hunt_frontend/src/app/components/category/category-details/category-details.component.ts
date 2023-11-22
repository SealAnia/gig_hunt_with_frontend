import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Category } from 'src/app/models/category.model';
import { CategoryService } from 'src/app/services/category.service';

@Component({
  selector: 'app-category-details',
  templateUrl: './category-details.component.html',
  styleUrls: ['./category-details.component.css']
})
export class CategoryDetailsComponent implements OnInit {

  @Input() currentCategory: Category = {
    categoryId: 0,
    name: '',
    comment: '',
    availableOnline: false
  };

  constructor(private categoryService: CategoryService,
    private route: ActivatedRoute,
    private router: Router) {}

  ngOnInit(): void {
    this.getCategoryById(this.route.snapshot.params["categoryId"]);
  }

  getCategoryById(categoryId: number) {
    this.categoryService.getById(categoryId)
      .subscribe({
        next: (result) => {
          this.currentCategory = result;
          console.log(result);
        },
        error: (e) => console.error(e)
      });
  }

  updateCategory(): void {
    this.categoryService.updateCategory(this.currentCategory.categoryId, this.currentCategory)
      .subscribe({
        next: (result) => {
          console.log(result);
        },
        error: (e) => console.error(e)
      });
  }

  deleteCategory(): void {
    this.categoryService.deleteCategory(this.currentCategory.categoryId)
      .subscribe({
        next: (result) => {
          console.log(result);
          this.router.navigate(['/categories']);
        },
        error: (e) => console.error(e)
      });
  }

}

import { Component, OnInit } from '@angular/core';
import { Category } from 'src/app/models/category.model';
import { CategoryService } from 'src/app/services/category.service';

@Component({
  selector: 'app-add-category',
  templateUrl: './add-category.component.html',
  styleUrls: ['./add-category.component.css']
})
export class AddCategoryComponent implements OnInit {

  category: Category = {
    name: '',
    comment: '',
    availableOnline: false
  };

  constructor(private categoryService: CategoryService) {}

  ngOnInit() {
  }

  saveCategory(): void {
    const data = {
      name: this.category.name,
      comment: this.category.comment,
      availableOnline: this.category.availableOnline
    };

    this.categoryService.createCategory(data)
      .subscribe({
        next: (result) => {
          console.log(result);
        },
        error: (e) => console.error(e)
      });
  }

  newCategory(): void {
    this.category = {
      name: '',
      comment: '',
      availableOnline: false
    };
  }

}

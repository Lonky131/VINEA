import { AfterViewInit, Component, ViewChild, OnInit, OnDestroy } from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';
import {FlexLayoutModule} from '@angular/flex-layout';
import { HttpParams } from '@angular/common/http';
import {WineService} from '../services/wine.service';
import {wine} from '../classes/wine';
import {cloneDeep} from 'lodash';
import { wineCategory } from '../classes/wine-category';
import { map, Subscription } from 'rxjs';
import { ActivatedRoute } from '@angular/router';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { DialogOverviewExampleDialog } from './dialog-overview-example-dialog';
import { CategoryService } from '../services/category.service';
import {DialogAddCategory} from './dialogAddCategory';
import { category } from '../classes/category';



@Component({
  selector: 'app-wine-details',
  templateUrl: './wine-details.component.html',
  styleUrls: ['./wine-details.component.scss']
})
export class WineDetailsComponent implements OnInit, AfterViewInit, OnDestroy{
  private sub: Subscription;
  public wine : wine;
  public allCategorites : Array<category>;
  public newCategoryValue : string;
  public selectedCategory : string;

  displayedColumns: string[] = ['id', 'categoryId', 'categoryName', 'value', 'actions'];
  dataSource: MatTableDataSource<wineCategory>;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(
    private wineService: WineService,
    private activatedRoute: ActivatedRoute,
    private categoryService: CategoryService,
    public dialog: MatDialog
  ) {

    this.sub = this.activatedRoute.params.subscribe(params => {
      this.wineService.getWineById(parseInt(params['id'])).subscribe((res) => {
        this.wine = res;
        console.log(res);
      });
      this.wineService.getWineCategoriesById(parseInt(params['id'])).subscribe((res) => {
        this.dataSource = new MatTableDataSource(res);
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
      });
    });
  }

  ngOnInit(): void {
    console.log("Wine Details initialized...");
    this.wineService.getWineCategoriesById(1).subscribe((res) => {
      console.log(res);
    });

    this.categoryService.getAllCategories().subscribe((res) => {
      this.allCategorites = res;
    });
  }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  ngOnDestroy() {
    this.sub.unsubscribe();
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }
  editCategory(id : any){
    console.log(id);
  }

  deleteWineDialog() : void{
    const dialogRef = this.dialog.open(DialogOverviewExampleDialog, {
      width: '300px'
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('Dialog closed');
      console.log(result);
      if(result == true){
/**
 * ADD WINE DELETE FUNCTIONALITY HERE
 *
 *
 */

        this.wineService.getWineById(this.wine.id).subscribe((res) => {
          console.log(`This is function call after confirm delete ${res.name}`);
        })
      }

    })

  }

  deleteWineCategoryDialog(categoryId: number) : void{
    const dialogRef = this.dialog.open(DialogOverviewExampleDialog, {
      width: '300px'
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('Dialog closed');
      console.log(result);
      if(result == true){
        //IF USER CONFIRMS DELETION CALL THE DELETE REQUEST
        this.wineService.deleteCategoryOfWine(this.wine.id, categoryId).subscribe();
      }

    })

  }


  addCategory() {
    const dialogRef = this.dialog.open(DialogAddCategory, {
      width: '500px',
      data: {
        categories : this.allCategorites,
        newValue : this.newCategoryValue,
        selectedCategory : this.selectedCategory
      }
    });

    dialogRef.afterClosed().subscribe(result => {
      if(result !== undefined) {
        let tempObj = this.allCategorites.find(o => o.name === result.selectedCategory);
        if (tempObj != undefined) {
          this.wineService.addCategoryToWine(this.wine.id, tempObj.id, result.newValue).subscribe();

          // DOESNT REFRESH THE MAT TABLE, TRY TO FIX IF EXTRA TIME
          this.wineService.getWineCategoriesById(this.wine.id).subscribe((res) => {
            this.dataSource = new MatTableDataSource(res);
            this.dataSource.paginator = this.paginator;
            this.dataSource.sort = this.sort;
          });
        } else{
          console.log("NO CATEGRIES AT ALL!");
        }
      }
    });

  }

}



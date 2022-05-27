import { AfterViewInit, Component, ViewChild, OnInit } from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';
import {FlexLayoutModule} from '@angular/flex-layout';
import { HttpParams } from '@angular/common/http';
import {WineService} from '../../services/wine.service';
import {wine} from '../../classes/wine';
import {cloneDeep} from 'lodash';
import {Router} from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { DialogAddWine } from 'src/app/dialogs/dialogAddWine/dialogAddWine';
import { category } from 'src/app/classes/category';



@Component({
  selector: 'app-wine',
  templateUrl: './wine.component.html',
  styleUrls: ['./wine.component.scss']
})
export class WineComponent implements OnInit, AfterViewInit {
  public wines : Array<wine>;
  public newWineName : string;
  public newWineProductionYear : number;
  public newWineAlcoholPercentage : number;
  public newWineVolume : number;
  public newWinePrice : number;
  public newWinePictureUrl : string;
  public newWineWineryId : number;
  public newWineCategories : category[];


  displayedColumns: string[] = ['id', 'name', 'productionYear', 'alcoholPercentage', 'volume', 'price','pictureUrl','winery'];
  dataSource: MatTableDataSource<wine>;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(
    private wineService: WineService,
    private router: Router,
    public dialog: MatDialog
  ) {
    this.wineService.getAllWines().subscribe((res) => {
      this.dataSource = new MatTableDataSource(res);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    });
  }

  ngOnInit(): void {
    console.log("Home initialized...");
  }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }
  editWineRedirect(id : any){
    console.log(id);
    this.router.navigate([`wine/${id}`]);
  }

  addWine(){
    const dialogRef = this.dialog.open(DialogAddWine, {
      width: '700px',
      data: {
        name : this.newWineName,
        productionYear : this.newWineProductionYear,
        alcoholPercentage : this.newWineAlcoholPercentage,
        volume : this.newWineVolume,
        price : this.newWinePrice,
        pictureUrl : this.newWinePictureUrl,
        wineryId : this.newWineWineryId,
        wineCategories : this.newWineCategories
      }
    });


    dialogRef.afterClosed().subscribe(result => {
      console.log(result);
      if(result !== undefined){
        this.wineService.addWine(
          result.name,
          result.productionYear,
          result.alcoholPercentage,
          result.volume,
          result.price,
          result.pictureUrl,
          result.wineryId,
          []).subscribe();
      }
    })
  }



}



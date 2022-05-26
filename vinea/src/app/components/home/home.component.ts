import { AfterViewInit, Component, ViewChild, OnInit } from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';
import {FlexLayoutModule} from '@angular/flex-layout';
import { HttpParams } from '@angular/common/http';
import {WineService} from '../../services/wine.service';
import {wine} from '../../classes/wine';
import {cloneDeep} from 'lodash';



@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit, AfterViewInit {
  public wines : Array<wine>;
  displayedColumns: string[] = ['id', 'name', 'productionYear', 'alcoholPercentage', 'volume', 'price','pictureUrl','winery'];
  dataSource: MatTableDataSource<wine>;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(
    private wineService: WineService
  ) {
    this.wineService.getAllWines().subscribe((res) => {
      this.dataSource = new MatTableDataSource(res);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    });
  }

  ngOnInit(): void {
    console.log("Home initialized...");
    //this.getAllWines();

  }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
    //console.log(this.wines);
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }
  // getAllWines() {
  //   this.wineService.getAllWines()
  //     .subscribe((data : wine[])=>{
  //       //console.log(data);
  //       this.wines = cloneDeep(data);

  //     },
  //     error => {
  //       console.log(error);
  //     });
  // }

}



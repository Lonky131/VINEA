import { AfterViewInit, Component, ViewChild, OnInit } from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';
import {FlexLayoutModule} from '@angular/flex-layout';
import { HttpParams } from '@angular/common/http';
import {WineriesService} from '../../services/wineries.service';
import { winery } from 'src/app/classes/winery';
import {cloneDeep} from 'lodash';

@Component({
  selector: 'app-wineries',
  templateUrl: './wineries.component.html',
  styleUrls: ['./wineries.component.scss']
})
export class WineriesComponent implements OnInit, AfterViewInit {
  public wineries : Array<winery>;
  displayedColumns: string[] = ['id', 'name', 'foundingYear', 'region'];
  dataSource: MatTableDataSource<winery>;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(
    private wineriesService: WineriesService
  ) {
    this.wineriesService.getAllWineries().subscribe((res)=>{
      this.dataSource = new MatTableDataSource(res);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    });
  }

  ngOnInit(): void {
    console.log("Wineries initialized...");

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
  // getAllWineries() {
  //   this.wineriesService.getAllWineries()
  //     .subscribe((data : winery[])=>{
  //       console.log(data);
  //       this.wineries = cloneDeep(data);

  //     },
  //     error => {
  //       console.log(error);
  //     });
  // }

}


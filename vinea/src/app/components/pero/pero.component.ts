import { AfterViewInit, Component, ViewChild, OnInit } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { WineriesService } from '../../services/wineries.service';
import { winery } from 'src/app/classes/winery';
import { DialogOverviewExampleDialog } from 'src/app/dialogs/dialogYesNo/dialog-overview-example-dialog';
import { MatDialog } from '@angular/material/dialog';
import { DialogAddWinery } from 'src/app/dialogs/addWinery/dialogAddWinery';
import { order } from 'src/app/classes/order';
import { OrderService } from 'src/app/services/order.service';


const DUMMY_DATA: order[] = [
  {id: 1, firstName: 'Pero', lastName: 'Peric', email: 'peric@gmail.com', phoneNumber: '0913141454', address: 'Perska 20', status: 'pending'}
];

@Component({
  selector: 'app-pero',
  templateUrl: './pero.component.html',
  styleUrls: ['./pero.component.scss']
})
export class PeroComponent implements OnInit, AfterViewInit {
  displayedColumns: string[] = ['id', 'firstName', 'lastName', 'email', 'phoneNumber', 'address', 'status'];
  //dataSource: MatTableDataSource<winery>;

  dataSource : MatTableDataSource<order>;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(
    private orderService: OrderService,
    public dialog: MatDialog
  ) {
    // this.wineriesService.getAllWineries().subscribe((res)=>{
    //   this.dataSource = new MatTableDataSource(res);
    //   this.dataSource.paginator = this.paginator;
    //   this.dataSource.sort = this.sort;
    // });
    this.dataSource = new MatTableDataSource(DUMMY_DATA);

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
  approveOrder(orderId: number) : void {
    this.orderService.changeOrderStatus(orderId, "Approved").subscribe();
  }

  denyOrder(orderId: number) : void {
    this.orderService.changeOrderStatus(orderId, "Denied").subscribe();
  }
}



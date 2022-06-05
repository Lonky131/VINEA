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
import { tableTask, task } from 'src/app/classes/task';


const DUMMY_DATA: order[] = [
  {id: 1, firstName: 'Pero', lastName: 'Peric', email: 'peric@gmail.com', phoneNumber: '0913141454', address: 'Perska 20', status: 'pending'},
  {id: 2, firstName: 'Ana', lastName: 'Meric', email: 'meric@gmail.com', phoneNumber: '09131416464', address: 'Merska 20', status: 'pending'},
  {id: 3, firstName: 'Marta', lastName: 'Seric', email: 'seric@gmail.com', phoneNumber: '0913147714', address: 'Serska 20', status: 'pending'},
  {id: 4, firstName: 'Bruno', lastName: 'Teric', email: 'teric@gmail.com', phoneNumber: '09131412414', address: 'Terska 20', status: 'pending'}
];

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.scss']
})
export class AdminComponent implements OnInit, AfterViewInit {
  displayedColumns: string[] = ['taskId','firstName', 'lastName', 'idCardNumber', 'actions'];
  dataSource: MatTableDataSource<tableTask>;
  public taskArray = new Array<tableTask>();

  //dataSource : MatTableDataSource<order>;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(
    private orderService: OrderService,
    public dialog: MatDialog
  ) {
    this.orderService.getAllTasks().subscribe((res)=>{

      for (let i = 0; i < res.length; i++) {
        let currentTask : tableTask = new tableTask;
        Object.assign(currentTask, {taskId : res[i].id.toString()});
        this.orderService.getTaskFirstname(res[i].id.toString()).subscribe(result => {
          Object.assign(currentTask, {firstName : result.value});
        });
        this.orderService.getTaskLastname(res[i].id.toString()).subscribe(result => {
          Object.assign(currentTask, {lastName : result.value});
        });
        this.orderService.getTaskIdCardNumber(res[i].id.toString()).subscribe(result => {
          Object.assign(currentTask, {idCardNumber : result.value});
        });
        this.taskArray.push(currentTask);
      }
      console.log(this.taskArray);
      this.dataSource = new MatTableDataSource(this.taskArray);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    });
    //this.dataSource = new MatTableDataSource(DUMMY_DATA);

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
  approveOrder(taskId: string) : void {
    this.orderService.completeOrder(taskId, true).subscribe();
  }

  denyOrder(taskId: string) : void {
    this.orderService.completeOrder(taskId, false).subscribe();
  }
}


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
import { task, userTableTask } from 'src/app/classes/task';


const DUMMY_DATA: order[] = [
  {id: 1, firstName: 'Pero', lastName: 'Peric', email: 'peric@gmail.com', phoneNumber: '0913141454', address: 'Perska 20', status: 'pending'}
];

@Component({
  selector: 'app-pero',
  templateUrl: './pero.component.html',
  styleUrls: ['./pero.component.scss']
})
export class PeroComponent implements OnInit, AfterViewInit {
  displayedColumns: string[] = ['taskId', 'firstName', 'lastName', 'status', 'actions'];
  public taskArray = new Array<userTableTask>();


  dataSource : MatTableDataSource<userTableTask>;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(
    private orderService: OrderService,
    public dialog: MatDialog
  ) {
    this.orderService.getAllNotificationsPositive().subscribe((res)=>{
      if(res.length > 0) {
        console.log(res);
        for (let i = 0; i < res.length; i++) {
          let currentTask : userTableTask = new userTableTask;
          Object.assign(currentTask, {taskId : res[i].id.toString()});
          this.orderService.getTaskFirstname(res[i].id.toString()).subscribe(result => {
            Object.assign(currentTask, {firstName : result.value});
          });
          this.orderService.getTaskLastname(res[i].id.toString()).subscribe(result => {
            Object.assign(currentTask, {lastName : result.value});
          });
          Object.assign(currentTask, {status : "Approved"});
          console.log(currentTask);
          this.taskArray.push(currentTask);
        }
        console.log(this.taskArray);
        this.dataSource = new MatTableDataSource(this.taskArray);
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;

      } else {
        this.orderService.getAllNotificationsNegative().subscribe((res)=>{
          if(res.length > 0) {
            console.log(res);
            for (let i = 0; i < res.length; i++) {
              let currentTask : userTableTask = new userTableTask;
              Object.assign(currentTask, {taskId : res[i].id.toString()});
              this.orderService.getTaskFirstname(res[i].id.toString()).subscribe(result => {
                Object.assign(currentTask, {firstName : result.value});
              });
              this.orderService.getTaskLastname(res[i].id.toString()).subscribe(result => {
                Object.assign(currentTask, {lastName : result.value});
              });
              Object.assign(currentTask, {status : "Denied"});
              this.taskArray.push(currentTask);
            }
          }
          console.log(this.taskArray);
          this.dataSource = new MatTableDataSource(this.taskArray);
          this.dataSource.paginator = this.paginator;
          this.dataSource.sort = this.sort;

        });
      }
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


  approveNotification(taskId : string) : void {
    this.orderService.completeNotification(taskId).subscribe();
  }
}



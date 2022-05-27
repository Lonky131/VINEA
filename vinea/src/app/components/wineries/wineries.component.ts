import { AfterViewInit, Component, ViewChild, OnInit } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { WineriesService } from '../../services/wineries.service';
import { winery } from 'src/app/classes/winery';
import { DialogOverviewExampleDialog } from 'src/app/dialogs/dialogYesNo/dialog-overview-example-dialog';
import { MatDialog } from '@angular/material/dialog';
import { DialogAddWinery } from 'src/app/dialogs/addWinery/dialogAddWinery';


@Component({
  selector: 'app-wineries',
  templateUrl: './wineries.component.html',
  styleUrls: ['./wineries.component.scss']
})
export class WineriesComponent implements OnInit, AfterViewInit {
  public wineries : Array<winery>;
  public newWineryName : string;
  public newWineryFoundingYear : number;
  public newWineryRegionId : number;

  displayedColumns: string[] = ['id', 'name', 'foundingYear', 'region', 'actions'];
  dataSource: MatTableDataSource<winery>;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(
    private wineriesService: WineriesService,
    public dialog: MatDialog
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

  addWinery(){
    const dialogRef = this.dialog.open(DialogAddWinery, {
      width: '700px',
      data: {
        name : this.newWineryName,
        foundingYear : this.newWineryFoundingYear,
        regionId : this.newWineryRegionId
      }
    });


    dialogRef.afterClosed().subscribe(result => {
      if(result !== undefined){
        this.wineriesService.addWinery(result.name, parseInt(result.foundingYear), result.regionId).subscribe();
      }
    })
  }

  deleteWineryDialog(wineryId: number) : void{
    const dialogRef = this.dialog.open(DialogOverviewExampleDialog, {
      width: '300px',
      data: {
        warningMessage : 'This action will delete this winery and ALL the wines from it!'
      }
    });

    dialogRef.afterClosed().subscribe(result => {
      if(result == true){
        //IF USER CONFIRMS DELETION CALL THE DELETE REQUEST
        this.wineriesService.deleteWineryById(wineryId).subscribe();
      }
    })
  }


}


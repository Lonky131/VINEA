import {
  ComponentFixture,
  fakeAsync,
  TestBed,
  tick,
  waitForAsync,
} from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { WineriesComponent } from './wineries.component';
import {
  HttpClientTestingModule,
  HttpTestingController,
} from '@angular/common/http/testing';
import { HttpClient } from '@angular/common/http';
import { MatDialog } from '@angular/material/dialog';

describe('WineriesComponent', () => {
  let component: WineriesComponent;
  let fixture: ComponentFixture<WineriesComponent>;
  let httpClient: HttpClient;
  let httpTestingController: HttpTestingController;
  let matDialog : MatDialog;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ WineriesComponent ],
      imports: [HttpClientTestingModule],
      providers: [{
        provide: MatDialog,
        useValue: {},
      }]
    })
    .compileComponents();

    httpClient = TestBed.inject(HttpClient);
    httpTestingController = TestBed.inject(HttpTestingController);
    matDialog = TestBed.inject(MatDialog);

    fixture = TestBed.createComponent(WineriesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
    }
  ));
  afterEach(() => {
    httpTestingController.verify();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

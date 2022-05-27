import { TestBed } from '@angular/core/testing';

import { WineriesService } from './wineries.service';

import {
  HttpClientTestingModule,
  HttpTestingController
  } from "@angular/common/http/testing";
import { HttpClient } from "@angular/common/http";
import { winery } from '../classes/winery';

describe('WineriesService', () => {
  let service: WineriesService;
  let httpClient: HttpClient;
  let httpTestingController: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule]
    });
    service = TestBed.inject(WineriesService);
    httpClient = TestBed.inject(HttpClient);
    httpTestingController = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpTestingController.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should make an API call GET ALL WINERIES', () => {
    const mockResponse = [
      {
        id: 1,
        name: 'Dalmatino',
        foundingYear: 2010,
        region: {
          id: 1,
          name: 'Istra',
          country : 'Croatia'
        },
      },
    ];

    service.getAllWineries().subscribe((res) => {
      expect(res).toBeTruthy();
      expect(res).toHaveSize(1);
      const winery = res[0];
      expect(winery).toBe(mockResponse[0]);
    });

    const mockRequest = httpTestingController.expectOne(
      '/api/winery'
    );

    expect(mockRequest.request.method).toEqual('GET');

    // Resolve with our mock data
    mockRequest.flush(mockResponse);
  });

  it('should make an POST API call to add winery', () => {
    const newWinery = {
      name: 'TestWinery',
      foundingYear: 2011,
      region: {
        id: 1,
        name: 'TestRegion',
        country: 'Croatia'
      }
    }

    service.addWinery(newWinery.name, newWinery.foundingYear, newWinery.region.id).subscribe((res) => {
      expect(res).toBeTruthy();
    });

    const req = httpTestingController.expectOne('/api/winery');
    expect(req.request.method).toEqual('POST');


    // Resolve with our mock data
    req.flush(req);
  });

});

import { TestBed } from '@angular/core/testing';

import { WineService } from './wine.service';

import {
  HttpClientTestingModule,
  HttpTestingController
  } from "@angular/common/http/testing";
import { HttpClient } from "@angular/common/http";
import { wine } from '../classes/wine';


describe('WineService', () => {
  let service: WineService;
  let httpClient: HttpClient;
  let httpTestingController: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule]
    });
    service = TestBed.inject(WineService);
    httpClient = TestBed.inject(HttpClient);
    httpTestingController = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpTestingController.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should make an API call GET ALL WINES', () => {
    const mockResponse = [
      {
        id : 2,
        name : "Best champagne ever",
        productionYear : 2015,
        alcoholPercentage : 20.2,
        volume : 2000,
        price : 500.0,
        pictureUrl : "https://cdn.pixabay.com/photo/2012/04/15/21/02/champagne-35313_960_720.png",
        winery : {
          id : 2,
          name : "Dalmoš",
          foundingYear : 1967,
          region : {
            id : 4,
            name : "Dalmacija",
            country : "Hrvatska"
          }
        }
      }
    ];

    service.getAllWines().subscribe((res) => {
      expect(res).toBeTruthy();
      expect(res).toHaveSize(1);
      const wine = res[0];
      expect(wine).toBe(mockResponse[0]);
    });

    const mockRequest = httpTestingController.expectOne(
      '/api/wine'
    );

    expect(mockRequest.request.method).toEqual('GET');

    // Resolve with our mock data
    mockRequest.flush(mockResponse);
  });


  it('should make an POST API call to add wine', () => {
    const newWine = {
      name: "Vino za popit s frendovima",
      productionYear: 2019,
      alcoholPercentage: 16,
      volume: 3000,
      price: 49.99,
      pictureUrl: "https://img.freepik.com/free-psd/wine-bottle-mockup_58466-14388.jpg?t=st=1652562381~exp=1652562981~hmac=3e9d72777aedcc7e8b54fc9e046a3240b556bbcab9256450df85a7eae68b97ea&w=1380",
      wineryId: 3,
      categories: [
      ]
  }

    service.addWine(newWine.name, newWine.productionYear, newWine.alcoholPercentage, newWine.volume, newWine.price, newWine.pictureUrl, newWine.wineryId, []).subscribe((res) => {
      expect(res).toBeTruthy();
      //expect(res).toBe(1);
    });

    const req = httpTestingController.expectOne('/api/wine');
    expect(req.request.method).toEqual('POST');


    // Resolve with our mock data
    req.flush(req);
  });
});

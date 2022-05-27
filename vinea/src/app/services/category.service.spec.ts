import { TestBed } from '@angular/core/testing';

import { CategoryService } from './category.service';
import {
  HttpClientTestingModule,
  HttpTestingController
  } from "@angular/common/http/testing";
import { HttpClient } from "@angular/common/http";

describe('CategoryService', () => {
  let service: CategoryService;
  let httpClient: HttpClient;
  let httpTestingController: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule]
    });
    service = TestBed.inject(CategoryService);
    httpClient = TestBed.inject(HttpClient);
    httpTestingController = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpTestingController.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should make an API call GET ALL CATEGORIES', () => {
    const mockResponse = [
      {
        id: 1,
        name: 'boja'
      }
    ];

    service.getAllCategories().subscribe((res) => {
      expect(res).toBeTruthy();
      expect(res).toHaveSize(1);
      const category = res[0];
      expect(category).toBe(mockResponse[0]);
    });

    const mockRequest = httpTestingController.expectOne(
      '/api/category'
    );

    expect(mockRequest.request.method).toEqual('GET');

    // Resolve with our mock data
    mockRequest.flush(mockResponse);
  });

});

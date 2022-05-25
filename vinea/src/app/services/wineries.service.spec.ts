import { TestBed } from '@angular/core/testing';

import { WineriesService } from './wineries.service';

describe('WineriesService', () => {
  let service: WineriesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(WineriesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

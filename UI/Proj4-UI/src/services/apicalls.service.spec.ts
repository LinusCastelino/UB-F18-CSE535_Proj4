import { TestBed, inject } from '@angular/core/testing';

import { APICallsService } from './apicalls.service';

describe('APICallsService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [APICallsService]
    });
  });

  it('should be created', inject([APICallsService], (service: APICallsService) => {
    expect(service).toBeTruthy();
  }));
});

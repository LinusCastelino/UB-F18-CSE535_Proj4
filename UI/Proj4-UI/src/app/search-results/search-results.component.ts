import { Component, OnInit } from '@angular/core';
import { APICallsService } from '../../services/apicalls.service'

@Component({
  selector: 'app-search-results',
  templateUrl: './search-results.component.html',
  styleUrls: ['./search-results.component.css']
})
export class SearchResultsComponent implements OnInit {
  English = false; Spanish=false;
  Hindi = false; Russian=false;
  French=false; delhi=false;
  nyc=false; bangkok=false;
  mexico_city=false; paris=false;
  constructor(private apiService: APICallsService) { }

  ngOnInit() {
  }

}

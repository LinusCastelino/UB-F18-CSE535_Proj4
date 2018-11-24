import { Component, OnInit } from '@angular/core';
import { APICallsService } from '../../services/apicalls.service'

@Component({
  selector: 'app-search-results',
  templateUrl: './search-results.component.html',
  styleUrls: ['./search-results.component.css']
})
export class SearchResultsComponent implements OnInit {
  English : boolean = false; 
  Spanish : boolean =false;
  Hindi : boolean = false; 
  Russian : boolean=false;
  French : boolean=false; 
  delhi : boolean=false;
  nyc : boolean =false; 
  bangkok : boolean=false;
  mexico_city : boolean=false; 
  paris : boolean=false;

  resultsAvailable : boolean = false;

  constructor(private apiService: APICallsService) { }

  ngOnInit() {
  }
  


}

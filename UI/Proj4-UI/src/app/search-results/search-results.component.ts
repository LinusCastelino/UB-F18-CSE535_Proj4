import { Component, OnInit, Input } from '@angular/core';
import { APICallsService } from '../../services/apicalls.service'
import { ITweet } from '../../models/ITweet'

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

  @Input() resultsAvailable : boolean = false;
  @Input() apiResponse;

  constructor(private apiService: APICallsService) { }

  ngOnInit() {
  }



}

import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { APICallsService } from '../../services/apicalls.service'
import { ITweet } from '../../models/ITweet'
import { throwToolbarMixedModesError } from '@angular/material';

@Component({
  selector: 'app-search-results',
  templateUrl: './search-results.component.html',
  styleUrls: ['./search-results.component.css']
})

export class SearchResultsComponent implements OnInit {
  english : boolean = false; 
  french : boolean=false; 
  hindi : boolean = false; 
  spanish : boolean =false;
  thai : boolean=false;
  bangkok : boolean=false;
  delhi : boolean =false; 
  mexico_city : boolean=false;
  nyc : boolean=false; 
  paris : boolean=false;

  @Input() apiResponse;
  @Output() filtersEmitter : EventEmitter<string[]> = new EventEmitter<string[]>();

  searchResponse : ITweet[] = [];


  constructor(private apiService: APICallsService) { }

  ngOnInit() {
  }

  onChange(){
    var filter : string[];
    let langFilter : string = '"';
    if(this.english==true)  langFilter+='"en",';
    if(this.french==true)  langFilter+='"fr",';
    if(this.hindi==true)  langFilter+='"hi",';
    if(this.spanish==true)  langFilter+='"es",';
    if(this.thai==true)  langFilter+='"th",';
    langFilter=langFilter.substring(0,(langFilter.length)-1)+'"';
    if(langFilter=='"') langFilter='""';
    
    let cityFilter : string = '"';
    if(this.bangkok==true)  cityFilter+='"bangkok",';
    if(this.delhi==true)  cityFilter+='"delhi",';
    if(this.mexico_city==true)  cityFilter+='"mexico city",';
    if(this.nyc==true)  cityFilter+='"nyc",';
    if(this.paris==true)  cityFilter+='"paris",';
    cityFilter=cityFilter.substring(0,(cityFilter.length)-1)+'"';
    if(cityFilter=='"') cityFilter='""';
    filter=[langFilter,cityFilter];
    this.filtersEmitter.emit(filter);
  }

}

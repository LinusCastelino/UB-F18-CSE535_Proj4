import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { APICallsService } from '../../services/apicalls.service'
import { PageEvent, getMatTooltipInvalidPositionError } from '@angular/material';
import {MatDatepickerInputEvent} from '@angular/material/datepicker';
import { FormControl } from '@angular/forms';



@Component({
  selector: 'app-search-results',
  templateUrl: './search-results.component.html',
  styleUrls: ['./search-results.component.css'],
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
  panelOpenState: boolean;
  date = new FormControl(new Date());
  serializedDate = new FormControl((new Date()).toISOString());
  crime : boolean = false;
  environment : boolean = false; 
  politics : boolean=false; 
  sUnrest : boolean = false; 
  infra : boolean =false;
  verified : boolean=false; 
  regular : boolean = false; 
  

  @Input() inputQuery : string;
  @Input() apiResponse : any;
  @Input() retrievalTime : number;
  @Input() langCount : any;
  @Input() cityCount : any;

  @Output() filtersEmitter : EventEmitter<string[]> = new EventEmitter<string[]>();

  pageSize : number = 5;
  pageIndex : number = 0;

  constructor(private apiService: APICallsService) { }

  ngOnInit() {
  }

  public handlePaginatorEvent(event : PageEvent){
    this.pageSize = event.pageSize;
    this.pageIndex = event.pageIndex;
    this.filtersChanged();
  }
  
  public filtersChanged(){
    var filter : any[];
    let langFilter : string = '';
    if(this.english==true)  langFilter+='"en",';
    if(this.french==true)  langFilter+='"fr",';
    if(this.hindi==true)  langFilter+='"hi",';
    if(this.spanish==true)  langFilter+='"es",';
    if(this.thai==true)  langFilter+='"th",';
    langFilter=langFilter.substring(0,(langFilter.length)-1)+'';
    if(langFilter=='"') langFilter='""';
    
    let cityFilter : string = '';
    if(this.bangkok==true)  cityFilter+='"bangkok",';
    if(this.delhi==true)  cityFilter+='"delhi",';
    if(this.mexico_city==true)  cityFilter+='"mexico city",';
    if(this.nyc==true)  cityFilter+='"nyc",';
    if(this.paris==true)  cityFilter+='"paris",';
    cityFilter=cityFilter.substring(0,(cityFilter.length)-1)+'';
    if(cityFilter=='"') cityFilter='""';

    let topicFilter : string = '';
    if(this.crime==true)  topicFilter+='"crime",';
    if(this.politics==true)  topicFilter+='"politics",';
    if(this.environment==true)  topicFilter+='"environment",';
    if(this.nyc==true)  topicFilter+='"nyc",';
    if(this.paris==true)  topicFilter+='"paris",';
    topicFilter=topicFilter.substring(0,(topicFilter.length)-1)+'';
    if(topicFilter=='"') topicFilter='""';

    let pageNo = this.pageIndex;
    let resultsPerPage = this.pageSize;

    filter=[langFilter, cityFilter, pageNo, resultsPerPage];
    this.filtersEmitter.emit(filter);
    console.log(this.serializedDate);
    console.log("heree");

  }

}

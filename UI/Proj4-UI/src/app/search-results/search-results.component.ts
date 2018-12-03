import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { APICallsService } from '../../services/apicalls.service'

import { PageEvent, getMatTooltipInvalidPositionError } from '@angular/material';

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
  crime : boolean = false;
  environment : boolean = false; 
  politics : boolean=false; 
  sUnrest : boolean = false; 
  infra : boolean =false;
  verified : boolean=false; 

  panelOpenState: boolean;
  
  datePicker1: boolean = false;
  datePicker2: boolean = false;
  dateFrom : string;
  dateTo : string;

  @Input() inputQuery : string;
  @Input() apiResponse : any;
  @Input() retrievalTime : number;
  @Input() langCount : any;
  @Input() cityCount : any;
  @Input() topicsCount : any;
  @Input() verifiedCount : number;

  @Output() filtersEmitter : EventEmitter<string[]> = new EventEmitter<string[]>();

  pageSize : number = 10;
  pageIndex : number = 0;

  constructor(private apiService: APICallsService) { }

  ngOnInit() {
  }

  public handlePaginatorEvent(event : PageEvent){
    this.pageSize = event.pageSize;
    this.pageIndex = event.pageIndex;
    this.filtersChanged();
  }

  public dateFromChanged(event: any){
    
    this.datePicker1 = true;
    this.dateFrom = new Date(event.value).toISOString();
    if(this.datePicker2){
      this.filtersChanged();
    }
  }

  public dateToChanged(event: any){
    this.datePicker2 = true;
    this.dateTo = new Date(event.value).toISOString();
    if(this.datePicker1){
      this.filtersChanged();
    }
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
    if(this.sUnrest==true)  topicFilter+='"social unrest",';
    if(this.infra==true)  topicFilter+='"infra",';
    topicFilter=topicFilter.substring(0,(topicFilter.length)-1)+'';
    if(topicFilter=='"') topicFilter='""';

    let verifiedFilter : string = '';
    if(this.verified==true)  verifiedFilter+='"true"';
    if(this.verified==false)  verifiedFilter+='"false"';
    

    let pageNo = this.pageIndex;
    let resultsPerPage = this.pageSize;

    let fromDate : string = null;
    let toDate : string = null; 
    if(this.datePicker1 && this.datePicker2){
      fromDate = this.dateFrom;
      toDate = this.dateTo;
      fromDate=fromDate.substring(0,12)+'0'+fromDate.substring(13,fromDate.length);
      toDate=toDate.substring(0,12)+'0'+toDate.substring(13,toDate.length);
      this.datePicker1 = false;
      this.datePicker2 = false;
    }

    filter=[langFilter, cityFilter, pageNo, resultsPerPage, topicFilter, verifiedFilter, fromDate, toDate];
    this.filtersEmitter.emit(filter);

  }

  public getDate(ISOdate : string){
    return new Date(ISOdate).toDateString();
  }

}

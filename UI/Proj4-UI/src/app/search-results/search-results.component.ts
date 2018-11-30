import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { APICallsService } from '../../services/apicalls.service'
import { PageEvent } from '@angular/material';
import {FormControl} from '@angular/forms';
import {MomentDateAdapter} from '@angular/material-moment-adapter';
import {DateAdapter, MAT_DATE_FORMATS, MAT_DATE_LOCALE} from '@angular/material/core';

// Depending on whether rollup is used, moment needs to be imported differently.
// Since Moment.js doesn't have a default export, we normally need to import using the `* as`
// syntax. However, rollup creates a synthetic default module and we thus need to import it using
// the `default as` syntax.
import * as _moment from 'moment';
// tslint:disable-next-line:no-duplicate-imports
//import {MomentBuiltinFormat as _rollupMoment} from 'moment';

const moment= _moment;

// See the Moment.js docs for the meaning of these formats:
// https://momentjs.com/docs/#/displaying/format/
export const MY_FORMATS = {
  parse: {
    dateInput: 'LL',
  },
  display: {
    dateInput: 'LL',
    monthYearLabel: 'MMM-YYYY',
    dateA11yLabel: 'LL',
    monthYearA11yLabel: 'MMM-DD-YYYY',
  },
};

@Component({
  selector: 'app-search-results',
  templateUrl: './search-results.component.html',
  styleUrls: ['./search-results.component.css'],
  providers: [
    // `MomentDateAdapter` can be automatically provided by importing `MomentDateModule` in your
    // application's root module. We provide it at the component level here, due to limitations of
    // our example generation script.
    {provide: DateAdapter, useClass: MomentDateAdapter, deps: [MAT_DATE_LOCALE]},

    {provide: MAT_DATE_FORMATS, useValue: MY_FORMATS},
  ],
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
  date = new FormControl(moment());


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
    
    let cityFilter : string = '"';
    if(this.bangkok==true)  cityFilter+='"bangkok",';
    if(this.delhi==true)  cityFilter+='"delhi",';
    if(this.mexico_city==true)  cityFilter+='"mexico city",';
    if(this.nyc==true)  cityFilter+='"nyc",';
    if(this.paris==true)  cityFilter+='"paris",';
    cityFilter=cityFilter.substring(0,(cityFilter.length)-1)+'"';
    if(cityFilter=='"') cityFilter='""';

    let pageNo = this.pageIndex;
    let resultsPerPage = this.pageSize;

    filter=[langFilter, cityFilter, pageNo, resultsPerPage];
    this.filtersEmitter.emit(filter);
   
   
    let  dates:string=this.date.value._d;
   // dates=dates.substring(5,15).toUpperCase().replace(' ','_');
    console.log(dates);

  }

}

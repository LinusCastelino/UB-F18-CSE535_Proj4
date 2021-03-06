import { Component, OnInit } from '@angular/core';
import { APICallsService } from '../../services/apicalls.service';

@Component({
  selector: 'app-toggle',
  templateUrl: './toggle.component.html',
  styleUrls: ['./toggle.component.css']
})
export class ToggleComponent implements OnInit {

  constructor(private apiService:APICallsService) { }

  ngOnInit() {
  }

  query : string = '';
  resultsAvailable : boolean = false;
  apiResponse:any = '';
  apiStats:any = '';
  apiSentiment : any = '';
  apiResponseTime: number = 0;
  searchInProgress : boolean = false; 
  statsAvailable : boolean = false;
  errorOccured : boolean = false;
  sentimentAvailable : boolean = false;

  langCount : any;
  cityCount : any;
  topicsCount : any;
  verifiedCount : number;

  public queryApi(inputText:string) : void{
    if(typeof(inputText) == 'string'){
      let startTime : any = new Date();
      this.searchInProgress = true;
      this.statsAvailable = false;
      this.sentimentAvailable = false;
      this.errorOccured = false;
      this.query = inputText;
      this.apiService.search(inputText, "", "", "0", "10","","", "", "").subscribe(
        response => { 
          this.searchInProgress = false;
          this.resultsAvailable = true;
          this.apiResponse = response;
          let endTime : any = new Date();
          this.apiResponseTime = endTime - startTime;

          this.langCount = this.apiResponse.lang;
          this.cityCount = this.apiResponse.city;
          this.topicsCount = this.apiResponse.topic;
          this.verifiedCount = this.apiResponse.verified.true;
      },
      err => {
        this.errorOccured = true;
        this.searchInProgress = false;
        this.apiResponseTime = 0;
        console.log("Error : " + JSON.stringify(err));
      });
    
      this.apiService.stats(inputText).subscribe(
        response => {
            this.apiStats=response;
            this.statsAvailable = true;
        },
        err=> {
           console.log("Error : " + JSON.stringify(err));
           this.statsAvailable = false;
        });

        this.apiService.sentiment(inputText).subscribe(
          response =>{
            this.apiSentiment = response;
            this.sentimentAvailable = true;
            console.log(response);
          },
          err => {
            console.log("Error : " + JSON.stringify(err));
            this.sentimentAvailable = false;
          }
        );
      }
  }

  public queryWithFilters(filters:string[]):void{
    //console.log("in toggle "+filters[0])
    let startTime : any = new Date();
    this.searchInProgress = true;
    this.errorOccured = false;
    this.apiService.search(this.query, filters[0], filters[1], filters[2], filters[3],
                          filters[4],filters[5], filters[6], filters[7]).subscribe(
      response => { 
        this.searchInProgress = false;
        this.resultsAvailable = true;
        this.apiResponse = response;
        let endTime : any = new Date();
        this.apiResponseTime = endTime - startTime;
    },
    err => {
      this.errorOccured = true;
      this.searchInProgress = false;
      this.apiResponseTime = 0;
      console.log("Error : " + JSON.stringify(err));
    });
  }
}

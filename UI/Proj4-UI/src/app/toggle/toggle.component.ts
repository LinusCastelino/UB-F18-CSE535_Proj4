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
  apiResponse; 

  public queryApi(inputText:string) : void{
    if(typeof(inputText) == 'string'){
      this.query = inputText;
      this.apiService.search(inputText).subscribe(response => { 
        this.resultsAvailable = true;
        this.apiResponse = response;
      });
    }
  }
}

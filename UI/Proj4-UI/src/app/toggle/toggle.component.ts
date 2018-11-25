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

  public queryApi(inputText:string) : void{
    if(typeof(inputText) == 'string'){
      this.apiService.search(inputText).subscribe(data => { 
        console.log("From toggle component : "+ JSON.stringify(data));
      });
    }
  }
}

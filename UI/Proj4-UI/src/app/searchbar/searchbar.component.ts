import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import {APICallsService } from '../../services/apicalls.service'

@Component({
  selector: 'app-searchbar',
  templateUrl: './searchbar.component.html',
  styleUrls: ['./searchbar.component.css']
})
export class SearchbarComponent implements OnInit {
  
  constructor(private apiService:APICallsService) { }

  @Output() apiResponse : EventEmitter<any> = new EventEmitter<any>(); 

  ngOnInit() {
  }

  public searchQuery(query){
    console.log(query);
    this.apiService.search(query).subscribe(data => {
      this.apiResponse.emit(data);
    });

  }
}

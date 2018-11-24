import { Component, OnInit } from '@angular/core';
import {APICallsService } from '../../services/apicalls.service'

@Component({
  selector: 'app-searchbar',
  templateUrl: './searchbar.component.html',
  styleUrls: ['./searchbar.component.css']
})
export class SearchbarComponent implements OnInit {
  
  constructor(private apiService:APICallsService) { }

  ngOnInit() {
  }

  public searchQuery(query){
    console.log(query);
    this.apiService.search(query).subscribe(data => {
      console.log(data);
    });

  }
}

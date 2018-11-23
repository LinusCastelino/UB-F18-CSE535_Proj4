import { Component, OnInit } from '@angular/core';
import { APICallsService } from '../../services/apicalls.service'

@Component({
  selector: 'app-search-results',
  templateUrl: './search-results.component.html',
  styleUrls: ['./search-results.component.css']
})
export class SearchResultsComponent implements OnInit {

  constructor(private apiService: APICallsService) { }

  ngOnInit() {
  }

}

import { Component, OnInit } from '@angular/core';
import { APICallsService } from '../../services/apicalls.service'

@Component({
  selector: 'app-search-statistics',
  templateUrl: './search-statistics.component.html',
  styleUrls: ['./search-statistics.component.css']
})
export class SearchStatisticsComponent implements OnInit {

  constructor(private apiService: APICallsService) { }

  ngOnInit() {
  }

}

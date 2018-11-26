import { Component, OnInit } from '@angular/core';
import { APICallsService } from '../../services/apicalls.service'

@Component({
  selector: 'app-search-statistics',
  templateUrl: './search-statistics.component.html',
  styleUrls: ['./search-statistics.component.css']
})
export class SearchStatisticsComponent implements OnInit {

  constructor(private apiService: APICallsService) { }

  map_data = [
    ['Germany', 200],
    ['US', 300],
    ['Brazil', 400],
    ['Canada', 500],
    ['France', 600],
    ['RU', 700]
  ];
  map_options = {
    colorAxis: {colors: ['#F9A6A6', '#FF0000']},
    backgroundColor: '#9BC9FD',
    datalessRegionColor: '#EBE9E9',
    defaultColor: '#f5f5f5',
  };
  
  ngOnInit() {
  }

}

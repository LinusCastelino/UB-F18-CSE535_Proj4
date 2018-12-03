import { Component, OnInit, Input } from '@angular/core';
import { APICallsService } from '../../services/apicalls.service'

@Component({
  selector: 'app-search-statistics',
  templateUrl: './search-statistics.component.html',
  styleUrls: ['./search-statistics.component.css']
})
export class SearchStatisticsComponent implements OnInit {

  constructor(private apiService: APICallsService) { }

  @Input() apiStats:any;
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
  
  pieChartData = [
      
      ['Work',    11],
      ['Eat',      2],
      ['Commute',  2],
      ['Watch TV', 2],
      ['Sleep',    7]   
    ];
  options= {title: 'My Daily Activities',
  pieSliceTextStyle:  {color: 'black'},
  is3D: true};

  donutOptions= {title: 'My Daily Activities',
  pieSliceTextStyle:  {color: 'black'},
  pieHole: 0.4,
  };

  ngOnInit() {
  }

}

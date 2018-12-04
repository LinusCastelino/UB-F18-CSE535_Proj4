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
    ['Mexico', 200],
    ['US', 300],
    ['India', 400],
    ['Thailand', 500],
    ['France', 600]
  ];
  map_options = {
    colorAxis: {colors: ['#F9A6A6', '#FF0000']},
    backgroundColor: '#9BC9FD',
    datalessRegionColor: '#EBE9E9',
    defaultColor: '#f5f5f5',
  };
  
  citiesPieData = [
      
      ['Work',   parseInt('11')],
      ['Eat',      parseInt('2')],
      ['Commute',  parseInt('2')],
      ['Watch TV', parseInt('2')],
      ['Sleep',   parseInt('7')]   
    ];
  
  citiesPieOptions= {title: 'My Daily Activities',
  pieSliceTextStyle:  {color: 'black'},
  is3D: true};

  sentimentDonutData = [
      
    ['Work',    11],
    ['Eat',      2],
    ['Commute',  2],
    ['Watch TV', 2],
    ['Sleep',    7]   
  ];

  sentimentDonutOptions= {title: 'My Daily Activities',
  pieSliceTextStyle:  {color: 'black'},
  pieHole: 0.4,
  };

  columnChartOptions={
    is3D: true,
    title: "Density of Precious Metals, in g/cm^3"
  };
  ngOnInit() {
  }

}

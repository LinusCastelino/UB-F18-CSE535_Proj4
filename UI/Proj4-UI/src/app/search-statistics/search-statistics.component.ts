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
  @Input() statsAvailable : boolean;
  @Input() apiSentiment : any;
  @Input() sentimentAvailable : boolean;


  map_options = {
    title: 'Geotagged tweets',
    colorAxis: {colors: ['#F9A6A6', '#FF0000']},
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
  
  tableOptions={
    title: 'Hashtags',
    showRowNumber: true,
  };
 
  citiesPieOptions= {title: 'Cities',
  pieSliceTextStyle:  {color: 'black'},
  is3D: true};



  sentimentDonutOptions= {title: 'Sentiment Analysis',
  pieSliceTextStyle:  {color: 'black'},
  pieHole: 0.4,
  };

  columnChartOptions={
    is3D: true,
    title: "Languages"
  };

  barChartOptions={
    is3D: true,
    title: "Topics"
  };
  
  
  ngOnInit() {
  }

}

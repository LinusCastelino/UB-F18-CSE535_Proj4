import { Component, OnInit } from '@angular/core';
import { APICallsService } from '../../services/apicalls.service'

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private apiService: APICallsService) { }

  ngOnInit() {
  }

}

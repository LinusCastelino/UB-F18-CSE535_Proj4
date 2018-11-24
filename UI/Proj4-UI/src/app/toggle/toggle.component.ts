import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-toggle',
  templateUrl: './toggle.component.html',
  styleUrls: ['./toggle.component.css']
})
export class ToggleComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

  public onApiResponse(data:any) : void{
    console.log("From toggle component : "+ data);
  }
}

import { Component, OnInit, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-searchbar',
  templateUrl: './searchbar.component.html',
  styleUrls: ['./searchbar.component.css']
})
export class SearchbarComponent implements OnInit {
  
  constructor() { }

  @Output() textInput : EventEmitter<string> = new EventEmitter<string>(); 

  ngOnInit() {
  }

  public search(inputText){
    this.textInput.emit(inputText);
  }
}

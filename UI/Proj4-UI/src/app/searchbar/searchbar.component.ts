import { Component, OnInit, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-searchbar',
  templateUrl: './searchbar.component.html',
  styleUrls: ['./searchbar.component.css']
})
export class SearchbarComponent implements OnInit {
  
  constructor() { }

  @Output() textInput : EventEmitter<string> = new EventEmitter<string>(); 

  inputText:string = '';
  
  ngOnInit() {
  }

  public search() : void{
    this.textInput.emit(this.inputText);
  }

  public handleEnter(keyCode: number) : void{
    if(keyCode == 13){
      this.search();
    }
  }
}

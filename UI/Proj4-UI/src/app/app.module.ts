import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { SearchResultsComponent } from './search-results/search-results.component';
import { SearchStatisticsComponent } from './search-statistics/search-statistics.component';

import { APICallsService } from '../services/apicalls.service'

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    SearchResultsComponent,
    SearchStatisticsComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    HttpClientModule
  ],
  providers: [APICallsService],
  bootstrap: [AppComponent]
})
export class AppModule { }

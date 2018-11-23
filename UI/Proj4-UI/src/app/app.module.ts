import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule, Routes } from '@angular/router';

import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { SearchResultsComponent } from './search-results/search-results.component';
import { SearchStatisticsComponent } from './search-statistics/search-statistics.component';
import { ToggleComponent } from './toggle/toggle.component';  

import { APICallsService } from '../services/apicalls.service';

const routes : Routes= [
  {path:'', redirectTo:'/home', pathMatch:'full'},
  {path:'home', component: HomeComponent},
  {path:'search', component: ToggleComponent}
];

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    SearchResultsComponent,
    SearchStatisticsComponent,
    ToggleComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    HttpClientModule,
    RouterModule.forRoot(routes)
  ],
  providers: [APICallsService],
  bootstrap: [AppComponent]
})
export class AppModule { }

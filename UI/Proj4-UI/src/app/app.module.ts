import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule, Routes } from '@angular/router';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import{ MatIconModule, MatDatepickerModule,MatCheckboxModule, MatNativeDateModule, MatExpansionModule} from '@angular/material/';
import { MatTabsModule } from '@angular/material/tabs';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatProgressBarModule } from '@angular/material/progress-bar';
import { MatPaginatorModule } from '@angular/material/paginator';
import { GoogleChartsModule } from 'angular-google-charts';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';


import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { SearchResultsComponent } from './search-results/search-results.component';
import { SearchStatisticsComponent } from './search-statistics/search-statistics.component';
import { ToggleComponent } from './toggle/toggle.component';  

import { APICallsService } from '../services/apicalls.service';
import { SearchbarComponent } from './searchbar/searchbar.component';

const routes : Routes= [
  {path:'', redirectTo:'/search', pathMatch:'full'},
  //{path:'home', component: HomeComponent},
  {path:'search', component: ToggleComponent}
];

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    SearchResultsComponent,
    SearchStatisticsComponent,
    ToggleComponent,
    SearchbarComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    HttpClientModule,
    RouterModule.forRoot(routes),
    MatFormFieldModule,
    MatInputModule,
    MatCardModule,
    MatButtonModule,
    MatTabsModule,
    MatIconModule,
    FormsModule,
    MatDatepickerModule,
    MatCheckboxModule, 
    MatNativeDateModule,
    MatExpansionModule,
    MatProgressBarModule,
    MatPaginatorModule,
    ReactiveFormsModule,
    MatProgressSpinnerModule,
    GoogleChartsModule.forRoot('AIzaSyD-9tSrke72PouQMnMX-a7eZSW0jkFMBWY')
  ],
  providers: [APICallsService],
  bootstrap: [AppComponent]
})
export class AppModule { }

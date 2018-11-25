import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { timeout } from 'rxjs/operators';

export const apiURL:string = "http://localhost:8080";

@Injectable({
  providedIn: 'root'
})

export class APICallsService {

  constructor(private httpClient : HttpClient) { }

  public search(inputquery, langFilter, cityFilter):Observable<any>{
    var URL = apiURL+'/ir';
    let headers = new HttpHeaders();
    headers.append('Content-Type', 'application/json');
    let params = new HttpParams().set('q',inputquery).set('lang',langFilter).set('city',cityFilter);
    return this.httpClient.get(URL, {headers, params}).pipe(timeout(5000));
  }

}

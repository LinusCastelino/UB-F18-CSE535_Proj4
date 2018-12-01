import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { timeout } from 'rxjs/operators';

export const apiURL:string = "http://team-kenspring-proj4.us-west-2.elasticbeanstalk.com";
//export const apiURL:string = "http://localhost:5000";

@Injectable({
  providedIn: 'root'
})

export class APICallsService {

  constructor(private httpClient : HttpClient) { }

  public search(inputquery, langFilter, cityFilter, pageNo, pageSize):Observable<any>{
    var URL = apiURL+'/select';
    let headers = new HttpHeaders();
    headers.append('Content-Type', 'application/json');
    let params = new HttpParams().set('q',inputquery).set('lang',langFilter)
                                 .set('city',cityFilter).set('pageNo',pageNo).set('pageSize',pageSize);
    return this.httpClient.get(URL, {headers, params}).pipe(timeout(10000));
  }

}

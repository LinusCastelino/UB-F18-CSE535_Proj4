import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { timeout } from 'rxjs/operators';

//export const apiURL:string = "http://team-kenspring-proj4.us-west-2.elasticbeanstalk.com";
export const apiURL:string = "http://localhost:5000";

@Injectable({
  providedIn: 'root'
})

export class APICallsService {

  constructor(private httpClient : HttpClient) { }

  public search(inputquery, langFilter, cityFilter, pageNo, pageSize, topicFilter, 
                verifiedFilter, dateFrom, dateTo):Observable<any>{
    var URL = apiURL+'/select';
    let headers = new HttpHeaders();
    headers.append('Content-Type', 'application/json');
    let params = new HttpParams().set('q',inputquery).set('lang',langFilter)
                                 .set('city',cityFilter).set('pageNo',pageNo).set('pageSize',pageSize)
                                 .set('topic',topicFilter).set('verified',verifiedFilter)
                                 .set('dateFrom',dateFrom).set('dateTo', dateTo);
    return this.httpClient.get(URL, {headers, params}).pipe(timeout(100000));
  }

  public stats(inputquery){
    var URL= apiURL+"/statistics";
    let headers = new HttpHeaders();
    headers.append('Content-Type', 'application/json');
    let params = new HttpParams().set('q',inputquery);
    return this.httpClient.get(URL, {headers, params}).pipe(timeout(20000));
  } 

  public sentiment(inputquery){
    var URL= apiURL+"/semantics";
    let headers = new HttpHeaders();
    headers.append('Content-Type', 'application/json');
    let params = new HttpParams().set('q',inputquery);
    return this.httpClient.get(URL, {headers, params});
  }

}

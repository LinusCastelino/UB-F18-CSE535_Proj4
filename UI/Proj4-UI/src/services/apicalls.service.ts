import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ITweet } from 'src/models/ITweet';

@Injectable({
  providedIn: 'root'
})
export class APICallsService {

  constructor(private httpClient : HttpClient) { }

  private apiURL:string = "";

  public search(inputquery):Observable<ITweet[]>{
    var URL = this.apiURL + "";
    let headers = new HttpHeaders();
    headers.append('Content-Type', 'application/json');
    let params = new HttpParams();
    params.set("q",inputquery);
    return this.httpClient.get<ITweet[]>(URL, {headers, params});
  }

}

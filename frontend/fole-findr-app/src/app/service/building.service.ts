import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class BuildingService {

  constructor(private readonly httpClient :HttpClient) { }

  public getFloorsOfBuilding(id: number): Observable<any>{
    return ;
  }
}

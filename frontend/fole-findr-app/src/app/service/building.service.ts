import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {BuildingFloor} from "../model/building-floor";
import {AppSettings} from "../constants/app-settings";
import {AppUrl} from "../constants/app-url";

@Injectable({
  providedIn: 'root'
})
export class BuildingService {

  constructor(private readonly httpClient: HttpClient) {
  }

  public getFloorsOfBuilding(id: number): Observable<BuildingFloor[]> {
    return this.httpClient.get<BuildingFloor[]>(AppSettings.BASE_URL + AppUrl.BUILDING_URL + '/' + id)
  }
}

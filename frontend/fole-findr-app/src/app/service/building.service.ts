import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {BuildingFloor} from "../model/building-floor";
import {AppSettings} from "../constants/app-settings";
import {AppUrl} from "../constants/app-url";
import {Room} from "../model/room";

@Injectable({
  providedIn: 'root'
})
export class BuildingService {

  constructor(private readonly httpClient: HttpClient) {
  }

  public getFloorsOfBuilding(id: number): Observable<BuildingFloor[]> {
    return this.httpClient.get<BuildingFloor[]>(AppSettings.BASE_URL + AppUrl.BUILDING_URL + '/' + id)
  }

  public getRoomsByFloor(id: number): Observable<any> {
    return this.httpClient.get<any>(AppSettings.BASE_URL + AppUrl.FLOOR_URL + '/' + id);
  }

  public getAvailableRooms(start: Date, end:Date, id: number): Observable<Room[]>{
    const body = {start: start,end: end}
    return this.httpClient.post<Room[]>(AppSettings.BASE_URL + AppUrl.AVAILABLE_ROOMS_URL + '/' +id, body);
  }
}

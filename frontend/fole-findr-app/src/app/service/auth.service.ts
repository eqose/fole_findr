import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {AppSettings} from "../constants/AppSettings";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private API_PRODUCT = '/api/v1/auth'

  constructor(private readonly httpClient: HttpClient) {
  }

  public authenticateUser(data: any): Observable<boolean> {
    return this.httpClient.post<boolean>(AppSettings.BASE_URL + this.API_PRODUCT, data);
  }
}

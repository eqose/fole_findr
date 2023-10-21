import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {AppSettings} from "../constants/app-settings";
import {AppUrl} from "../constants/app-url";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private readonly httpClient: HttpClient) {
  }

  public authenticateUser(data: any): Observable<any> {
    return this.httpClient.post<any>(AppSettings.BASE_URL + AppUrl.LOGIN_URL, data);
  }

  getToken(): string | null {
    return sessionStorage.getItem('jwtToken');
  }

  logout(): void {
    localStorage.removeItem('jwtToken');
  }

  isAuthenticated(): boolean {
    return !!localStorage.getItem('jwtToken');
  }
}

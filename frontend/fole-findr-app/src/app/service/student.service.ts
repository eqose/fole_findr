import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Student} from "../model/student";
import {AppSettings} from "../constants/app-settings";
import {AppUrl} from "../constants/app-url";

@Injectable({
  providedIn: 'root'
})
export class StudentService {

  constructor(private readonly httpClient :HttpClient) { }

  public getStudents(): Observable<Student[]> {
    return this.httpClient.get<Student[]>(AppSettings.BASE_URL + AppUrl.STUDENT_URL)
  }
}

import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Student} from "../model/student";
import {AppSettings} from "../constants/app-settings";
import {AppUrl} from "../constants/app-url";
import {StudentRegistration} from "../model/student-registration";
import {StudentFilter} from "../model/student-filter";

@Injectable({
  providedIn: 'root'
})
export class StudentService {

  constructor(private readonly httpClient :HttpClient) { }

  public getStudents(filter: StudentFilter): Observable<Student[]> {
    return this.httpClient.post<Student[]>(AppSettings.BASE_URL + AppUrl.STUDENT_URL + '/students', filter);
  }

  public saveStudent(student: StudentRegistration): Observable<any>{
    return this.httpClient.post<any>(AppSettings.BASE_URL + AppUrl.STUDENT_URL, student)
  }

}

import {BehaviorSubject} from "rxjs";
import {Student} from "../model/student";

export class DataSharingService{
  constructor() {
  }

  isLogged: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  menuItems: BehaviorSubject<any> = new BehaviorSubject<any>(null);
  menuItem: BehaviorSubject<string> = new BehaviorSubject<string>('');
  inDashboard: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  listStudents: BehaviorSubject<Student[]> = new BehaviorSubject<Student[]>([])
}

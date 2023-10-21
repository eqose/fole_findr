import {BehaviorSubject} from "rxjs";

export class DataSharingService{
  constructor() {
  }

  isLogged: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  menuItems: BehaviorSubject<any> = new BehaviorSubject<any>(null);
  menuItem: BehaviorSubject<string> = new BehaviorSubject<string>('');
}

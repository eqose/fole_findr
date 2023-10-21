import {BehaviorSubject} from "rxjs";

export class DataSharingService{
  constructor() {
  }

  isLogged: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
}

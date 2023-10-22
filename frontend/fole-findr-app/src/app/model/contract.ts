import {Room} from "./room";
import {Student} from "./student";

export class Contract {
  id!: number;
  room!: Room;
  students!: Student;
  startDate!: Date;
  endDate!: Date;
  note!: string;
}

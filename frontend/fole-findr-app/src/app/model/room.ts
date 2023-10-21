import {RoomType} from "./room-type";
import {BuildingFloor} from "./building-floor";

export class Room {
  id!: number;
  name!: string;
  roomType!: RoomType;
  buildingFloorDTO!: BuildingFloor;
}

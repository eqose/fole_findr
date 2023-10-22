import {Component, OnInit} from '@angular/core';
import {BuildingService} from "../../service/building.service";
import {Room} from "../../model/room";

@Component({
  selector: 'app-room-list',
  templateUrl: './room-list.component.html',
  styleUrls: ['./room-list.component.css']
})
export class RoomListComponent implements OnInit{
  public gridItems: Room[] = [];

  constructor(private readonly buildingService :BuildingService) {
  }
  ngOnInit(): void {
    this.loadRooms();
  }

  public loadRooms(){
    this.buildingService.getRoomsByFloor(Number(sessionStorage.getItem('floor'))).subscribe({
      next: (data)=> {
        this.gridItems = data;
        this.gridItems.forEach(el => {
          el.roomDescr = el.name + ' ' +el.roomType.name;
        })
      }
    })
  }

  public showStudents(){

  }

}

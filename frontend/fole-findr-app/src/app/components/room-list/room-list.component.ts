import {Component, OnInit} from '@angular/core';
import {BuildingService} from "../../service/building.service";
import {Room} from "../../model/room";
import {StudentService} from "../../service/student.service";
import {StudentFilter} from "../../model/student-filter";
import {Student} from "../../model/student";
import {DialogService} from "primeng/dynamicdialog";
import {StudentListComponent} from "../student-list/student-list.component";
import {StudentDetailComponent} from "../student-list/student-detail/student-detail.component";
import {DataSharingService} from "../../service/data-sharing-service";

@Component({
  selector: 'app-room-list',
  templateUrl: './room-list.component.html',
  styleUrls: ['./room-list.component.css'],
  providers: [DialogService]
})
export class RoomListComponent implements OnInit{
  public gridItems: Room[] = [];
  public studentList:Student[] = [];

  constructor(private readonly buildingService :BuildingService,
              private readonly studentService: StudentService,
              private readonly dataSharingService :DataSharingService,
              private readonly dialogService :DialogService) {
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

  public showStudents(item: Room){
    const filter = new StudentFilter();
    filter.roomId = item.id;
    this.studentService.getStudents(filter).subscribe({
      next: (data) => {
        this.studentList = data;
        console.log(this.studentList)
        this.dataSharingService.listStudents.next(this.studentList);
      }
    })

    this.dialogService.open(StudentListComponent, {
      header: 'Studentet e dhomes: '+ item.name,
      width: '70vw',
      modal: true
    })
  }

}

import {Component, OnInit} from '@angular/core';
import {Student} from "../../model/student";
import {StudentService} from "../../service/student.service";

@Component({
  selector: 'app-student-list',
  templateUrl: './student-list.component.html',
  styleUrls: ['./student-list.component.css']
})
export class StudentListComponent implements OnInit {
  public studentList: Student[] = [];

  constructor(private readonly studentService: StudentService) {
  }

  ngOnInit(): void {
    this.loadStudentsOfBuilding(1)
  }

  public loadStudentsOfBuilding(id: number) {
    this.studentService.getStudents().subscribe({
      next: (data) => {
        this.studentList = data;
        console.log(this.studentList)
      }
    })
  }

}

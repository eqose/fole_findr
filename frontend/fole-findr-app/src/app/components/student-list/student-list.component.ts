import {Component, OnInit} from '@angular/core';
import {Student} from "../../model/student";
import {StudentService} from "../../service/student.service";
import {DialogService} from "primeng/dynamicdialog";
import {StudentDetailComponent} from "./student-detail/student-detail.component";
import {StudentFilter} from "../../model/student-filter";

@Component({
  selector: 'app-student-list',
  templateUrl: './student-list.component.html',
  styleUrls: ['./student-list.component.css'],
  providers: [DialogService]
})
export class StudentListComponent implements OnInit {
  public studentList: Student[] = [];
  public filter: StudentFilter = new StudentFilter();

  constructor(private readonly studentService: StudentService,
              private readonly dialogService: DialogService) {
  }

  ngOnInit(): void {
    this.initFilter();
    this.loadStudentsOfBuilding()
  }

  public initFilter() {
    this.filter.start = this.getFirstAndLastDateOfMonth(new Date().getFullYear(), new Date().getMonth()).firstDate;
    this.filter.end = this.getFirstAndLastDateOfMonth(new Date().getFullYear(), new Date().getMonth()).lastDate;
    this.filter.godinaId = Number(sessionStorage.getItem("building"))
  }

  public loadStudentsOfBuilding() {
    this.studentService.getStudents(this.filter).subscribe({
      next: (data) => {
        this.studentList = data;
        console.log(this.studentList)
      }
    })
  }

  private getFirstAndLastDateOfMonth(year: number, month: number): { firstDate: Date, lastDate: Date } {
    const firstDate = new Date(year, month - 1, 2);
    const lastDate = new Date(year, month, 1);

    return {firstDate, lastDate};
  }

  public openStudentProfile(student?: Student) {
    this.dialogService.open(StudentDetailComponent, {
      data: {student: student ? student : null},
      header: student ? 'Profili i: ' + student.firstName + ' ' + student.lastName : 'Student i ri',
      width: '50vw',
      modal: true
    }).onClose.subscribe(() => {
      this.loadStudentsOfBuilding()
    })
  }
}

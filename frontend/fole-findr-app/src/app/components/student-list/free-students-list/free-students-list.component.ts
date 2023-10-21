import {Component, OnInit} from '@angular/core';
import {StudentDetailComponent} from "../student-detail/student-detail.component";
import {Student} from "../../../model/student";
import {StudentFilter} from "../../../model/student-filter";
import {StudentService} from "../../../service/student.service";
import {ContractDetailComponent} from "../../contracts/contract-detail/contract-detail.component";
import {DialogService} from "primeng/dynamicdialog";

@Component({
  selector: 'app-free-students-list',
  templateUrl: './free-students-list.component.html',
  styleUrls: ['./free-students-list.component.css'],
  providers: [DialogService]
})
export class FreeStudentsListComponent implements OnInit {
  public studentList: Student[] = [];
  public filter: StudentFilter = new StudentFilter();

  constructor(private readonly studentService: StudentService,
              private readonly dialogService: DialogService) {
  }

  ngOnInit(): void {
    this.loadStudentsOfBuilding()
  }

  public loadStudentsOfBuilding() {
    this.studentService.getStudents(this.filter).subscribe({
      next: (data) => {
        this.studentList = data;
        console.log(this.studentList)
      }
    })
  }

  public onNewStudent() {
    this.dialogService.open(StudentDetailComponent, {
      header: 'Student i ri',
      width: '50vw',
      modal: true
    }).onClose.subscribe(() => {
      this.loadStudentsOfBuilding()
    })
  }

  public onAddContract(student: Student) {
    this.dialogService.open(ContractDetailComponent, {
      data: {
        studentId: student.id,
        studentName: student.firstName + ' ' + student.lastName
      },
      header: 'Kontrate e re',
      width: '50vw',
      modal: true
    }).onClose.subscribe(() => {
      this.loadStudentsOfBuilding()
    })
  }
}

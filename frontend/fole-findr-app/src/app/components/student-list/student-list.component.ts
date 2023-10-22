import {Component, OnInit} from '@angular/core';
import {Student} from "../../model/student";
import {StudentService} from "../../service/student.service";
import {DialogService, DynamicDialogConfig} from "primeng/dynamicdialog";
import {StudentDetailComponent} from "./student-detail/student-detail.component";
import {StudentFilter} from "../../model/student-filter";
import {DataSharingService} from "../../service/data-sharing-service";
import {ContractService} from "../../service/contract.service";

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
              private readonly contractService :ContractService,
              private readonly dataSharingService :DataSharingService,
              private readonly dialogService: DialogService) {
  }

  ngOnInit(): void {
    this.dataSharingService.listStudents.subscribe((list)=> {
      if (list.length>0){
        this.studentList = list
      }
      else {
        this.initFilter();
        this.loadStudentsOfBuilding()
      }
    })
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

  public printPDF(student: Student){
    this.contractService.getContractsByStrudent(student.id).subscribe({
      next: (data)=> {
        this.contractService.generatePDF(data[0].id).subscribe({
          next: (bytes)=> {
            const blob = new Blob([bytes], { type: 'text/csv' });
            const url= URL.createObjectURL(blob);
            window.open(url);
          }
        })
      }
    })
  }
}

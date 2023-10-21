import {Component, OnInit} from '@angular/core';
import {StudentRegistration} from "../../../model/student-registration";
import {StudentService} from "../../../service/student.service";
import {MessageService} from "primeng/api";
import {DynamicDialogRef} from "primeng/dynamicdialog";

@Component({
  selector: 'app-student-detail',
  templateUrl: './student-detail.component.html',
  styleUrls: ['./student-detail.component.css'],
  providers: [DynamicDialogRef, MessageService ]
})
export class StudentDetailComponent implements OnInit {
  public student!: StudentRegistration;
  public loader = false;

  constructor(private readonly studentService: StudentService,
              private readonly ref: DynamicDialogRef,
              private readonly messageService: MessageService) {
  }

  ngOnInit(): void {
    this.student = new StudentRegistration();
  }

  public onStudentSave() {
    this.loader = true;
    this.student.firstName = this.student.firstName.toUpperCase();
    this.student.lastName = this.student.lastName.toUpperCase();
    this.studentService.saveStudent(this.student).subscribe({
      next: () => this.messageService.add({
        severity: 'error',
        summary: 'Error!',
        detail: 'Username ose password i gabuar!'
      }),
      error: (err) => {
        this.messageService.add({severity: 'error', summary: 'Error!', detail: 'Username ose password i gabuar!'});
        console.log('error', err)
        this.loader = false
      },
      complete: () => {
        this.loader = false;
        this.ref.close();
      }
    })
  }

}

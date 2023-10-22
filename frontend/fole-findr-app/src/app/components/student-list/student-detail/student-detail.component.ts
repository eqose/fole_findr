import {Component, OnInit} from '@angular/core';
import {StudentRegistration} from "../../../model/student-registration";
import {StudentService} from "../../../service/student.service";
import {MessageService} from "primeng/api";
import {DynamicDialogConfig, DynamicDialogRef} from "primeng/dynamicdialog";
import {Student} from "../../../model/student";

@Component({
  selector: 'app-student-detail',
  templateUrl: './student-detail.component.html',
  styleUrls: ['./student-detail.component.css'],
  providers: [MessageService]
})
export class StudentDetailComponent implements OnInit {
  public student!: StudentRegistration;
  public loader = false;

  constructor(private readonly studentService: StudentService,
              private readonly ref: DynamicDialogRef,
              private readonly config: DynamicDialogConfig,
              private readonly messageService: MessageService) {
  }

  ngOnInit(): void {
    this.student = new StudentRegistration();
    if (this.config.data.student){
      this.student.firstName = this.config.data.student.firstName;
      this.student.lastName = this.config.data.student.lastName;
      this.student.nId = this.config.data.student.nationalNo;
      this.student.birthDay = this.config.data.student.birthDay;
    }
  }

  public onStudentSave() {
    this.loader = true;
    this.student.firstName = this.student.firstName.toUpperCase();
    this.student.lastName = this.student.lastName.toUpperCase();
    this.studentService.saveStudent(this.student).subscribe({
      next: () => this.messageService.add({severity: 'success', summary: 'Shkëlqyeshëm!', detail: 'Studenti u ruajt me sukses!'}),
      error: (err) => {
        this.messageService.add({severity: 'error', summary: 'Error!', detail: 'Studenti nuk u ruajt!'});
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

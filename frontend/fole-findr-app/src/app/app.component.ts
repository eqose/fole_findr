import {Component, OnInit} from '@angular/core';
import {MenuItem} from "primeng/api";
import {DataSharingService} from "./service/data-sharing-service";
import {Router} from "@angular/router";
import {AuthService} from "./service/auth.service";
import {StudentService} from "./service/student.service";
import {Student} from "./model/student";
import {StudentDetailComponent} from "./components/student-list/student-detail/student-detail.component";
import {DialogService} from "primeng/dynamicdialog";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [DataSharingService, DialogService]
})
export class AppComponent implements OnInit {
  public sidebarVisible = false;
  public isLogged: boolean = false;
  public items: MenuItem[] = [];
  public topItems: MenuItem[] = [];
  public selectedTopItem: any;
  public inputName: string = '';
  public loader = false;
  public inDashboard = false;
  public studentList: Student[] = [];
  public selectedStudent!: Student;

  constructor(private readonly dataSharingService: DataSharingService,
              private router: Router,
              private readonly dialogService: DialogService,
              private readonly studentService: StudentService,
              private readonly authService: AuthService) {
    this.dataSharingService.isLogged.subscribe((check) => {
      if (check) {
        this.isLogged = check;
        this.selectedTopItem = this.topItems.filter(el => el.id == sessionStorage.getItem('building'))[0]
        this.dataSharingService.menuItems.next(this.topItems);
      }
    })

    this.dataSharingService.menuItem.subscribe((item) => {
      if (item) {
        this.selectedTopItem = this.topItems.filter(el => el.id == item)[0]
      }
    })

    this.dataSharingService.inDashboard.subscribe((check) => {
      this.inDashboard = check;
    })
  }

  ngOnInit() {
    this.items = [
      {
        label: 'Studentet me kontrate',
        icon: 'fa-solid fa-people-roof',
        routerLink: '/studentet-me-kontrate'
      },
      {
        label: 'Studentet pa kontrate',
        icon: 'fa-solid fa-person-circle-question',
        routerLink: '/studentet-pa-kontrate'
      },
      {
        label: 'Dhomat',
        icon: 'fa-solid fa-person-shelter',
        routerLink: '/dhomat'
      }
    ];

    this.topItems = [
      {
        label: 'Godina A',
        icon: 'fa-solid fa-building',
        style: 'color: #27c792',
        id: '1'
      },
      {
        label: 'Godina B',
        icon: 'fa-solid fa-building',
        style: 'color: #27c792',
        id: '2'
      },
      {
        label: 'Godina C',
        icon: 'fa-solid fa-building',
        style: 'color: #27c792',
        id: '3'
      }
    ];
  }

  onItemSelect(item: any) {
    this.sidebarVisible = false
    this.router.navigate([item.routerLink])
    this.dataSharingService.inDashboard.next(false)
  }

  public onNameSearch() {
    this.studentService.searchStudents(this.inputName).subscribe({
      next: (data) => {
        this.studentList = data
        this.studentList.forEach(el => {
          el.fullName = el.firstName + ' '+el.lastName
        })
      }
    })
  }

  public onLogOut() {
    this.loader = true;
    setTimeout(() => {
      this.isLogged = false;
      this.authService.logout();
      this.router.navigate(['/login']);
      this.loader = false;
    }, 500);
  }

  public onGoBack() {
    this.loader = true;
    this.sidebarVisible = false;
    setTimeout(() => {
      this.router.navigate(['/dashboard']);
      this.loader = false;
    }, 200);
  }

  public resetView() {
    this.loader = true;
    setTimeout(() => {
      sessionStorage.setItem('building', this.selectedTopItem.id.toString())
      this.router.navigate(['/dashboard']);
      this.loader = false;
    }, 200);
  }

  public openModal(){
    this.sidebarVisible = false;
    this.dialogService.open(StudentDetailComponent, {
      data: {student: this.selectedStudent},
      header: 'Profili i: ' + this.selectedStudent.fullName,
      width: '50vw',
      modal: true
    }).onClose.subscribe(()=> {
      this.selectedStudent = new Student();
      this.studentList = []
    })
  }
}

import {Component, OnInit} from '@angular/core';
import {MenuItem} from "primeng/api";
import {DataSharingService} from "./service/data-sharing-service";
import {Router} from "@angular/router";
import {AuthService} from "./service/auth.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [DataSharingService]
})
export class AppComponent implements OnInit {
  public sidebarVisible = false;
  public isLogged: boolean = false;
  public items: MenuItem[] = [];
  public topItems: MenuItem[] = [];
  public selectedTopItem!: MenuItem;
  public inputName: string = '';
  public loader = false;
  public inDashboard = false;

  constructor(private readonly dataSharingService: DataSharingService,
              private router: Router,
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

    this.dataSharingService.inDashboard.subscribe((check)=> {
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

  public onGoBack(){
    this.loader = true;
    this.sidebarVisible = false;
    setTimeout(() => {
      this.router.navigate(['/dashboard']);
      this.loader = false;
    }, 200);
  }
}

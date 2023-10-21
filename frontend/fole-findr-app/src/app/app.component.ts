import {Component, OnInit} from '@angular/core';
import {MenuItem} from "primeng/api";
import {DataSharingService} from "./service/data-sharing-service";
import {Router} from "@angular/router";

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

  constructor(private readonly dataSharingService: DataSharingService,
              private router: Router) {
    this.dataSharingService.isLogged.subscribe((check) => {
      if (check) {
        this.isLogged = check;
        this.selectedTopItem = this.topItems.filter(el => el.id == sessionStorage.getItem('building'))[0]
        this.dataSharingService.menuItems.next(this.topItems);
      }
    })

    this.dataSharingService.menuItem.subscribe((item) =>{
      if (item){
        this.selectedTopItem = this.topItems.filter(el => el.id == item)[0]
      }
    })
  }

  ngOnInit() {
    this.items = [
      {
        label: 'Studentet',
        icon: 'fa-solid fa-people-roof',
        routerLink: '/studentet'
      },
      {
        label: 'Dhomat',
        icon: 'fa-solid fa-person-shelter',
        routerLink: '/dhomat'
      },
      {
        label: 'Regjistri',
        icon: 'fa-solid fa-calendar-days',
        routerLink: '/regjistrit'
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

  onItemSelect(item: any){
    this.sidebarVisible = false
    this.router.navigate([item.routerLink])
  }

  public onNameSearch() {

  }

  public onLogOut(){

  }
}

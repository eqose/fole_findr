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
  public inputName: string = '';

  constructor(private readonly dataSharingService: DataSharingService,
              private router: Router) {
    this.dataSharingService.isLogged.subscribe((check) => {
      if (check) {
        this.isLogged = check;
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
  }

  onItemSelect(item: any){
    this.sidebarVisible = false
    this.router.navigate([item.routerLink])
  }

  public onNameSearch() {

  }
}

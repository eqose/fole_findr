import {Component, OnInit} from '@angular/core';
import {MenuItem} from "primeng/api";
import {Router} from "@angular/router";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit{
  public items: MenuItem[] = [];
  public floorShow = false;
  public selectedBuilding: string = '';
  public floorList: number[] = [1,2,3,4,7,8,5,3,4,6];

  constructor(private router: Router) {
  }

  ngOnInit() {
    this.items = [
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
  public onBuildingClick(item: any) {
    this.floorShow = true;
    this.selectedBuilding = item.label;
  }

  public onCloseFloors(){
    this.floorShow = false;
    this.selectedBuilding = '';
  }

  public onFloorClick(id: number){

  }
}

import {Component, OnInit} from '@angular/core';
import {MenuItem} from "primeng/api";
import {Router} from "@angular/router";
import {BuildingFloor} from "../../model/building-floor";
import {BuildingService} from "../../service/building.service";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit{
  public items: MenuItem[] = [];
  public floorShow = false;
  public selectedBuilding: string = '';
  public floorList: BuildingFloor[] = [];
  public loader = false;

  constructor(private router: Router,
              private readonly buildingService :BuildingService) {
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
    this.loadFloors(item.id)
    this.floorShow = true;
    this.selectedBuilding = item.label;
  }

  public onCloseFloors(){
    this.floorShow = false;
    this.selectedBuilding = '';
  }

  public onFloorClick(id: number){
  }

  public loadFloors(id: number) {
    this.loader = true
    this.buildingService.getFloorsOfBuilding(id).subscribe({
      next: (data)=> this.floorList = data.reverse(),
      error: (err) => console.log('error', err),
      complete: ()=> this.loader = false
    })
  }
}

import {Component, OnInit} from '@angular/core';
import {MenuItem} from "primeng/api";
import {Router} from "@angular/router";
import {BuildingFloor} from "../../model/building-floor";
import {BuildingService} from "../../service/building.service";
import {DataSharingService} from "../../service/data-sharing-service";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  public items: MenuItem[] = [];
  public floorShow = false;
  public selectedBuilding: string = '';
  public floorList: BuildingFloor[] = [];
  public loader = false;

  constructor(private router: Router,
              private readonly buildingService: BuildingService,
              private readonly dataSharingService: DataSharingService) {
  }

  ngOnInit() {
    this.dataSharingService.menuItems.subscribe((items) => {
      if (items) {
        this.items = items
      }
    })
    this.dataSharingService.inDashboard.next(true);
  }

  public onBuildingClick(item: any) {
    this.loadFloors(item.id)
    this.floorShow = true;
    this.dataSharingService.menuItem.next(item.id)
    this.selectedBuilding = item.label;
  }

  public onCloseFloors() {
    this.floorShow = false;
    this.selectedBuilding = '';
  }

  public onFloorClick(id: number) {
    this.loader = true;
    setTimeout(() => {
      this.loader = false;
      this.router.navigate(['/dhomat', id])
    }, 400);
  }

  public loadFloors(id: number) {
    this.loader = true
    this.buildingService.getFloorsOfBuilding(id).subscribe({
      next: (data) => this.floorList = data.reverse(),
      error: (err) => console.log('error', err),
      complete: () => this.loader = false
    })
  }
}

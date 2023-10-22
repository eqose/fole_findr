import {Component, OnInit} from '@angular/core';
import {BuildingService} from "../../service/building.service";
import {BuildingFloor} from "../../model/building-floor";
import {Router} from "@angular/router";

@Component({
  selector: 'app-floor-list',
  templateUrl: './floor-list.component.html',
  styleUrls: ['./floor-list.component.css']
})
export class FloorListComponent implements OnInit{
  public loader = false;
  public floorList: BuildingFloor[] = [];

  constructor(private readonly buildingService: BuildingService,
              private router: Router) {
  }
  ngOnInit(): void {
  }

  public loadFloors(id: number) {
    this.loader = true
    this.buildingService.getFloorsOfBuilding(id).subscribe({
      next: (data) => this.floorList = data.reverse(),
      error: (err) => console.log('error', err),
      complete: () => this.loader = false
    })
  }

  public onFloorClick(id: number) {
    this.loader = true;
    setTimeout(() => {
      this.loader = false;
      this.router.navigate(['/dhomat', id])
    }, 400);
  }

}

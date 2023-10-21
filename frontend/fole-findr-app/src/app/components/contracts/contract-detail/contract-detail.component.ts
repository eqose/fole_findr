import {Component, OnInit} from '@angular/core';
import {ContractInsert} from "../../../model/contract-insert";
import {DynamicDialogConfig, DynamicDialogRef} from "primeng/dynamicdialog";
import {ContractService} from "../../../service/contract.service";
import {BuildingFloor} from "../../../model/building-floor";
import {BuildingService} from "../../../service/building.service";

@Component({
  selector: 'app-contract-detail',
  templateUrl: './contract-detail.component.html',
  styleUrls: ['./contract-detail.component.css'],
  providers: [DynamicDialogRef]
})
export class ContractDetailComponent implements OnInit {
  public contract: ContractInsert = new ContractInsert();
  public studentName: string = '';
  public studentId!: number;
  public selectedFloor!: BuildingFloor;
  public loader = false;
  public floorList: BuildingFloor[] = [];

  constructor(private readonly ref: DynamicDialogRef,
              private readonly buildingService: BuildingService,
              private readonly contractService: ContractService,
              private readonly config: DynamicDialogConfig) {
  }

  ngOnInit(): void {
    if (this.config.data) {
      this.studentName = this.config.data.studentName;
      this.studentId = this.config.data.studentId;
    }
    this.loadFloorsOfBuilding();
  }

  public onContractSave() {
    // this.loader = true;
    // this.contract.studentId = this.studentId;
  }

  public loadFloorsOfBuilding() {
    this.loader = true;
    this.buildingService.getFloorsOfBuilding(Number(sessionStorage.getItem('building'))).subscribe({
      next: (data) => this.floorList = data,
      error: (err) => console.log('error', err),
      complete: () => {
        this.loader = false
      }
    })
  }

}

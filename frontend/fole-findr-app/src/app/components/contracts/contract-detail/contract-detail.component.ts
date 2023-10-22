import {Component, OnInit} from '@angular/core';
import {ContractInsert} from "../../../model/contract-insert";
import {DynamicDialogConfig, DynamicDialogRef} from "primeng/dynamicdialog";
import {ContractService} from "../../../service/contract.service";
import {BuildingFloor} from "../../../model/building-floor";
import {BuildingService} from "../../../service/building.service";
import {Room} from "../../../model/room";
import {MessageService} from "primeng/api";

@Component({
  selector: 'app-contract-detail',
  templateUrl: './contract-detail.component.html',
  styleUrls: ['./contract-detail.component.css'],
  providers: [MessageService]
})
export class ContractDetailComponent implements OnInit {
  public contract: ContractInsert = new ContractInsert();
  public studentName: string = '';
  public studentId!: number;
  public studentNId!: string;
  public selectedFloor!: BuildingFloor;
  public selectedRoom!: Room;
  public loader = false;
  public floorList: BuildingFloor[] = [];
  public roomList: Room[] = [];

  constructor(private readonly ref: DynamicDialogRef,
              private readonly messageService: MessageService,
              private readonly buildingService: BuildingService,
              private readonly contractService: ContractService,
              private readonly config: DynamicDialogConfig) {
  }

  ngOnInit(): void {
    if (this.config.data) {
      this.studentName = this.config.data.studentName;
      this.studentId = this.config.data.studentId;
      this.studentNId = this.config.data.studenNId;
    }
    this.loadFloorsOfBuilding();
  }

  public onContractSave() {
    this.loader = true;
    this.contract.studentId = this.studentId;
    this.contract.roomId = this.selectedRoom.id;

    this.contractService.saveContract(this.contract).subscribe({
      next: () => {
        this.messageService.add({severity: 'success', summary: 'Shkëlqyeshëm!', detail: 'Kontrata u ruajt me sukses!'});
      },
      error: (err)=> {
        console.log('error', err)
      },
      complete: ()=> {
        this.loader = false;
        this.ref.destroy();
      }
    })
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

  public getListOfRooms(){
    this.loader = true;
    this.buildingService.getAvailableRooms(this.contract.start, this.contract.end, this.selectedFloor.id).subscribe({
      next: (data)=> {
        this.roomList = data;
        this.roomList.forEach(el => {
          el.roomDescr = el.name + ' ' +el.roomType.name;
        })
      },
      error: (err)=> {
        console.log('error', err)
        this.loader = false;
      },
      complete: ()=>{
        this.loader=false
      }
    })
  }

}

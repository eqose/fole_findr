import {Component, OnInit} from '@angular/core';
import {MenuItem} from "primeng/api";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit{
  items: MenuItem[] = [];

  position: string = 'top';


  ngOnInit() {
    this.items = [
      {
        label: 'Finder',
        icon: 'fa-building'
      },
      {
        label: 'App Store',
        icon: 'fa-building'
      },
      {
        label: 'Photos',
        icon: 'fa-building'
      },
      {
        label: 'Trash',
        icon: 'fa-building'
      }
    ];
  }
}

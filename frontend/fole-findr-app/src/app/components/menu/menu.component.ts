import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit{
  public meuitems: any[] = [];

  ngOnInit(): void {
    this.meuitems = [
      {
        label: 'Godina A',
        icon: 'fa-solid fa-building'
      },
      {
        label: 'Godina B',
        icon: 'fa-solid fa-building'
      },
      {
        label: 'Godina C',
        icon: 'fa-solid fa-building'
      }
    ];
  }

  public shoMenu() {

  }

}

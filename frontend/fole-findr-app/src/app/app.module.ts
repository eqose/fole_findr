import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';

import {AppComponent} from './app.component';
import {LoginComponent} from './components/login/login.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {InputTextModule} from "primeng/inputtext";
import {ButtonModule} from "primeng/button";
import {RadioButtonModule} from "primeng/radiobutton";
import {AppRoutingModule} from './app-routing.module';
import {ProgressSpinnerModule} from "primeng/progressspinner";
import {MessageModule} from "primeng/message";
import {ToastModule} from "primeng/toast";
import {DashboardComponent} from './components/dashboard/dashboard.component';
import {DockModule} from "primeng/dock";
import {MenubarModule} from "primeng/menubar";
import {MenuComponent} from './components/menu/menu.component';
import {StudentListComponent} from './components/student-list/student-list.component';
import {CardModule} from "primeng/card";
import {TableModule} from "primeng/table";
import {HttpInterceptorService} from "./service/http-interceptor.service";
import { FloorListComponent } from './components/floor-list/floor-list.component';
import { RoomListComponent } from './components/room-list/room-list.component';
import { StudentDetailComponent } from './components/student-list/student-detail/student-detail.component';
import {SidebarModule} from "primeng/sidebar";
import {DropdownModule} from "primeng/dropdown";
import {CalendarModule} from "primeng/calendar";
import { FreeStudentsListComponent } from './components/student-list/free-students-list/free-students-list.component';
import { ContractDetailComponent } from './components/contracts/contract-detail/contract-detail.component';
import { ContractListComponent } from './components/contracts/contract-list/contract-list.component';
import {AutoCompleteModule} from "primeng/autocomplete";
import {RippleModule} from "primeng/ripple";

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    DashboardComponent,
    MenuComponent,
    StudentListComponent,
    FloorListComponent,
    RoomListComponent,
    StudentDetailComponent,
    FreeStudentsListComponent,
    ContractDetailComponent,
    ContractListComponent],
    imports: [
        BrowserModule,
        FormsModule,
        BrowserAnimationsModule,
        InputTextModule,
        MessageModule,
        HttpClientModule,
        ButtonModule,
        ReactiveFormsModule,
        RadioButtonModule,
        AppRoutingModule,
        ProgressSpinnerModule,
        ToastModule,
        DockModule,
        MenubarModule,
        CardModule,
        TableModule,
        SidebarModule,
        DropdownModule,
        CalendarModule,
        AutoCompleteModule,
        RippleModule
    ],
  providers: [
    {provide: HTTP_INTERCEPTORS, useClass: HttpInterceptorService, multi: true}],
  bootstrap: [AppComponent]
})
export class AppModule {
}

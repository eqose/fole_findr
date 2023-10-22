import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from "./components/login/login.component";
import {DashboardComponent} from "./components/dashboard/dashboard.component";
import {AuthGuard} from "../auth.guard";
import {StudentListComponent} from "./components/student-list/student-list.component";
import {FreeStudentsListComponent} from "./components/student-list/free-students-list/free-students-list.component";
import {RoomListComponent} from "./components/room-list/room-list.component";
import {FloorListComponent} from "./components/floor-list/floor-list.component";

const routes: Routes = [
  {path: 'login', component: LoginComponent},
  {path: 'dashboard', component: DashboardComponent, canActivate: [AuthGuard]},
  {path: 'studentet-me-kontrate', component: StudentListComponent},
  {path: 'studentet-pa-kontrate', component: FreeStudentsListComponent},
  {path: 'dhomat', component: FloorListComponent},
  {path: 'dhomat/:id', component: RoomListComponent},
  {path: '', redirectTo: '/login', pathMatch: 'full'},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {
}

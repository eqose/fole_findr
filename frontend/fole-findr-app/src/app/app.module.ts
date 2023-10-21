import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import {FormsModule, ReactiveFormsModule } from "@angular/forms";
import {InputTextModule} from "primeng/inputtext";
import {ButtonModule} from "primeng/button";
import {RadioButtonModule} from "primeng/radiobutton";
import { AppRoutingModule } from './app-routing.module';
import {ProgressSpinnerModule} from "primeng/progressspinner";
import {MessageModule} from "primeng/message";
import {ToastModule} from "primeng/toast";

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent
  ],
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
    ToastModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

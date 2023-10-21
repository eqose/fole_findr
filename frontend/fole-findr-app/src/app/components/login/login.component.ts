import {Component} from '@angular/core';
import {AuthService} from "../../service/auth.service";
import {MessageService} from "primeng/api";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  providers: [MessageService]
})
export class LoginComponent {
  public username: string = '';
  public password: string = '';
  public role: string = '';
  public loader = false;


  constructor(private readonly authService :AuthService,
              private readonly messageService :MessageService) {
  }

  public setUsername(){
    console.log(this.username)
  }

  public setPass(){
    console.log(this.password)
  }

  public onAuthenticate() {
    this.loader = true;
    const data = {username: this.username, password: this.password}
    this.authService.authenticateUser(data).subscribe({
      next: (data) => {
        if (!data) {
          this.messageService.add({ severity: 'error', summary: 'Error!', detail: 'Username ose password i gabuar!' });
        }
      },
      error: (err) => {
        console.log('error', err)
        this.loader = false;
        this.messageService.add({ severity: 'error', summary: 'Error!', detail: 'Dicka shkoi keq! \nProvo perseri me vone!' });
      }, complete: ()=> this.loader = false
    })
  }
}

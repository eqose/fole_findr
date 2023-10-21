import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../service/auth.service";
import {MessageService} from "primeng/api";
import {Router} from "@angular/router";
import {DataSharingService} from "../../service/data-sharing-service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  providers: [MessageService]
})
export class LoginComponent implements OnInit {
  public username: string = '';
  public password: string = '';
  public role: string = '';
  public loader = false;


  constructor(private readonly authService: AuthService,
              private router: Router,
              private readonly dataSharingService: DataSharingService,
              private readonly messageService: MessageService) {
  }

  public onAuthenticate() {
    this.loader = true;
    const data = {username: this.username, password: this.password}
    this.authService.authenticateUser(data).subscribe({
      next: (data) => {
        if (data) {
          sessionStorage.setItem('jwtToken', data.token)
          sessionStorage.setItem('building', '2')
        } else {
          this.messageService.add({severity: 'error', summary: 'Error!', detail: 'Username ose password i gabuar!'});
        }
      },
      error: (err) => {
        console.log('error', err)
        this.loader = false;
        this.messageService.add({
          severity: 'error',
          summary: 'Error!',
          detail: 'Dicka shkoi keq! \nProvo perseri me vone!'
        });
      }, complete: () => {
        this.loader = false
        this.dataSharingService.isLogged.next(true);
        this.router.navigate(['/dashboard']);
      }
    })
  }

  ngOnInit(): void {
    sessionStorage.setItem('jwtToken', '')
  }
}

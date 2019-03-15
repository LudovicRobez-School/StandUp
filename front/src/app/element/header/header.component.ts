import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/model/user';
import { AuthenticationService } from 'src/app/service/authentication.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  private connectedUser: User = null;

  constructor(private authenticationService: AuthenticationService) { }

  ngOnInit() {
    if (this.authenticationService.isConnected) {
      this.authenticationService.getConnectedUser().subscribe(
        data => this.connectedUser = data,
        error => console.error(error)
      );
    }
  }

  signout() {
    this.authenticationService.logout();
  }

}

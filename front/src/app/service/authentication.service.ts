import { Injectable, Output, EventEmitter } from '@angular/core';
import { UserService } from './user.service';
import { User } from '../model/user';
import { Observable, of, throwError } from 'rxjs';
import { map, catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  private users = Array<User>();
  @Output() getLoggedUser: EventEmitter<User> = new EventEmitter();

  constructor(private userService: UserService) { }

  signin(email: string, password: string): Observable<User | any> {
    return this.userService.getUsers().pipe(
      map((res) => {
        let user: User = null;
        this.users = res;
        user = this.users.find(user => user.email === email && user.password === password);
        if (user) {
          localStorage.setItem("currentUser", JSON.stringify(user));
          // Envoyer un signal pour dire que la connexion a été établie
          this.getLoggedUser.emit(user);
        }
        return user;
      })
    );
  }

  logout() {
    // remove user from local storage to log user out
    localStorage.removeItem('currentUser');
    console.log("Session deleted");
  }

  isConnected(): boolean {
    if (localStorage.getItem('currentUser')) {
      // connected so return true
      return true;
    } else {
      return false;
    }
  }

  getConnectedUser(): Observable<User> {
    return of(JSON.parse(localStorage.getItem('currentUser')));
  }
}

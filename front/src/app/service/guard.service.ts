import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthenticationService } from './authentication.service';

@Injectable({
  providedIn: 'root'
})
export class GuardService {

  // Constructeur
  constructor(private router: Router, private authenticationService: AuthenticationService) { }
  
  // Méthode permettant de vérifier si l'utilisateur est connecté ou pas pour accéder à une route privée
  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    if (this.authenticationService.isConnected()) {
      // logged in so return true
      return true;
    }

    // not logged in so redirect to login page with the return url
    this.router.navigate(['/user/signin'], { queryParams: { returnUrl: state.url } });
    return false;
  }
}
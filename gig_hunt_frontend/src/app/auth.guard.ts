//NEW CLASS

import { CanActivate, Router } from "@angular/router";
import { AuthenticationServiceService } from "./services/authentication-service.service";

export class AuthGuard implements CanActivate {

    constructor(private authService: AuthenticationServiceService, private router: Router) {}
  
    canActivate(): boolean {
      if (this.authService.isUserLoggedIn()) {
        return true; // Allow access if user is logged in
      } else {
        // Redirect unauthenticated users to login page
        this.router.navigate(['/login_page.html']); // Adjust '/login' to your login page route
        return false;
      }
    }
  }
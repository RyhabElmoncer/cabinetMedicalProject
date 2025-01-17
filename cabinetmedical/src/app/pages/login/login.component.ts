import { Component } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { AuthService } from '../../services/auth/auth.service';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    ReactiveFormsModule, // For reactive forms
    HttpClientModule,    // For HTTP requests
    CommonModule,        // Angular's common directives like *ngIf, *ngFor
    RouterModule,        // To use [routerLink]
    FormsModule          // Required for [(ngModel)]
 ],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  email: string = '';
  password: string = '';
  isSubmitting: boolean = false;
  errorMessage: string = '';

  constructor(private authService: AuthService, private router: Router) {}

  onSubmit() {
    this.isSubmitting = true;
    const credentials = {
      email: this.email,
      password: this.password,
    };
  
    console.log('Données envoyées :', credentials);
  
    this.authService.authenticate(credentials).subscribe(
      (response) => {
        const role = response.role;  // Assurez-vous que 'role' est correct dans la réponse
        this.redirectToDashboard(role);
        this.isSubmitting = false;
      },
      (error) => {
        console.error('Login échoué :', error);
        this.isSubmitting = false;
      }
    );
  }
  

  redirectToDashboard(role: string) {
    switch (role) {
      case 'DOCTEUR':
        this.router.navigate(['/medic-dashboard']);
        break;
      case 'SECRETAIRE':
        this.router.navigate(['/secretary-dashboard']);
        break;
      case 'PATIENT':
        this.router.navigate(['/patient-dashboard']);
        break;
      default:
        this.errorMessage = 'Rôle utilisateur non reconnu.';
    }
  }
}

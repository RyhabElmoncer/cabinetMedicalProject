import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { Router, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { AuthService } from '../../services/auth/auth.service';

@Component({
  selector: 'app-signup',
  standalone: true,
  imports: [
    ReactiveFormsModule, // Pour les formulaires réactifs
    HttpClientModule,    // Pour les requêtes HTTP
    CommonModule,        // Contient les directives Angular de base comme *ngIf, *ngFor
    RouterModule,        // Pour utiliser [routerLink]
  ],
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent {
  signupForm: FormGroup; // Formulaire de création de compte
  isSubmitting = false;  // Indique si une requête est en cours
  errorMessage: string | null = null; // Pour afficher les messages d'erreur

  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) {
    // Initialisation du formulaire
    this.signupForm = this.formBuilder.group({
      username: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      lastname: ['', Validators.required], 
      password: ['', [Validators.required, Validators.minLength(6)]],
    });
  }

  onSubmit() {
    // Si le formulaire est invalide, stoppez l'exécution
    if (this.signupForm.invalid) return;

    this.isSubmitting = true;
    this.errorMessage = null;

    const formData = this.signupForm.value;

    // Appeler l'API pour l'inscription
    this.authService.register(formData).subscribe({
      next: () => {
        this.isSubmitting = false;
        this.router.navigate(['/login']); // Redirection vers la page de connexion
      },
      error: (err) => {
        this.isSubmitting = false;
        this.errorMessage = "Une erreur est survenue lors de l'inscription.";
        console.error(err);
      },
    });
  }
}

import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private apiUrl = 'http://localhost:9999/api/v1/auth'; // Remplacez par l'URL réelle de votre backend

  constructor(private http: HttpClient) {}

  // Inscription
  register(data: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/register`, data, { headers: this.getHeaders() })
      .pipe(
        catchError(this.handleError)
      );
  }


  authenticate(data: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/authenticate`, data, this.getRequestOptions())
      .pipe(catchError(this.handleError));
  }

  private getRequestOptions() {
    return {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
    };
  }

  private handleError(error: any): Observable<never> {
    let errorMessage = 'Une erreur est survenue';
  
    if (error.error instanceof ErrorEvent) {
      // Erreur côté client
      errorMessage = `Erreur client : ${error.error.message}`;
    } else {
      // Erreur côté serveur
      errorMessage = `Erreur serveur : ${error.status} - ${error.error.message || error.message}`;
    }
    
    console.error(errorMessage); // Log du détail pour déboguer
    return throwError(errorMessage);
  }
  
  // Rafraîchissement du token
  refreshToken(refreshToken: string): Observable<any> {
    const headers = this.getHeaders(refreshToken); // Utilisation du token de rafraîchissement
    return this.http.post(`${this.apiUrl}/refresh-token`, {}, { headers })
      .pipe(
        catchError(this.handleError)
      );
  }

  // Méthode pour obtenir les headers, ajoute l'Authorization si le token est fourni
  private getHeaders(refreshToken: string = ''): HttpHeaders {
    let headers = new HttpHeaders().set('Content-Type', 'application/json'); // Type de contenu par défaut
    if (refreshToken) {
      headers = headers.set('Authorization', `Bearer ${refreshToken}`); // Ajout de l'en-tête Authorization si nécessaire
    }
    return headers;
  }

 
}

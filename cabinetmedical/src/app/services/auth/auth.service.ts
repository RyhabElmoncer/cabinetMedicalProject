import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private apiUrl = 'http://localhost:9999/api/v1/auth'; // Remplacez par l'URL réelle de votre backend

  constructor(private http: HttpClient) {}

 
  register(data: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/register`, data);
  }

 
  authenticate(data: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/authenticate`, data);
  }

  refreshToken(refreshToken: string): Observable<any> {
    const headers = new HttpHeaders().set('Authorization', `Bearer ${refreshToken}`);
    return this.http.post(`${this.apiUrl}/refresh-token`, {}, { headers });
  }
}

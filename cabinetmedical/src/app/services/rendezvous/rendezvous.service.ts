import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
export interface RendezVousDTO {
  date: string;
  heure: string;
  status: string;
  patient: { id: number };
}

@Injectable({
  providedIn: 'root',
})
export class RendezvousService {
  private baseUrl = 'http://localhost:9999/api/rendezvous'; // URL de base de l'API backend

  constructor(private http: HttpClient) {}

  // Demander un rendez-vous
  demanderRendezVous(rendezVous: RendezVousDTO): Observable<RendezVousDTO> {
    const url = `${this.baseUrl}/rendezvous`;
    return this.http.post<RendezVousDTO>(url, rendezVous);
  }

  // Obtenir la liste de tous les rendez-vous
  getAllRendezVous(): Observable<RendezVousDTO[]> {
    const url = `${this.baseUrl}/all`;
    return this.http.get<RendezVousDTO[]>(url);
  }

  // Accepter un rendez-vous
  accepterRendezVous(id: number): Observable<RendezVousDTO> {
    const url = `${this.baseUrl}/${id}/accepter`;
    return this.http.put<RendezVousDTO>(url, {});
  }

  // Refuser un rendez-vous
  refuserRendezVous(id: number): Observable<RendezVousDTO> {
    const url = `${this.baseUrl}/${id}/refuser`;
    return this.http.put<RendezVousDTO>(url, {});
  }
  // Obtenir la liste des rendez-vous d'un patient spécifique
  getRendezVousByPatientId(patientId: number): Observable<RendezVousDTO[]> {
    const url = `${this.baseUrl}/patient/${patientId}`;
    return this.http.get<RendezVousDTO[]>(url);
  }
}

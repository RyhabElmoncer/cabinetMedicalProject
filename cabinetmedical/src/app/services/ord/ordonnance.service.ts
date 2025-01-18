import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface OrdonnanceDTO {
  id?: number;
  patientId: number;
  description: string;
  dateCreation: string;
}

@Injectable({
  providedIn: 'root',
})
export class OrdonnanceService {
  private baseUrl = 'http://localhost:8080/api/ordonnances'; // Remplacez avec votre URL backend

  constructor(private http: HttpClient) {}

  // Ajouter une ordonnance
  ajouterOrdonnance(ordonnance: OrdonnanceDTO): Observable<OrdonnanceDTO> {
    const url = `${this.baseUrl}/ajouter`;
    return this.http.post<OrdonnanceDTO>(url, ordonnance);
  }

  // Consulter les ordonnances par ID de patient
  consulterOrdonnancesParPatient(patientId: number): Observable<OrdonnanceDTO[]> {
    const url = `${this.baseUrl}/patient/${patientId}`;
    return this.http.get<OrdonnanceDTO[]>(url);
  }

  // Obtenir toutes les ordonnances
  getAllOrdonnances(): Observable<OrdonnanceDTO[]> {
    const url = `${this.baseUrl}/all`;
    return this.http.get<OrdonnanceDTO[]>(url);
  }
}

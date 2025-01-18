import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RendezVousDTO, RendezvousService } from '../../services/rendezvous/rendezvous.service';

@Component({
  selector: 'app-patient-dashboard',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './patient-dashboard.component.html',
  styleUrls: ['./patient-dashboard.component.css'],
})
export class PatientDashboardComponent implements OnInit {
  appointmentDate: string = '';
  appointmentTime: string = '';
  appointments: RendezVousDTO[] = [];
  submitted: boolean = false;

  constructor(private rendezvousService: RendezvousService) {}
  ngOnInit(): void {
    this.fetchAppointmentsByPatient();
  }
  logout(): void {
    localStorage.removeItem('userId');
    alert('Déconnexion réussie.');
    window.location.href = '/login'; // Redirection vers la page de connexion
  }
  
  /**
   * Fetch all appointments for the current patient.
   */
  fetchAppointments(): void {
    this.rendezvousService.getAllRendezVous().subscribe({
      next: (data) => {
        this.appointments = data;
        console.log('Appointments fetched successfully:', data);
      },
      error: (err) => {
        console.error('Erreur lors du chargement des rendez-vous:', err);
        alert('Une erreur est survenue lors du chargement des rendez-vous.');
      },
    });
  }

  requestAppointment(): void {
    this.submitted = true;
  
    if (!this.appointmentDate || !this.appointmentTime) {
      alert('Veuillez remplir tous les champs obligatoires.');
      this.submitted = false;
      return;
    }
  
    const patientId = localStorage.getItem('userId');
    if (!patientId) {
      alert('Erreur : ID du patient introuvable. Veuillez vous reconnecter.');
      this.submitted = false;
      return;
    }
  
    const newRendezVous = {
      date: `${this.appointmentDate}T${this.appointmentTime}`,
      heure: this.appointmentTime,
      status: 'EN_ATTENTE',
      patient: { id: +patientId },
    };
  
    this.rendezvousService.demanderRendezVous(newRendezVous).subscribe({
      next: (response) => {
        this.appointments.push(response);
        this.resetForm();
        alert('Votre rendez-vous a été demandé avec succès.');
      },
      error: (err) => {
        console.error('Erreur lors de la demande de rendez-vous:', err);
        alert('Une erreur est survenue lors de la demande de rendez-vous.');
        this.submitted = false;
      },
    });
  }  

 
  fetchAppointmentsByPatient(): void {
    const patientId = localStorage.getItem('userId');
    if (!patientId) {
      alert('Erreur : ID du patient introuvable. Veuillez vous reconnecter.');
      return;
    }
  
    this.rendezvousService.getRendezVousByPatientId(+patientId).subscribe({
      next: (data) => {
        this.appointments = data;
        console.log('Appointments for patient fetched successfully:', data);
      },
      error: (err) => {
        console.error('Erreur lors du chargement des rendez-vous du patient:', err);
        alert('Une erreur est survenue lors du chargement des rendez-vous.');
      },
    });
  }
  
  /**
   * Reset the form fields and submission state.
   */
  private resetForm(): void {
    this.appointmentDate = '';
    this.appointmentTime = '';
    this.submitted = false;
  }
}

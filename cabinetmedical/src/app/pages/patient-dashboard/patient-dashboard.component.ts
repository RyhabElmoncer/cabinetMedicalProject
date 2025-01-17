import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';


@Component({
  selector: 'app-patient-dashboard',
  standalone: true, // Composant autonome
  imports: [
    CommonModule,  // Obligatoire pour ngIf, ngFor, etc.
    FormsModule    // Obligatoire pour [(ngModel)]
  ],
  templateUrl: './patient-dashboard.component.html',
  styleUrls: ['./patient-dashboard.component.css']
})
export class PatientDashboardComponent {
  appointmentDate: string = '';
  appointmentTime: string = '';
  appointmentReason: string = '';
  appointments: any[] = [
    {
      date: '2025-01-17',
      time: '10:00',
      reason: 'Consultation générale',
      status: 'en-attente'
    },
    {
      date: '2025-01-18',
      time: '14:00',
      reason: 'Suivi médical',
      status: 'accepte'
    }
  ];

  requestAppointment() {
    if (this.appointmentDate && this.appointmentTime && this.appointmentReason) {
      this.appointments.push({
        date: this.appointmentDate,
        time: this.appointmentTime,
        reason: this.appointmentReason,
        status: 'en-attente'
      });

      // Réinitialiser le formulaire
      this.appointmentDate = '';
      this.appointmentTime = '';
      this.appointmentReason = '';
    }
  }
}

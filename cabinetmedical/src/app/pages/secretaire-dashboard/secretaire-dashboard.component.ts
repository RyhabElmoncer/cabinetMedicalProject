import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-secretaire-dashboard',
  standalone: true,
  templateUrl: './secretaire-dashboard.component.html',
  styleUrls: ['./secretaire-dashboard.component.css'],
  imports: [FormsModule, CommonModule],
})
export class SecretaireDashboardComponent {
  searchQuery: string = '';
  rendezvousList = [
    { id: 1, patient: 'Jean Dupont', date: '2025-01-20', heure: '14:00', status: 'En attente' },
    { id: 2, patient: 'Marie Curie', date: '2025-01-21', heure: '09:00', status: 'En attente' },
    { id: 3, patient: 'Albert Einstein', date: '2025-01-22', heure: '11:00', status: 'En attente' },
  ];
  filteredRendezvous = [...this.rendezvousList];

  filterRendezvous() {
    this.filteredRendezvous = this.rendezvousList.filter((rendezvous) =>
      rendezvous.patient.toLowerCase().includes(this.searchQuery.toLowerCase())
    );
  }

  accepterRendezvous(rendezvous: any) {
    rendezvous.status = 'Accepté';
  }

  refuserRendezvous(rendezvous: any) {
    rendezvous.status = 'Refusé';
  }
}

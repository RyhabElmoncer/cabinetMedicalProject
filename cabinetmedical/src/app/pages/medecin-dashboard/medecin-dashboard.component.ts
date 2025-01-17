import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms'; // Pour [(ngModel)]
import { CommonModule } from '@angular/common'; // Pour *ngFor

@Component({
  selector: 'app-medecin-dashboard',
  standalone: true, // Indique que ce composant est autonome
  templateUrl: './medecin-dashboard.component.html',
  styleUrls: ['./medecin-dashboard.component.css'],
  imports: [FormsModule, CommonModule], // Importez les modules nécessaires ici
})
export class MedecinDashboardComponent {
  searchQuery: string = ''; // Pour la recherche
  patients = [
    { id: 1, nom: 'Jean Dupont', email: 'jean.dupont@example.com', age: 45 },
    { id: 2, nom: 'Marie Curie', email: 'marie.curie@example.com', age: 39 },
    { id: 3, nom: 'Albert Einstein', email: 'albert.einstein@example.com', age: 50 },
  ];
  filteredPatients = [...this.patients];

  filterPatients() {
    this.filteredPatients = this.patients.filter((patient) =>
      patient.nom.toLowerCase().includes(this.searchQuery.toLowerCase())
    );
  }

  viewOrdonnances(patient: any) {
    alert(`Ordonnances pour ${patient.nom}`);
  }

  addOrdonnance(patient: any) {
    alert(`Ajouter une ordonnance pour ${patient.nom}`);
  }
  
}

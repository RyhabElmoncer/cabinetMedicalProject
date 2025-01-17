import { Routes } from '@angular/router';
import { LoginComponent } from './pages/login/login.component';
import { SignupComponent } from './pages/signup/signup.component';
import { MedecinDashboardComponent } from './pages/medecin-dashboard/medecin-dashboard.component';
import { PatientDashboardComponent } from './pages/patient-dashboard/patient-dashboard.component';
import { SecretaireDashboardComponent } from './pages/secretaire-dashboard/secretaire-dashboard.component';


export const routes: Routes = [
    { path: 'medic-dashboard', component: MedecinDashboardComponent },
    { path: 'secretary-dashboard', component: SecretaireDashboardComponent },
    { path: 'patient-dashboard', component: PatientDashboardComponent },
  { path: 'login', component: LoginComponent },
  { path: 'signup', component: SignupComponent },
  { path: '**', redirectTo: 'login' } 
];

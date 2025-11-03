import { Routes } from '@angular/router';
import { HomeComponent } from './components/home-component/home-component';
import { ObraComponent } from './components/obra-component/obra-component';
import { UsuarioComponent } from './components/usuario-component/usuario-component';

export const routes: Routes = [
    {path: '', redirectTo: 'home', pathMatch: 'full'},
    { path: 'home', component: HomeComponent,
        children: [
            { path: 'obras', component: ObraComponent },
            { path: 'usuarios', component: UsuarioComponent },
        ],
    }
];

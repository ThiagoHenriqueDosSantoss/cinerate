import { Routes } from '@angular/router';
import { HomeComponent } from './components/home-component/home-component';
import { ObraComponent } from './components/obra-component/obra-component';
import { UsuarioComponent } from './components/usuario-component/usuario-component';
import { GeneroComponent } from './components/genero-component/genero-component';
import { LoginComponent } from './components/login-component/login-component';

export const routes: Routes = [
    {path: '', redirectTo: 'login', pathMatch: 'full'},
    {path:'login', component: LoginComponent},
    { path: 'home', component: HomeComponent,
        children: [
            { path: 'obras', component: ObraComponent },
            { path: 'usuarios', component: UsuarioComponent },
            { path: 'generos', component: GeneroComponent },
        ],
    }
];

import { Component, OnInit } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { RouterModule } from '@angular/router';

//Imports do primeng
import { MenubarModule } from 'primeng/menubar';
import { MenuItem } from 'primeng/api';

@Component({
  selector: 'app-home-component',
  standalone: true,
  imports: [MenubarModule,RouterModule],
  templateUrl: './home-component.html',
  styleUrl: './home-component.css',
})
export class HomeComponent implements OnInit{
  items: MenuItem[] | undefined;

  constructor(private router: Router){}
  ngOnInit() {
        this.items = [
            {
                label: 'Home',
                icon: 'pi pi-home',
                routerLink:  ['/home'] 
            },
            {
                label: 'Obras',
                icon: 'pi pi-lightbulb',
                routerLink:  ['/home/obras'] 
            },
            {
                label: 'Usuarios',
                icon: 'pi pi-users',
                routerLink:  ['/home/usuarios'] 
            },
        ]
    }
}

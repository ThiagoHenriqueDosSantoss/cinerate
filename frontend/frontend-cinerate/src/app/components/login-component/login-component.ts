import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';

import { CardModule } from 'primeng/card';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-login-component',
  standalone: true,
  imports: [CommonModule,CardModule, FormsModule, ButtonModule,InputTextModule],
  templateUrl: './login-component.html',
  styleUrl: './login-component.css',
})
export class LoginComponent {
  usuario: string = '';
  senha: string = '';

   login() {
    if (this.usuario === 'admin' && this.senha === '123') {
      console.log('✅ Login ok!');
      // redireciona aqui, se quiser
    } else {
      console.log('❌ Usuário ou senha incorretos');
    }
  }
}

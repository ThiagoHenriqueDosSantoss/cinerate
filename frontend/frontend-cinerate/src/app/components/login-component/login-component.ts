import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';

import { CardModule } from 'primeng/card';
import { CommonModule } from '@angular/common';
import { Login } from '../../interface/Login';

@Component({
  selector: 'app-login-component',
  standalone: true,
  imports: [CommonModule,CardModule, FormsModule, ButtonModule,InputTextModule],
  templateUrl: './login-component.html',
  styleUrl: './login-component.css',
})
export class LoginComponent {
  loginUsuario: Login = {
    login: '',
    senha: ''
  }

   login() {
    if (this.loginUsuario.login === 'admin' && this.loginUsuario.senha === '123') {
      console.log('✅ Login ok!');
      // redireciona aqui, se quiser
    } else {
      console.log('❌ Usuário ou senha incorretos');
    }
  }
}

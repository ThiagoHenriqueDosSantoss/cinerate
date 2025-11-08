import { Component, Inject, PLATFORM_ID } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';

import { CardModule } from 'primeng/card';
import { CommonModule, isPlatformBrowser } from '@angular/common';
import { Login } from '../../interface/Login';
import { LoginService } from '../../service/login-service';
import { Router } from '@angular/router';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-login-component',
  standalone: true,
  imports: [CommonModule,CardModule, FormsModule, ButtonModule,InputTextModule,RouterModule],
  templateUrl: './login-component.html',
  styleUrl: './login-component.css',
})
export class LoginComponent {
  isBrowser: boolean;

  loginUsuario: Login = {
    nome: '',
    senha: ''
  }
  constructor(private loginService: 
    LoginService,
    private router: Router,
    @Inject(PLATFORM_ID) private platformId: Object
  ){
    this.isBrowser = isPlatformBrowser(this.platformId);
  }

   login() {
    this.loginService.login(this.loginUsuario).subscribe({
      next: (res) => {
        if(res.token){
          localStorage.setItem('token',res.token)
        }
        this.router.navigate(['/home']);
        console.log(res.token);
      },
      error: (err) => {
        console.error(err);
        alert(err.error?.mensagem || 'Usuário ou senha incorretos');
      }
    });
  }
}

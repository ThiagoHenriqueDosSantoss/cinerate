import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

//Imports do primeng
import { ButtonModule } from 'primeng/button';
import { TableModule } from 'primeng/table';
import { DialogModule } from 'primeng/dialog';
import { InputTextModule } from 'primeng/inputtext';
import { UsuarioService } from '../../service/usuario-service';
import { Usuario } from '../../interface/Usuario';

@Component({
  selector: 'app-usuario-component',
  imports: [CommonModule,ButtonModule, TableModule, InputTextModule, DialogModule, FormsModule],
  templateUrl: './usuario-component.html',
  styleUrl: './usuario-component.css',
})
export class UsuarioComponent implements OnInit{
  displayModal = false;

  novoUsuario: Usuario = { nome: '', email: '' , senha: '' };

  usuarios = [
    { id: 1, nome: 'João Silva', email: 'joao@email.com' },
    { id: 2, nome: 'Maria Souza', email: 'maria@email.com' },
    { id: 3, nome: 'Carlos Oliveira', email: 'carlos@email.com' }
  ];
  constructor(private usuarioService: UsuarioService){}

  ngOnInit(): void {
    this.usuarios;
  }
  salvarUsuario() {
  this.usuarioService.salvarUsuarios(this.novoUsuario).subscribe({
    next: (usuario) => {
      console.log('Usuário salvo:', usuario);
      this.displayModal = false; // fecha modal
      this.novoUsuario = { nome: '', email: '' , senha: ''};
    },
    error: (err) => {
      console.error('Erro ao salvar usuário', err);
    }
  });
}
}

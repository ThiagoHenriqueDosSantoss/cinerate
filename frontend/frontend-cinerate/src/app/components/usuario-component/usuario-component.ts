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

  usuarios: Usuario[] = [];

  constructor(private usuarioService: UsuarioService){}

  ngOnInit(): void {
    this.buscarUsuarios();
  }
  salvarUsuario() {
    console.log('Usuário salvo:', this.novoUsuario);
    const usuarioSalvar: Usuario = {
      nome: this.novoUsuario.nome,
      email: this.novoUsuario.email,
      senha: this.novoUsuario.senha
    };
    this.usuarioService.salvarUsuarios(usuarioSalvar).subscribe({
      next: (usuarioSalvar) => {
        console.log('Usuário salvo:', usuarioSalvar);
        this.displayModal = false;
        this.novoUsuario = { nome: '', email: '' , senha: ''};
      },
      error: (err) => {
        console.error('Erro ao salvar usuário', err);
      }
    });
  }
  buscarUsuarios() {
  this.usuarioService.buscarUsuarios().subscribe({
    next: (usuarios: Usuario[]) => {
      this.usuarios = usuarios; // atualiza a lista do componente
      console.log('Usuários carregados:', usuarios);
    },
    error: (err) => {
      console.error('Erro ao buscar usuários', err);
    }
  });
}
}

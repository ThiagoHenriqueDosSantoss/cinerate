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

  novoUsuario: Usuario = { idUsuario: 0, nome: '', email: '' , senha: '' , dataDeCadastro: ''};

  usuarios: Usuario[] = [];

  constructor(private usuarioService: UsuarioService){}

  ngOnInit(): void {
    this.buscarUsuarios();
  }
  salvarUsuario() {
  // Se tiver ID → está editando
  if (this.novoUsuario.idUsuario) {

    const usuarioEditar: Partial<Usuario> = {
      nome: this.novoUsuario.nome,
      email: this.novoUsuario.email,
      senha: this.novoUsuario.senha,
      dataDeCadastro: this.novoUsuario.dataDeCadastro
    };

    this.usuarioService.atualizarUsuarios(this.novoUsuario.idUsuario, usuarioEditar).subscribe({
      next: () => {
        setTimeout(() => {
          this.displayModal = false;
        }, 0);
        this.resetarFormulario();
        this.buscarUsuarios();
      },
      error: (err) => console.error(err)
    });

  // Novo usuário
  } else {
    this.usuarioService.salvarUsuarios(this.novoUsuario).subscribe({
      next: () => {
        setTimeout(() => {
          this.displayModal = false;
          this.resetarFormulario();
          this.buscarUsuarios();
        }, 0);
      },
      error: (err) => console.error(err)
    });
  }
}
  buscarUsuarios() {
    this.usuarioService.buscarUsuarios().subscribe({
      next: (usuarios: Usuario[]) => {
        this.usuarios = usuarios;
      },
      error: (err) => {
        console.error('Erro ao buscar usuários', err);
      }
    });
  }
  showDialog(usuario?: Usuario) {
    if (usuario && usuario.idUsuario) {
      this.novoUsuario = { ...usuario };
    } else {
      this.resetarFormulario();
    }

    // Abrir o modal por último
    setTimeout(() => {
      this.displayModal = true;
    });
  }
  resetarFormulario() {
    this.novoUsuario = { idUsuario: 0, nome: '', email: '', senha: '' , dataDeCadastro: ''};
  }
  onEdit(usuario: Usuario) {
    if (!usuario.idUsuario) {
      console.error("Este usuário não possui ID. Não é possível editar.");
      return;
    }
    this.usuarioService.buscarUsuariosPorID(usuario.idUsuario).subscribe({
      next: (usuarioEncontrado) => {
        this.showDialog(usuarioEncontrado);
      },
      error: (err) => console.error("Erro ao buscar usuário:", err)
    });
  }
}

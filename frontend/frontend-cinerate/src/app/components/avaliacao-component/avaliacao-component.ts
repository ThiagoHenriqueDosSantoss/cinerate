import { Component, OnInit } from '@angular/core';

import { CardModule } from 'primeng/card';
import { ButtonModule } from 'primeng/button';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { DialogModule } from 'primeng/dialog';
import { ObraService } from '../../service/obra-service';
import { Obra } from '../../interface/Obra';
import { RatingModule } from 'primeng/rating';
import { Avaliacao } from '../../interface/Avaliacao';
import { CriarAvaliacao } from '../../interface/CriarAvaliacao';
import { AvaliacaoService } from '../../service/avaliacao-service';

@Component({
  selector: 'app-avaliacao-component',
  standalone: true,
  imports: [FormsModule,CommonModule,CardModule,ButtonModule, DialogModule,RatingModule],
  templateUrl: './avaliacao-component.html',
  styleUrl: './avaliacao-component.css',
})
export class AvaliacaoComponent implements OnInit{
  
  visible: boolean = false;
  obras: Obra[] = [];

  novaAvaliacao: CriarAvaliacao = {
    nota: 0,
    comentario: '',
    idUsuario: 0,
    idObra: 0
  };
  obraSelecionada: Obra | null = null;

  constructor(private obraService:ObraService, private avaliacaoService: AvaliacaoService){}

  ngOnInit(): void {
      this.visible;
      this.buscarObras();
  }
  abrirModalAvaliacao(obra:Obra){
    this.obraSelecionada = obra;
      this.novaAvaliacao = {
      nota: 0,
      comentario: '',
      idUsuario: undefined, // Pegar do usuário logado
      idObra: obra.idObra
    };
    console.log('Nova avaliação configurada:', this.novaAvaliacao);
    this.visible = true;
  }
  buscarObras() {
    this.obraService.buscarObras().subscribe({
      next: (obras: Obra[]) => {
          this.obras = obras;
          console.log(this.obras);
      },
      error: (err) => {
        console.error('Erro ao buscar obras', err);
      }
    });
  }
  salvarAvaliacao() {

    if (!this.novaAvaliacao.nota || !this.novaAvaliacao.comentario) {
      console.error('Preencha todos os campos!');
      return;
    }

    this.avaliacaoService.criarAvaliacao(this.novaAvaliacao).subscribe({
      next: (response) => {
        console.log('Avaliação salva:', response);
        this.fecharModal();
      },
      error: (err) => {
        console.error('Erro ao salvar avaliação:', err);
      }
    });
  }

  // Método para pegar o ID do usuário logado
  getUsuarioLogadoId(): number {
    // Aqui você pega do localStorage, service de autenticação, etc
    const usuarioLogado = JSON.parse(localStorage.getItem('usuario') || '{}');
    return usuarioLogado.idUsuario || 10; // valor padrão se não encontrar
  }

  fecharModal() {
    this.visible = false;
    this.obraSelecionada = null;
  }
}

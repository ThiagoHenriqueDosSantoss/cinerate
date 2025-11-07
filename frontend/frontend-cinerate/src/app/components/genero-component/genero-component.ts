import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { Genero } from '../../interface/Genero';
import { TableModule } from 'primeng/table';
import { CommonModule } from '@angular/common';
import { GeneroService } from '../../service/genero-service';
import { DialogModule } from 'primeng/dialog';
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { ConfirmationService, MessageService } from 'primeng/api';

@Component({
  selector: 'app-genero-component',
  standalone: true,
  imports: [FormsModule, InputTextModule, ButtonModule, TableModule, CommonModule, DialogModule,ConfirmDialogModule],
  templateUrl: './genero-component.html',
  styleUrl: './genero-component.css',
  providers: [ConfirmationService, MessageService],
})
export class GeneroComponent implements OnInit {

  generoSelecionadoExclusao?: Genero | undefined;

  visible: boolean = false;
  generos: Genero[] = []

  novoGenero: Genero = {
    idGenero: null,
    nome: ''
  }
  constructor(private generoService: GeneroService, private confirmationService: ConfirmationService,
  private messageService: MessageService){}

  ngOnInit(): void {
    this.buscarGenero();
  }
  salvarGenero(){
      if(this.novoGenero.idGenero){
        const generoParaEditar : Genero = {
          idGenero: this.novoGenero.idGenero,
          nome: this.novoGenero.nome
        }
        this.generoService.atualizaGenero(generoParaEditar?.idGenero as number,generoParaEditar).subscribe({
          next: (genero) => {
            this.resetaFormulario();
            this.buscarGenero();
            this.visible = false;
            console.log("Genero Editado:"+genero);
          }
        })
    }
    else{
        const generoParaSalvar : Genero = {
        idGenero: this.novoGenero.idGenero,
        nome: this.novoGenero.nome
        }
        if(generoParaSalvar.nome === ''){
          alert('Por favor informe um nome para o gênero antes de clicar em salvar!');  
        }
        else{
            this.generoService.salvarGenero(generoParaSalvar).subscribe({
            next: () => {
              this.resetaFormulario();
              this.buscarGenero();
            }
          });
        }
    }
  }
  buscarGenero(){
    this.generoService.buscarGeneros().subscribe({
      next: (genero: Genero[]) => {
        this.generos = genero;
      }
    })
  }
  editarGenero(genero?: Genero){
    console.log("ID Genero:"+genero?.idGenero);
    this.visible = true;
    if(!genero?.idGenero){
      return;
    }
    
    this.generoService.buscarGeneroPorID(genero?.idGenero).subscribe({
      next: (genero) => {
        console.log(genero);
        this.novoGenero = {... genero } 
      }
    })
    }
    resetaFormulario():Genero{
      return {
        idGenero: null,
        nome: ''
      }
    }
    deletarGenero(genero?: Genero, event?: Event){
      if(!genero?.idGenero){
        console.warn('ID do genero não encontrado!');
        return;
      }
      this.generoSelecionadoExclusao = {...genero};
      this.confirmarExclusaoGenero(event);
    }
    confirmarExclusaoGenero(event?: Event) {

      this.confirmationService.confirm({
      target: event?.target as EventTarget,
      message: 'Tem certeza que deseja excluir este gênero?',
      header: 'Confirmação de Exclusão',
      icon: 'pi pi-info-circle',
      rejectLabel: 'Cancelar',
      rejectButtonProps: { label: 'Cancelar', severity: 'secondary', outlined: true },
      acceptButtonProps: { label: 'Excluir', severity: 'danger' },
      accept: () => {
            // chama o serviço de exclusão
            this.generoService.deletarGenero(this.generoSelecionadoExclusao?.idGenero as number).subscribe({
              next: () => {
                this.messageService.add({
                  severity: 'success',
                  summary: 'Sucesso',
                  detail: 'Gênero excluído com sucesso!'
                });
                this.buscarGenero();
                this.generoSelecionadoExclusao = undefined;
              },
              error: () => {
                this.messageService.add({
                  severity: 'error',
                  summary: 'Erro',
                  detail: 'Falha ao excluir gênero.'
                });
              }
            });
          },
          reject: () => {
            this.generoSelecionadoExclusao = undefined;
          }
        });
    }
  }
2
import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

//Imports do primeng
import { ButtonModule } from 'primeng/button';
import { TableModule } from 'primeng/table';
import { DialogModule } from 'primeng/dialog';
import { InputTextModule } from 'primeng/inputtext';
import { Obra } from '../../interface/Obra';
import { TipoObra } from '../../enum/TipoObra';
import { Select } from 'primeng/select';
import { ObraService } from '../../service/obra-service';
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { ConfirmationService, MessageService } from 'primeng/api';
import { Genero } from '../../interface/Genero';

@Component({
  selector: 'app-obra-component',
  standalone: true,
  imports: [CommonModule,ButtonModule, TableModule, InputTextModule, DialogModule, FormsModule, Select, ConfirmDialogModule],
  templateUrl: './obra-component.html',
  providers: [ConfirmationService, MessageService],
  styleUrl: './obra-component.css'
})
export class ObraComponent implements OnInit {

  displayModal = false;

  novaObra: Obra = this.criarObraVazia();
  obraSelecionadaExclusao!: Obra | undefined; 
  obras: Obra[] = [];
  generos: Genero[] = [];

  tiposObra = [
    { label: 'Filme', value: 'FILME' },
    { label: 'Serie', value: 'Serie' },
    { label: 'Anime', value: 'ANIME' },
  ];

  constructor(
    private obraService: ObraService,
    private confirmationService: ConfirmationService,
    private messageService: MessageService,
    private cd: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.buscarObras();
  }

  criarObraVazia(): Obra {
    return {
      idObra: 0,
      titulo: '',
      descricao: '',
      anoLancamento: 0,
      tipoobra: TipoObra.FILME,
      imagemUrl: '',
      genero: {
        idGenero: null,
        nome: ''
      }
    };
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

  salvarObra() {
    const obraParaSalvar: Obra = { ...this.novaObra };

    if (obraParaSalvar.idObra) {
      this.obraService.atualizarObra(obraParaSalvar.idObra, obraParaSalvar).subscribe({
        next: () => {
          this.buscarObras();
          this.resetarFormulario();
          this.displayModal = false;
        },
        error: () => {
          this.messageService.add({ severity: 'error', summary: 'Erro', detail: 'Falha ao atualizar obra' });
        }
      });
    } else {
      console.log(obraParaSalvar)
      this.obraService.criarNovaObra(obraParaSalvar).subscribe({
        next: () => {
          this.buscarObras();
          this.resetarFormulario();
          this.displayModal = false;
        },
        error: () => {
          this.messageService.add({ severity: 'error', summary: 'Erro', detail: 'Falha ao criar obra' });
        }
      });
    }
  }

  resetarFormulario() {
    this.novaObra = this.criarObraVazia();
  }

  showDialog(obra?: Obra) {
    if (obra && obra.idObra) {
      this.novaObra = { ...obra };
    } else {
      this.resetarFormulario();
      this.buscarGeneros();
    }
    this.displayModal = true;
  }

  onEdit(obra: Obra) {
    if (!obra?.idObra) return;
    console.log(obra);
    this.obraService.buscarObrasPorID(obra.idObra).subscribe({
      next: (res) => {
        this.novaObra = res;
        this.displayModal = true;
        this.buscarGeneros();
      },
      error: () => {
        this.messageService.add({ severity: 'error', summary: 'Erro', detail: 'Falha ao buscar obra' });
      }
    });
  }

  onDelete(obra: Obra, event: Event) {
    console.log(obra.idObra);
    if (!obra?.idObra) {
      console.warn('ID da obra não encontrado!');
      return;
    }
    this.obraSelecionadaExclusao = {... obra }
    this.confirm2(event);
  }

  confirm2(event: Event) {
    this.confirmationService.confirm({
      target: event.target as EventTarget,
      message: 'Tem certeza que deseja excluir esta obra?',
      header: 'Danger Zone',
      icon: 'pi pi-info-circle',
      rejectLabel: 'Cancel',
      rejectButtonProps: { label: 'Cancel', severity: 'secondary', outlined: true },
      acceptButtonProps: { label: 'Delete', severity: 'danger' },
      accept: () => {
        this.obraService.deletarObra(this.obraSelecionadaExclusao?.idObra).subscribe({
          next: () => {
            this.messageService.add({ severity: 'success', summary: 'Deleted', detail: 'Obra excluída com sucesso' });
            this.buscarObras();
          },
          error: () => {
            this.messageService.add({ severity: 'error', summary: 'Erro', detail: 'Falha ao excluir obra' });
          }
        });
      },
      reject: () => {
        this.obraSelecionadaExclusao = undefined;
      }
    });
  }
  buscarGeneros(){
    this.obraService.buscarGeneros().subscribe({
      next: (genero: Genero[]) => {
        setTimeout(() => {
          this.generos = genero;
        },500);
      }
    });
  }
}

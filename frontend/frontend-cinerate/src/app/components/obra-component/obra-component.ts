import { Component, OnInit } from '@angular/core';
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

@Component({
  selector: 'app-obra-component',
  standalone: true,
  imports: [CommonModule,ButtonModule, TableModule, InputTextModule, DialogModule, FormsModule, Select],
  templateUrl: './obra-component.html',
  styleUrl: './obra-component.css'
})
export class ObraComponent implements OnInit{

  displayModal = false;

  novaObra: Obra = {  
    idobra: 0,
    titulo: '',
    descricao: '',
    anoLancamento: 0,
    tipoobra: TipoObra.ARTIGO,
    imagemUrl: '',  
  };

  obras: Obra[] = [];

  selectedCity: Obra | undefined;

  tiposObra = [
    { label: 'Filme', value: 'FILME' },
    { label: 'Documentario', value: 'DOCUMENTARIO' },
    { label: 'Artigo', value: 'ARTIGO' },
    { label: 'Anime', value: 'ANIME' },
  ];

  constructor(private obraService: ObraService){}
  
  ngOnInit(): void {
    this.displayModal;
    this.buscarObras();
  }

  salvarObra() {
    const novaObra: Obra = {
      idobra: this.novaObra.idobra,
      titulo: this.novaObra.titulo,
      descricao: this.novaObra.descricao,
      anoLancamento: this.novaObra.anoLancamento,
      tipoobra: this.novaObra.tipoobra,
      imagemUrl: this.novaObra.imagemUrl, 
    }
    this.obraService.criarNovaObra(novaObra).subscribe({
      next: (obraSalva) => {
        setTimeout(() => {
          console.log(obraSalva);
          this.displayModal = false;
          this.resetarFormulario();
        }, 0);
      },
  })
}

  buscarObras() {
    this.obraService.buscarObras().subscribe({
      next: (obras: Obra[]) => {
        this.obras = obras;
      },
        error: (err) => {
        console.error('Erro ao buscar usuários', err);
      }
    });
  }

  showDialog(obra?: Obra) {
    this.displayModal= true;
  }

  resetarFormulario() {
    this.novaObra = { 
      idobra: 0,
      titulo: '',
      descricao: '',
      anoLancamento: 0,
      tipoobra: TipoObra.ARTIGO,
      imagemUrl: '', 
    };
  }

  onEdit(obra: Obra) {

  }
}

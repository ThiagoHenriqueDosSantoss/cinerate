import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { Genero } from '../../interface/Genero';
import { TableModule } from 'primeng/table';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-genero-component',
  standalone: true,
  imports: [FormsModule, InputTextModule, ButtonModule, TableModule, CommonModule],
  templateUrl: './genero-component.html',
  styleUrl: './genero-component.css',
})
export class GeneroComponent implements OnInit {

  visible = false;
  generos: Genero[] = []

  novoGenero: Genero = {
    idgenero: null,
    nome: ''
  }
  ngOnInit(): void {
    this.generos;
  }
  
}

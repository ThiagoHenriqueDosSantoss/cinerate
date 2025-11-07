import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Genero } from '../interface/Genero';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class GeneroService {
  private baseUrl = 'http://localhost:8080';

  constructor(private http: HttpClient){}

  salvarGenero(genero: Genero): Observable<Genero>{
    const url = `${this.baseUrl}/api/genero/novoGenero`;
    return this.http.post<Genero>(url,genero);
  }
  buscarGeneros():Observable<any> {
    const url = `${this.baseUrl}/api/genero/listarGenero`;
    return this.http.get(url);
  }
  buscarGeneroPorID(idgenero:number): Observable<Genero>{
    const url = `${this.baseUrl}/api/genero/listarGenero/${idgenero}`;
    return this.http.get<Genero>(url);
  }
  atualizaGenero(idgenero:number, genero:Genero): Observable<Genero> {
    const url = `${this.baseUrl}/api/genero/atualizarGenero/${idgenero}`;
    return this.http.put<Genero>(url, genero);
  }
  deletarGenero(idgenero:number): Observable<Genero>{
    const url = `${this.baseUrl}/api/genero/removerGenero/${idgenero}`;
    return this.http.delete<Genero>(url);
  }
}

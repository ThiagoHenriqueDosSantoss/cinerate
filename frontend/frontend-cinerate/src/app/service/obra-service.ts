import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Obra } from '../interface/Obra';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ObraService {
  private baseUrl = 'http://localhost:8080';

  constructor(private http: HttpClient){}

  public criarNovaObra(novaObra:Obra): Observable<Obra> {
    const url = `${this.baseUrl}/api/obra/novaObra`;
    return this.http.post<Obra>(url, novaObra);
  }
  public buscarObras(): Observable<Obra[]> {
    const url = `${this.baseUrl}/api/obra/listarObra`;
    return this.http.get<Obra[]>(url);
  }

  public buscarObrasPorID(idobra:number):Observable<Obra>{
    const url = `${this.baseUrl}/api/obra/listarObra/${idobra}`;
    return this.http.get<Obra>(url);
  }
  public atualizarObra(idobra:number, obraParaAtualizar:Obra):Observable<Obra>{
    const url = `${this.baseUrl}/api/obra/atualizarObra/${idobra}`;
    return this.http.put<Obra>(url,obraParaAtualizar);
  }
  public deletarObra(idobra:number):Observable<Obra>{
    const url = `${this.baseUrl}/api/obra/removerObra/${idobra}`;
    return this.http.delete<Obra>(url);
  }
  public buscarGeneros():Observable<any>{
    const url = `${this.baseUrl}/api/genero/listarGenero`;
    return this.http.get(url);
  }
}

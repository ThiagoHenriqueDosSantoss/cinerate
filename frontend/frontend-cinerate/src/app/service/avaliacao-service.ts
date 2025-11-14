import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CriarAvaliacao } from '../interface/CriarAvaliacao';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AvaliacaoService {
  private baseUrl = 'http://localhost:8080';

  constructor(private http: HttpClient){}

  criarAvaliacao(avaliacao:CriarAvaliacao):Observable<CriarAvaliacao>{
    const url = `${this.baseUrl}/api/avaliacao/novaAvaliacao`;
    return this.http.post<CriarAvaliacao>(url,avaliacao);
  }
  buscarAvaliacao():Observable<any>{
    const url = `${this.baseUrl}/api/avaliacao/listarAvaliacao`;
    return this.http.get(url);
  }
}

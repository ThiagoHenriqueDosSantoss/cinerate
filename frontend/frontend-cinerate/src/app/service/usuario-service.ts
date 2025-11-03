import { HttpBackend, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Usuario } from '../interface/Usuario';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class UsuarioService {
  private url = 'http://localhost:8080';

  private http: HttpClient;

  constructor(handler: HttpBackend){
    this.http = new HttpClient(handler);
  }

  public salvarUsuarios(novoUsuario: Usuario): Observable<Usuario>{
    return this.http.post<Usuario>(`${this.url}/usuarios`, novoUsuario);
  }
}

import { HttpBackend, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Usuario } from '../interface/Usuario';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class UsuarioService {
  private baseUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) {}  // <-- apenas isso

  public salvarUsuarios(novoUsuario: Usuario): Observable<Usuario> {
    const url = `${this.baseUrl}/api/usuario`;
    return this.http.post<Usuario>(url, novoUsuario);
  }
  public buscarUsuarios():Observable<Usuario[]>{
    const url = `${this.baseUrl}/api/usuario`;
    return this.http.get<Usuario[]>(url);
  }
}

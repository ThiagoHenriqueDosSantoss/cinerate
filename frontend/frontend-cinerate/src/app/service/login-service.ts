import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Login } from '../interface/Login';
import { LoginResponse } from '../interface/LoginResponse';

@Injectable({
  providedIn: 'root',
})
export class LoginService {
  private baseUrl = 'http://localhost:8080';

  constructor(private http: HttpClient){}

  public login(usuario: Login): Observable<LoginResponse> {
    const url = `${this.baseUrl}/api/login`;
    return this.http.post<LoginResponse>(url, usuario);
  }
}

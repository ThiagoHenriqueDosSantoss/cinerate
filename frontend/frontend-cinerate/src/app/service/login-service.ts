import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Login } from '../interface/Login';

@Injectable({
  providedIn: 'root',
})
export class LoginService {
  private baseUrl = 'http://localhost:8080';

  constructor(private http: HttpClient){}

  public login(usuario:Login):Observable<Login>{
    const url = `${this.baseUrl}/api/login`;
    return this.http.post<Login>(url,usuario);
  }
}

import { Credencial } from "../enum/Credencial";

export interface Usuario {
  idUsuario:number,
  nome: string;
  email: string;
  senha:string;
  dataDeCadastro: string;
  credencial: Credencial;
}
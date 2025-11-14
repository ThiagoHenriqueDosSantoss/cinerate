import { Obra } from "./Obra";
import { Usuario } from "./Usuario";

export interface Avaliacao {
  idavaliacao: number;
  nota: number;
  comentario: string;
  dataAvaliacao: string;
  usuario: Usuario;
  obra: Obra;
}
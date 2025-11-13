import { TipoObra } from "../enum/TipoObra";
import { Genero } from "./Genero";
import { Usuario } from "./Usuario";

export interface Obra {
  idObra: number | any;                 // id da obra
  titulo: string;                 // título da obra
  descricao: string;              // descrição da obra
  anoLancamento: number;          // ano de lançamento
  tipoobra: TipoObra;         // tipo da obra
  imagemUrl?: string;             // URL da imagem (opcional)
  genero:Genero;
  usuarios?: Usuario;
}
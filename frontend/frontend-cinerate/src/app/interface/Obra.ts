import { TipoObra } from "../enum/TipoObra";

export interface Obra {
  idObra: number | any;                 // id da obra
  titulo: string;                 // título da obra
  descricao: string;              // descrição da obra
  anoLancamento: number;          // ano de lançamento
  tipoobra: TipoObra;         // tipo da obra
  imagemUrl?: string;             // URL da imagem (opcional)
}
export interface CriarAvaliacao {
  nota: number;
  comentario: string;
  idUsuario: number | undefined;
  obra: {
    idobra: number;
  };
}
export interface LoginResponse {
  success: boolean;
  mensagem: string;
  token?: string;
  usuario?: {
    id: number;
    nome: string;
    email: string;
    credencial: string;
    dataDeCadastro: string;
  };
}
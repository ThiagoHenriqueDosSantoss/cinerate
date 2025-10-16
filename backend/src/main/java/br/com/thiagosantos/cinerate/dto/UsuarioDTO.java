package br.com.thiagosantos.cinerate.dto;


import java.time.LocalDateTime;

public class UsuarioDTO {
    private String nome;
    private String email;
    private String senha;
    private LocalDateTime dataDeCadastro;

    public UsuarioDTO(){

    }

    public UsuarioDTO(String nome, String email, String senha, LocalDateTime dataDeCadastro) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.dataDeCadastro = dataDeCadastro;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public LocalDateTime getDataDeCadastro() {
        return dataDeCadastro;
    }
}

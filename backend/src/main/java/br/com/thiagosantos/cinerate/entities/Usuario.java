package br.com.thiagosantos.cinerate.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idusuario;
    @Column(name = "nome", nullable = false)
    private String nome;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "senha", nullable = false)
    private String senha;
    @Column(name = "dataDeCadastro")
    @CreationTimestamp
    private LocalDateTime dataDeCadastro;

    @OneToMany(mappedBy = "usuario")
    private Set<UsuarioObra> usuarioObras = new HashSet<>();

    public Usuario(){

    }

    public Usuario(Long idUsuario, String nome, String email, String senha, LocalDateTime dataDeCadastro) {
        this.idusuario = idUsuario;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.dataDeCadastro = dataDeCadastro;
    }

    public Long getIdUsuario() {
        return idusuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDateTime getDataDeCadastro() {
        return dataDeCadastro;
    }

    public void setDataDeCadastro(LocalDateTime dataDeCadastro) {
        this.dataDeCadastro = dataDeCadastro;
    }
}

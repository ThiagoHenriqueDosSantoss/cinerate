package br.com.thiagosantos.cinerate.entities;

import br.com.thiagosantos.cinerate.enums.UserRoles;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@EqualsAndHashCode(of = "idusuario")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Usuario{
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
    @JsonIgnore
    private Set<Obra> usuarioObras = new HashSet<>();
    @Enumerated(EnumType.STRING)
    private UserRoles credencial;
    public Usuario(){

    }

    public Usuario(Long idusuario, String nome, String email, String senha, LocalDateTime dataDeCadastro, Set<Obra> usuarioObras, UserRoles credencial) {
        this.idusuario = idusuario;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.dataDeCadastro = dataDeCadastro;
        this.usuarioObras = usuarioObras;
        this.credencial = credencial;
    }

    public Long getIdUsuario() {
        return idusuario;
    }

    public void setIdUsuario(Long idusuario) {
        this.idusuario = idusuario;
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

    public UserRoles getCredencial() {
        return credencial;
    }

    public void setCredencial(UserRoles credencial) {
        this.credencial = credencial;
    }
}

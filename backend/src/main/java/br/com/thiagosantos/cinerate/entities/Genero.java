package br.com.thiagosantos.cinerate.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "genero")
public class Genero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("idGenero")
    private Long idgenero;

    @Column(name = "nome", nullable = false,length = 55)
    private String nome;

    @OneToMany(mappedBy = "genero")
    @JsonIgnore
    private List<Obra> obras;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idusuario", nullable = true)
    private Usuario usuario;

    public Genero() {

    }

    public Genero(Long idgenero, String nome, List<Obra> obras, Usuario usuario) {
        this.idgenero = idgenero;
        this.nome = nome;
        this.obras = obras;
        this.usuario = usuario;
    }

    public Long getIdgenero() {
        return idgenero;
    }

    public void setIdgenero(Long idgenero) {
        this.idgenero = idgenero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Obra> getObras() {
        return obras;
    }

    public void setObras(List<Obra> obras) {
        this.obras = obras;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}

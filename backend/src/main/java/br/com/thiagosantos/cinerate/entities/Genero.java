package br.com.thiagosantos.cinerate.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.HashSet;
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

    @ManyToMany
    @JoinTable(
            name = "obra_genero",
            joinColumns = @JoinColumn(name = "idgenero"),
            inverseJoinColumns = @JoinColumn(name = "idobra")
    )
    private Set<Obra> obras = new HashSet<>();

    public Genero() {

    }

    public Genero(Long idgenero, String nome, Set<Obra> obras) {
        this.idgenero = idgenero;
        this.nome = nome;
        this.obras = obras;
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

    public Set<Obra> getObras() {
        return obras;
    }

    public void setObras(Set<Obra> obras) {
        this.obras = obras;
    }
}

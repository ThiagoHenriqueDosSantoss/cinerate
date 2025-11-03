package br.com.thiagosantos.cinerate.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "genero")
public class Genero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idgenero;

    @Column(name = "nome", nullable = false,length = 55)
    private String nome;

    @JoinTable(
            name = "obra_genero",
            joinColumns = @JoinColumn(name = "idobra"),
            inverseJoinColumns = @JoinColumn(name = "idgenero")
    )
    private Set<Genero> generos = new HashSet<>();

    public Genero() {

    }

    public Genero(Long idgenero, String nome, Set<Genero> generos) {
        this.idgenero = idgenero;
        this.nome = nome;
        this.generos = generos;
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

    public Set<Genero> getObra() {
        return generos;
    }

    public void setObra(Set<Genero> generos) {
        this.generos = generos;
    }
}

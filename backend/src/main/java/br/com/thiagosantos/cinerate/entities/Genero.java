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

    @ManyToMany
    @JoinTable(
            name = "fk_obrao",
            joinColumns = @JoinColumn(name = "idgenero"),
            inverseJoinColumns = @JoinColumn(name = "idobra")
    )
    private Set<Obra> obra = new HashSet<>();

    public Genero() {

    }

    public Genero(Long idgenero, String nome, Set<Obra> obra) {
        this.idgenero = idgenero;
        this.nome = nome;
        this.obra = obra;
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

    public Set<Obra> getObra() {
        return obra;
    }

    public void setObra(Set<Obra> obra) {
        this.obra = obra;
    }
}

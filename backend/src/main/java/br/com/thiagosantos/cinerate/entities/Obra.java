package br.com.thiagosantos.cinerate.entities;

import br.com.thiagosantos.cinerate.enums.TipoObraEnum;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "obra")
public class Obra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idobra;

    @Column(name = "titulo", nullable = false,length = 255)
    private String titulo;

    @Column(name = "descricao",nullable = false,length = 255)
    private String descricao;

    @Column(name = "anoLancamento",nullable = false,length = 4)
    private Integer anoLancamento;

    @Column(name = "tipoobra", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoObraEnum tipoobra;

    @Column(name = "imagemUrl",nullable = true)
    private String imagemUrl;

    @ManyToMany
    @JoinTable(
            name = "fk_usuario",
            joinColumns = @JoinColumn(name = "idobra"),
            inverseJoinColumns = @JoinColumn(name = "idusuario")
    )
    private Set<Usuario> usuarios = new HashSet<>();

    @ManyToMany(mappedBy = "generos")
    private Set<Obra> obras = new HashSet<>();

    public Obra(){

    }

    public Obra(Long idobra, String titulo, String descricao, Integer anoLancamento, TipoObraEnum tipoobra, String imagemUrl, Set<Usuario> usuarios, Set<Obra> obra) {
        this.idobra = idobra;
        this.titulo = titulo;
        this.descricao = descricao;
        this.anoLancamento = anoLancamento;
        this.tipoobra = tipoobra;
        this.imagemUrl = imagemUrl;
        this.usuarios = usuarios;
        this.obras = obra;
    }

    public Long getIdObra() {
        return idobra;
    }

    public void setIdObra(Long idObra) {
        this.idobra = idObra;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(Integer anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public TipoObraEnum getTipoobra() {
        return tipoobra;
    }

    public void setTipoobra(TipoObraEnum tipoobra) {
        this.tipoobra = tipoobra;
    }

    public String getImagemUrl() {
        return imagemUrl;
    }

    public void setImagemUrl(String imagemUrl) {
        this.imagemUrl = imagemUrl;
    }

    public Set<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Set<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Set<Obra> getObras() {
        return obras;
    }

    public void setGenero(Set<Obra> obras) {
        this.obras = obras;
    }
}

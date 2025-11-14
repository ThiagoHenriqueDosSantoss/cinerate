package br.com.thiagosantos.cinerate.entities;

import br.com.thiagosantos.cinerate.enums.TipoObraEnum;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "obra")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Obra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("idobra")
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

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Genero genero;

    public Obra(){

    }

    public Obra(Long idobra, String titulo, String descricao, Integer anoLancamento, TipoObraEnum tipoobra, String imagemUrl,Genero genero, Usuario usuario) {
        this.idobra = idobra;
        this.titulo = titulo;
        this.descricao = descricao;
        this.anoLancamento = anoLancamento;
        this.tipoobra = tipoobra;
        this.imagemUrl = imagemUrl;
        this.genero = genero;
        this.usuario = usuario;
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

    public Usuario getUsuarios() {
        return usuario;
    }

    public void setUsuarios(Usuario usuarios) {
        this.usuario = usuarios;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }
}

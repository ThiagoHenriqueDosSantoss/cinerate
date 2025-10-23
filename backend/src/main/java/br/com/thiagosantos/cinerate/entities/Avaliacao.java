package br.com.thiagosantos.cinerate.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "avaliacao")
public class Avaliacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idavaliacao;

    @Column(name = "nota", nullable = false)
    private Integer nota;

    @Column(name = "comentario",nullable = false, length = 255)
    private String comentario;

    @Column(name = "dataavaliacao",columnDefinition = "current_timestamp", nullable = true)
    private LocalDateTime dataAvaliacao;

    @ManyToOne
    @JoinColumn(name = "usuario", referencedColumnName = "idusuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "obra", referencedColumnName = "idobra")
    private Obra obra;

    public Avaliacao(){

    }

    public Avaliacao(Long idavaliacao, Integer nota, String comentario, LocalDateTime dataAvaliacao, Usuario usuario, Obra obra) {
        this.idavaliacao = idavaliacao;
        this.nota = nota;
        this.comentario = comentario;
        this.dataAvaliacao = dataAvaliacao;
        this.usuario = usuario;
        this.obra = obra;
    }

    public Long getIdavaliacao() {
        return idavaliacao;
    }

    public void setIdavaliacao(Long idavaliacao) {
        this.idavaliacao = idavaliacao;
    }

    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public LocalDateTime getDataAvaliacao() {
        return dataAvaliacao;
    }

    public void setDataAvaliacao(LocalDateTime dataAvaliacao) {
        this.dataAvaliacao = dataAvaliacao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Obra getObra() {
        return obra;
    }

    public void setObra(Obra obra) {
        this.obra = obra;
    }
}

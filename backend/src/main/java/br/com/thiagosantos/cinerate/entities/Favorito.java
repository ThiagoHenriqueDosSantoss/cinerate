package br.com.thiagosantos.cinerate.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "favorito")
public class Favorito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idfavorito;

    @Column(name = "usuarioid")
    private Long idUsuario;

    @Column(name = "obraid")
    private Long idObra;

    @Column(name = "datainclusao", columnDefinition = "current_timestamp")
    private LocalDateTime dataInclusao;

    public Favorito(){

    }

    public Favorito(Long idfavorito, Long idUsuario, Long idObra, LocalDateTime dataInclusao) {
        this.idfavorito = idfavorito;
        this.idUsuario = idUsuario;
        this.idObra = idObra;
        this.dataInclusao = dataInclusao;
    }

    public Long getIdfavorito() {
        return idfavorito;
    }

    public void setIdfavorito(Long idfavorito) {
        this.idfavorito = idfavorito;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdObra() {
        return idObra;
    }

    public void setIdObra(Long idObra) {
        this.idObra = idObra;
    }

    public LocalDateTime getDataInclusao() {
        return dataInclusao;
    }

    public void setDataInclusao(LocalDateTime dataInclusao) {
        this.dataInclusao = dataInclusao;
    }
}

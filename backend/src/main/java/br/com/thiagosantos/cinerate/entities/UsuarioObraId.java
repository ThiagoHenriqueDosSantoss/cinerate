package br.com.thiagosantos.cinerate.entities;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UsuarioObraId implements Serializable {

    private Long idusuario;
    private Long idobra;

    public UsuarioObraId() {}

    public UsuarioObraId(Long idusuario, Long idobra) {
        this.idusuario = idusuario;
        this.idobra = idobra;
    }

    public Long getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Long idusuario) {
        this.idusuario = idusuario;
    }

    public Long getIdobra() {
        return idobra;
    }

    public void setIdobra(Long idobra) {
        this.idobra = idobra;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UsuarioObraId)) return false;
        UsuarioObraId that = (UsuarioObraId) o;
        return Objects.equals(idusuario, that.idusuario) &&
                Objects.equals(idobra, that.idobra);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idusuario, idobra);
    }
}

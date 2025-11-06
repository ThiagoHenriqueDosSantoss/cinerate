package br.com.thiagosantos.cinerate.entities;


import jakarta.persistence.*;

@Entity
@Table(name = "usuario_obra")
public class UsuarioObra {

    @EmbeddedId
    private UsuarioObraId id;

    @ManyToOne
    @MapsId("idusuario")
    @JoinColumn(name = "idusuario")
    private Usuario usuario;

    @ManyToOne
    @MapsId("idobra")
    @JoinColumn(name = "idobra")
    private Obra obra;

    public UsuarioObra() {}

    public UsuarioObra(Usuario usuario, Obra obra) {
        this.usuario = usuario;
        this.obra = obra;
        this.id = new UsuarioObraId(usuario.getIdUsuario(), obra.getIdObra());
    }
}
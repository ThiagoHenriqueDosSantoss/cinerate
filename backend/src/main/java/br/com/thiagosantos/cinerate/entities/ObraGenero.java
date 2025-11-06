package br.com.thiagosantos.cinerate.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "obra_genero")
public class ObraGenero {
    @EmbeddedId
    private ObraGeneroId id = new ObraGeneroId();

    @ManyToOne
    @MapsId("idObra")
    @JoinColumn(name = "idobra")
    private Obra obra;

    @ManyToOne
    @MapsId("idGenero")
    @JoinColumn(name = "idgenero")
    private Genero genero;

}

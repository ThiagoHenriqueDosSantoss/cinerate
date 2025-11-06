package br.com.thiagosantos.cinerate.repository;

import br.com.thiagosantos.cinerate.entities.ObraGenero;
import br.com.thiagosantos.cinerate.entities.ObraGeneroId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ObraGeneroRepository extends JpaRepository<ObraGenero, ObraGeneroId> {
}

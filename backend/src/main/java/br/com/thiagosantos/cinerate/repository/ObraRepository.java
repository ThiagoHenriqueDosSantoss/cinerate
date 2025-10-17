package br.com.thiagosantos.cinerate.repository;

import br.com.thiagosantos.cinerate.entities.Obra;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ObraRepository extends JpaRepository<Obra, Long> {
}

package br.com.thiagosantos.cinerate.repository;

import br.com.thiagosantos.cinerate.entities.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {
}

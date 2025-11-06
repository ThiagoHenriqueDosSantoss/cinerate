package br.com.thiagosantos.cinerate.repository;

import br.com.thiagosantos.cinerate.entities.UsuarioObra;
import br.com.thiagosantos.cinerate.entities.UsuarioObraId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioObraRepository extends JpaRepository<UsuarioObra, UsuarioObraId> {
}

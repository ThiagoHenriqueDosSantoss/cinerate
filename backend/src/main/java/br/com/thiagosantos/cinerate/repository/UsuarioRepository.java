package br.com.thiagosantos.cinerate.repository;

import br.com.thiagosantos.cinerate.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}

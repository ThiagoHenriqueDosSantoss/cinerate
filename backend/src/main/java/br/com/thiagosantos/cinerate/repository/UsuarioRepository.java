package br.com.thiagosantos.cinerate.repository;

import br.com.thiagosantos.cinerate.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    UserDetails findByLogin(String nome);
}

package br.com.thiagosantos.cinerate.service;

import br.com.thiagosantos.cinerate.dto.UsuarioDTO;
import br.com.thiagosantos.cinerate.entities.Usuario;
import br.com.thiagosantos.cinerate.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario criarUsuario(UsuarioDTO dto){
        Usuario u = new Usuario();
        u.setNome(dto.getNome());
        u.setSenha(dto.getSenha());
        u.setEmail(dto.getEmail());
        u.setDataDeCadastro(LocalDateTime.now());
        return this.usuarioRepository.save(u);
    }
}

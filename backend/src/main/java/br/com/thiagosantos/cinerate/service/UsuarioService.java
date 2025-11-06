package br.com.thiagosantos.cinerate.service;


import br.com.thiagosantos.cinerate.entities.Usuario;
import br.com.thiagosantos.cinerate.repository.UsuarioRepository;
import br.com.thiagosantos.cinerate.security.CriptografiaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario criarUsuario(Usuario usuario){
        Usuario u = new Usuario();
        u.setNome(usuario.getNome());
        String senhaHash = CriptografiaUtil.gerarHash(usuario.getSenha());
        u.setSenha(senhaHash);
        u.setEmail(usuario.getEmail());
        u.setDataDeCadastro(LocalDateTime.now());
        return this.usuarioRepository.save(u);
    }
    public List<Usuario> listarUsuario(){
        return this.usuarioRepository.findAll();
    }

    public Usuario atualizarUsuario(Long idusuario, Usuario usuario){
        Usuario u = new Usuario();
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(idusuario);
        usuarioOptional.get();

        if(usuario.getNome() != null){
            u.setNome(usuario.getNome());
        }
        if (usuario.getSenha()!= null){
            String senhaHash = CriptografiaUtil.gerarHash(usuario.getSenha());
            u.setSenha(senhaHash);

        }
        if(usuario.getEmail() != null){
            u.setEmail(usuario.getEmail());
        }
        return this.usuarioRepository.save(u);
    }
}

package br.com.thiagosantos.cinerate.service;


import br.com.thiagosantos.cinerate.entities.Usuario;
import br.com.thiagosantos.cinerate.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario criarUsuario(String nome, String email, String senha){
        Usuario u = new Usuario();
        u.setNome(nome);
        u.setSenha(email);
        u.setEmail(senha);
        u.setDataDeCadastro(LocalDateTime.now());
        return this.usuarioRepository.save(u);
    }
    public List<Usuario> listarUsuario(){
        return this.usuarioRepository.findAll();
    }
}

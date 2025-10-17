package br.com.thiagosantos.cinerate.controller;

import br.com.thiagosantos.cinerate.dto.UsuarioDTO;
import br.com.thiagosantos.cinerate.entities.Usuario;
import br.com.thiagosantos.cinerate.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/usuario")
    public Usuario criarUsuario(String nome, String email, String senha){
        Usuario response = this.usuarioService.criarUsuario(nome,senha,email);
        return response;
    }
}

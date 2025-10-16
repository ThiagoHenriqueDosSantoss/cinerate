package br.com.thiagosantos.cinerate.controller;

import br.com.thiagosantos.cinerate.dto.UsuarioDTO;
import br.com.thiagosantos.cinerate.entities.Usuario;
import br.com.thiagosantos.cinerate.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/usuario")
    public Usuario criarUsuario(UsuarioDTO dto){
        Usuario response = this.usuarioService.criarUsuario(dto);
        return response;
    }
}

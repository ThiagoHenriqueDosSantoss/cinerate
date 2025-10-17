package br.com.thiagosantos.cinerate.controller;


import br.com.thiagosantos.cinerate.entities.Usuario;
import br.com.thiagosantos.cinerate.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.PATCH, RequestMethod.DELETE, RequestMethod.POST})
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/usuario")
    public Usuario criarUsuario(String nome, String email, String senha){
        Usuario response = this.usuarioService.criarUsuario(nome,senha,email);
        return response;
    }
    @GetMapping("/usuario")
    public List<Usuario> listarUsuario(){
        return this.usuarioService.listarUsuario();
    }
}

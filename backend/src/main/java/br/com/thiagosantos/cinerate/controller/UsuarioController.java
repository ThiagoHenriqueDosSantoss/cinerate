package br.com.thiagosantos.cinerate.controller;


import br.com.thiagosantos.cinerate.entities.Usuario;
import br.com.thiagosantos.cinerate.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.PATCH, RequestMethod.DELETE, RequestMethod.POST})
@RequestMapping(value = "/api/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Usuario> criarUsuario(String nome, String email, String senha){
        Usuario response = this.usuarioService.criarUsuario(nome,senha,email);
        return ResponseEntity.ok(response);
    }
    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuario(){
        return ResponseEntity.ok(this.usuarioService.listarUsuario());
    }
    @PatchMapping("/{idusuario}")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable Long idusuario,Usuario usuario){
        return ResponseEntity.ok(this.usuarioService.atualizarUsuario(idusuario, usuario));
    }
}

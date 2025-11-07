package br.com.thiagosantos.cinerate.controller;


import br.com.thiagosantos.cinerate.entities.Usuario;
import br.com.thiagosantos.cinerate.repository.UsuarioRepository;
import br.com.thiagosantos.cinerate.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.POST})
@RequestMapping(value = "/api/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<Usuario> criarUsuario(@RequestBody Usuario usuario){
        Usuario response = this.usuarioService.criarUsuario(usuario);
        return ResponseEntity.ok(response);
    }
    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuario(){
        return ResponseEntity.ok(this.usuarioService.listarUsuario());
    }
    @PutMapping("/atualizar/{idusuario}")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable Long idusuario, @RequestBody Usuario usuarioAtualizado) {
        Optional<Usuario> usuarioExistente = usuarioRepository.findById(idusuario);
        if (usuarioExistente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Usuario usuario = usuarioExistente.get();
        usuario.setNome(usuarioAtualizado.getNome());
        usuario.setEmail(usuarioAtualizado.getEmail());
        usuario.setSenha(usuarioAtualizado.getSenha());
        Usuario usuarioSalvo = usuarioRepository.save(usuario);
        return ResponseEntity.ok(usuarioSalvo);
    }
    @GetMapping("/id/{idusuario}")
    public ResponseEntity<Usuario> buscarUsuarioPorID(@PathVariable Long idusuario){
        return usuarioRepository.findById(idusuario)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}

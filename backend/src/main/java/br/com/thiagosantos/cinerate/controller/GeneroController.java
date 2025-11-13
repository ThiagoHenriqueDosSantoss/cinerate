package br.com.thiagosantos.cinerate.controller;

import br.com.thiagosantos.cinerate.dto.GeneroDTO;
import br.com.thiagosantos.cinerate.entities.Genero;
import br.com.thiagosantos.cinerate.entities.Usuario;
import br.com.thiagosantos.cinerate.repository.GeneroRepository;
import br.com.thiagosantos.cinerate.repository.UsuarioRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/genero")
public class GeneroController {
    @Autowired
    GeneroRepository generoRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    // LISTAR TODOS
    @GetMapping("/listarGenero")
    public List<GeneroDTO> listar() {
        return generoRepository.findAll().stream()
                .map(g -> new GeneroDTO(
                        g.getIdgenero(),
                        g.getNome(),
                        g.getUsuario() != null ? g.getUsuario().getNome() : null
                ))
                .toList();
    }

    // OBTER POR ID
    @GetMapping("/listarGenero/{idgenero}")
    public ResponseEntity<Genero> getById(@PathVariable("idgenero") Long id) {
        Optional<Genero> object = generoRepository.findById(id);
        return object.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // NOVO
    @PostMapping("/novoGenero")
    public ResponseEntity<?> novo(@RequestBody Genero genero, HttpServletRequest request) {

        Long idUsuario = ((Number) request.getAttribute("idUsuario")).longValue();

        if (idUsuario == null) {
            return ResponseEntity.status(401).body("Usuário não identificado no token.");
        }

        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        genero.setUsuario(usuario);
        Genero response = generoRepository.save(genero);

        return ResponseEntity.ok(response);
    }

    // REMOVER
    @DeleteMapping("/removerGenero/{idgenero}")
    public ResponseEntity<Void> remover(@PathVariable("idgenero") Long id) {
        Optional<Genero> object = generoRepository.findById(id);
        if (object.isPresent()) {
            generoRepository.delete(object.get());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    // ATUALIZAR
    @PutMapping("/atualizarGenero/{idgenero}")
    public ResponseEntity<?> atualizar(@PathVariable("idgenero") Long id,
                                       @RequestBody Genero novoGenero,
                                       HttpServletRequest request) {

        Long idUsuario = ((Number) request.getAttribute("idUsuario")).longValue();

        if (idUsuario == null) {
            return ResponseEntity.status(401).body("Usuário não identificado no token.");
        }

        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Optional<Genero> generoExistente = generoRepository.findById(id);

        if (generoExistente.isPresent()) {
            novoGenero.setIdgenero(id);
            novoGenero.setUsuario(usuario);

            Genero response = generoRepository.save(novoGenero);

            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(404).body("Gênero não encontrado");
    }
}

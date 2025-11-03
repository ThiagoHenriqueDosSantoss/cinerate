package br.com.thiagosantos.cinerate.controller;

import br.com.thiagosantos.cinerate.entities.Genero;
import br.com.thiagosantos.cinerate.repository.GeneroRepository;
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

    // LISTAR TODOS
    @GetMapping("/listarGenero")
    public ResponseEntity<List<Genero>> listar() {
        return ResponseEntity.ok((List<Genero>) generoRepository.findAll());
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
    public ResponseEntity<Genero> novo(@RequestBody Genero genero) {
        return ResponseEntity.ok(generoRepository.save(genero));
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
    public ResponseEntity<Genero> atualizar(@PathVariable("idgenero") Long id,
                                            @RequestBody Genero novoGenero) {
        Optional<Genero> object = generoRepository.findById(id);
        if (object.isPresent()) {
            novoGenero.setIdgenero(id); // garante que o ID do objeto atualizado seja o mesmo
            return ResponseEntity.ok(generoRepository.save(novoGenero));
        }
        return ResponseEntity.notFound().build();
    }
}

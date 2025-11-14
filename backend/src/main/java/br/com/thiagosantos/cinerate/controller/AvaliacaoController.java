package br.com.thiagosantos.cinerate.controller;

import br.com.thiagosantos.cinerate.entities.Avaliacao;
import br.com.thiagosantos.cinerate.entities.Obra;
import br.com.thiagosantos.cinerate.entities.Usuario;
import br.com.thiagosantos.cinerate.repository.AvaliacaoRepository;
import br.com.thiagosantos.cinerate.repository.ObraRepository;
import br.com.thiagosantos.cinerate.repository.UsuarioRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/avaliacao")
public class AvaliacaoController {
    @Autowired
    AvaliacaoRepository avalicaoRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    ObraRepository obraRepository;

    // LISTAR TODOS
    @GetMapping("/listarAvaliacao")
    public ResponseEntity<List<Avaliacao>> listar() {
        return ResponseEntity.ok((List<Avaliacao>) avalicaoRepository.findAll());
    }

    // OBTER POR ID
    @GetMapping("/listarAvaliacao/{idavaliacao}")
    public ResponseEntity<Avaliacao> getById(@PathVariable("id") Long id) {
        Optional<Avaliacao> object = avalicaoRepository.findById(id);
        return object.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // NOVO
    @PostMapping("/novaAvaliacao")
    public ResponseEntity<Avaliacao> novo(@RequestBody Avaliacao avaliacao, HttpServletRequest request) {
        Long idUsuario = ((Number) request.getAttribute("idUsuario")).longValue();

        if (idUsuario == null) {
            return ResponseEntity.status(401).build();
        }

        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        avaliacao.setUsuario(usuario);

        Obra obra = obraRepository.findById(avaliacao.getObra().getIdObra())
                .orElseThrow(() -> new RuntimeException("Obra não encontrada"));

        avaliacao.setObra(obra);
        return ResponseEntity.ok(avalicaoRepository.save(avaliacao));
    }

    // REMOVER
    @DeleteMapping("/remover/{id}")
    public ResponseEntity<Void> remover(@PathVariable("idavaliacao") Long id) {
        Optional<Avaliacao> object = avalicaoRepository.findById(id);
        if (object.isPresent()) {
           avalicaoRepository.delete(object.get());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    // ATUALIZAR
    @PutMapping("/atualizarAvaliacao/{idavaliacao}")
    public ResponseEntity<Avaliacao> atualizar(@PathVariable("id") Long id,
                                               @RequestBody Avaliacao novaAvaliacao) {
        Optional<Avaliacao> object = avalicaoRepository.findById(id);
        if (object.isPresent()) {
            novaAvaliacao.setIdavaliacao(id); // garante que o ID do objeto atualizado seja o mesmo
            return ResponseEntity.ok(avalicaoRepository.save(novaAvaliacao));
        }
        return ResponseEntity.notFound().build();
    }
}

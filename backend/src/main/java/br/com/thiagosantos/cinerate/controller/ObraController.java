package br.com.thiagosantos.cinerate.controller;

import br.com.thiagosantos.cinerate.entities.Obra;
import br.com.thiagosantos.cinerate.repository.ObraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/obra")
public class ObraController {

    @Autowired
    ObraRepository obraRepository;

    // LISTAR TODOS
    @GetMapping("/listarObra")
    public ResponseEntity<List<Obra>> listar() { return ResponseEntity.ok((List<Obra>) obraRepository.findAll()); }

    // OBTER POR ID
    @GetMapping("/listarObra/{idobra}")
    public ResponseEntity<Obra> getById(@PathVariable("idobra") Long id) {
        Optional<Obra> object = obraRepository.findById(id);
        return object.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // NOVO
    @PostMapping("/novaObra")
    public ResponseEntity<Obra> novo(@RequestBody Obra obra) {
        obra.setIdObra(null);
        Obra o = new Obra(
                obra.getIdObra(),
                obra.getTitulo(),
                obra.getDescricao(),
                obra.getAnoLancamento(),
                obra.getTipoobra(),
                obra.getImagemUrl(),
                obra.getGenero()
        );

        return ResponseEntity.ok(obraRepository.save(obra));
    }

    // REMOVER
    @DeleteMapping("/removerObra/{idobra}")
    public ResponseEntity<Void> remover(@PathVariable("idobra") Long id) {
        Optional<Obra> object = obraRepository.findById(id);
        if (object.isPresent()) {
            obraRepository.delete(object.get());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    // ATUALIZAR
    @PutMapping("/atualizarObra/{idobra}")
    public ResponseEntity<Obra> atualizar(@PathVariable("idobra") Long id,
                                          @RequestBody Obra novoObra) {
        Optional<Obra> object = obraRepository.findById(id);
        if (object.isPresent()) {
            // garante que o ID do objeto atualizado seja o mesmo
            novoObra.setIdObra(id);
            return ResponseEntity.ok(obraRepository.save(novoObra));
        }
        return ResponseEntity.notFound().build();
    }
}

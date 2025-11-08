package br.com.thiagosantos.cinerate.controller;

import br.com.thiagosantos.cinerate.dto.LoginResponseDTO;
import br.com.thiagosantos.cinerate.entities.Login;
import br.com.thiagosantos.cinerate.entities.Usuario;
import br.com.thiagosantos.cinerate.repository.UsuarioRepository;
import br.com.thiagosantos.cinerate.security.CriptografiaUtil;
import br.com.thiagosantos.cinerate.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.POST})
@RequestMapping(value = "/api/login")
public class LoginController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody Login login) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByNome(login.getNome());

        if (usuarioOpt.isEmpty() || !login.getSenha().equals(usuarioOpt.get().getSenha())) {
            return ResponseEntity.status(401).body("Usuário ou senha incorreta");
        }

        Usuario usuario = usuarioOpt.get();
        String token = JwtUtil.gerarToken(usuario.getNome());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }
}

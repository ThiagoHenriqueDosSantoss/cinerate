package br.com.thiagosantos.cinerate.controller;

import br.com.thiagosantos.cinerate.dto.LoginResponseDTO;
import br.com.thiagosantos.cinerate.entities.Usuario;
import br.com.thiagosantos.cinerate.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.POST})
@RequestMapping(value = "/api/login")
public class LoginController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity login(UserDetails data){
        UserDetails response = this.usuarioRepository.findByLogin(data.getUsername());
        if(response != null){
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.notFound().build();
    }
}

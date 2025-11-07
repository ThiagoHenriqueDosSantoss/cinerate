package br.com.thiagosantos.cinerate.controller;

import br.com.thiagosantos.cinerate.dto.LoginResponseDTO;
import br.com.thiagosantos.cinerate.entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.POST})
@RequestMapping(value = "/api/login")
public class LoginController {
    @Autowired
    private AuthenticationManager authenticationManager;


    @PostMapping
    public ResponseEntity login(){
    return null;
    }
}

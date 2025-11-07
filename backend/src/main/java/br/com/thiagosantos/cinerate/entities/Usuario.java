package br.com.thiagosantos.cinerate.entities;

import br.com.thiagosantos.cinerate.enums.UserRoles;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@EqualsAndHashCode(of = "idusuario")
public class Usuario implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idusuario;
    @Column(name = "login", nullable = false)
    private String login;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "senha", nullable = false)
    private String senha;
    @Column(name = "dataDeCadastro")
    @CreationTimestamp
    private LocalDateTime dataDeCadastro;

    @OneToMany(mappedBy = "usuario")
    @JsonIgnore
    private Set<Obra> usuarioObras = new HashSet<>();
    @Enumerated(EnumType.STRING)
    private UserRoles credencial;
    public Usuario(){

    }

    public Usuario(Long idusuario, String login, String email, String senha, LocalDateTime dataDeCadastro, Set<Obra> usuarioObras, UserRoles credencial) {
        this.idusuario = idusuario;
        this.login = login;
        this.email = email;
        this.senha = senha;
        this.dataDeCadastro = dataDeCadastro;
        this.usuarioObras = usuarioObras;
        this.credencial = credencial;
    }

    public Long getIdUsuario() {
        return idusuario;
    }

    public void setIdUsuario(Long idusuario) {
        this.idusuario = idusuario;
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDateTime getDataDeCadastro() {
        return dataDeCadastro;
    }

    public void setDataDeCadastro(LocalDateTime dataDeCadastro) {
        this.dataDeCadastro = dataDeCadastro;
    }

    public UserRoles getCredencial() {
        return credencial;
    }

    public void setCredencial(UserRoles credencial) {
        this.credencial = credencial;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.credencial == UserRoles.ADMIN){
            return List.of(
              new SimpleGrantedAuthority("ROLE_ADMIN"),
              new SimpleGrantedAuthority("ROLE_USER")
            );
        }
        else{
            return List.of(
                    new SimpleGrantedAuthority("ROLE_USER")
            );
        }
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}

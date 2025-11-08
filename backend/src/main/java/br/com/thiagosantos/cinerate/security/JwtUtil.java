package br.com.thiagosantos.cinerate.security;

import br.com.thiagosantos.cinerate.entities.Usuario;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

public class JwtUtil {

    private static final String SECRET_BASE64 = Base64.getEncoder().encodeToString("SUA_CHAVE_SECRETA_MUITO_LONGA_AQUI_ substitua".getBytes());
    private static final Key key = Keys.hmacShaKeyFor(Base64.getDecoder().decode(SECRET_BASE64));

    private static final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hora

    public static String gerarToken(Usuario usuario) {
        return Jwts.builder()
                .setSubject(usuario.getNome())
                .setIssuedAt(new Date())
                .claim("idUsuario", usuario.getIdUsuario()) // use exatamente o mesmo nome de claim que vai extrair
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key)
                .compact();
    }

    public static Claims getClaims(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            return null;
        }
    }

    public static Long getIdUsuarioDoToken(String token) {
        Claims claims = getClaims(token);
        if (claims == null) return null;
        Object id = claims.get("idusuario"); // importante: mesmo nome
        return id != null ? Long.parseLong(id.toString()) : null;
    }
}

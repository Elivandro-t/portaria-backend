package com.portariacd.portaria.infrastructure.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.portariacd.portaria.domain.models.auth.Permission;
import com.portariacd.portaria.domain.models.auth.Usuario;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TokenConfigure {
    public String geraToken(Usuario usuario){
        Set<String> permissoes = usuario.getPerfil().getPermissoes().stream().map(Permission::getName)
                .collect(Collectors.toSet());
        List<String> permissoesList = permissoes.stream().toList();
        try {
            var algorith = Algorithm.HMAC256("colocarChaveSecreta");
            return JWT.create()
                    .withIssuer("Portaria")
                    .withClaim("filial", usuario.getFilial())
                    .withClaim("id", usuario.getId())
                    .withClaim("nome",usuario.getNome())
                    .withClaim("permissoes", permissoesList)
                    .withClaim("perfil", usuario.getPerfil().getNome())
                    .withSubject(usuario.getEmail())
                    .withExpiresAt(ExpiredToken())
                    .sign(algorith);
        }catch (JWTCreationException exception) {
            throw new RuntimeException("Erro na geração de token!");
        }
    }
    private Instant ExpiredToken() {
        return LocalDateTime.now().plusHours(8).toInstant(ZoneOffset.of("-03:00"));
    }

    public String validaToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("colocarChaveSecreta");
            return JWT.require(algorithm)
                    .withIssuer("Portaria")
                    .build()
                    .verify(token).getSubject();
        } catch (JWTVerificationException exception) {
            throw new RuntimeException(exception);
            // Invalid signature/claims
        }
    }
}

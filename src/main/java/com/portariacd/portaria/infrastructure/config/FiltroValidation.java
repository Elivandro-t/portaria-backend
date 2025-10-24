package com.portariacd.portaria.infrastructure.config;

import com.portariacd.portaria.infrastructure.persistence.UsuarioEntity;
import com.portariacd.portaria.infrastructure.persistence.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class FiltroValidation extends OncePerRequestFilter {
    private UsuarioRepository repository;
    private TokenConfigure tokenConfigure;
    public  FiltroValidation(UsuarioRepository usuarioRepository,TokenConfigure tokenConfigure){
        this.repository = usuarioRepository;
        this.tokenConfigure = tokenConfigure;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods",
                "ACL, CANCELUPLOAD, CHECKIN, CHECKOUT, COPY, DELETE, GET, HEAD, LOCK, MKCALENDAR, MKCOL, MOVE, OPTIONS, POST, PROPFIND, PROPPATCH, PUT, REPORT, SEARCH, UNCHECKOUT, UNLOCK, UPDATE, VERSION-CONTROL");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers",
                "Origin, X-Requested-With, Content-Type, Accept, Key, Authorization");

        // Se for uma preflight request, responde direto e não continua o filtro
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            return;
        }
        String token = ValidaToken(request);
        if (token != null) {

            var authservice = tokenConfigure.validaToken(token);
            UsuarioEntity usuario = repository.findOneByEmail(authservice).orElseThrow(
                    ()-> new RuntimeException("Usuario não encontrado")
            );
                var auth = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(auth);

        }
        filterChain.doFilter(request,response);
    }
    private String ValidaToken(HttpServletRequest request) {
        String auth = request.getHeader("Authorization");
        if(auth!=null){
            return auth.replace("Bearer","").trim();
        }
        return null;
    }
}

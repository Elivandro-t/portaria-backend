package com.portariacd.portaria.infrastructure.adapters;

import com.portariacd.portaria.domain.gateways.UsuarioGatewayRepository;
import com.portariacd.portaria.domain.models.vo.RegistroPortaria.RequestPortariaDTO;
import com.portariacd.portaria.domain.models.vo.usuarioVO.*;
import com.portariacd.portaria.infrastructure.config.TokenConfigure;
import com.portariacd.portaria.domain.models.auth.Usuario;
import com.portariacd.portaria.domain.models.vo.CadastroUsuarioDto;
import com.portariacd.portaria.infrastructure.persistence.PerfilEntity;
import com.portariacd.portaria.infrastructure.persistence.PerfilRepository;
import com.portariacd.portaria.infrastructure.persistence.UsuarioEntity;
import com.portariacd.portaria.infrastructure.persistence.UsuarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UsuarioFacture implements UsuarioGatewayRepository {
    private final UsuarioRepository repository;
    private final PerfilRepository perfilRepository;
    private AuthenticationManager authenticationManager;
    private final TokenConfigure tokenConfigure;

    public  UsuarioFacture(UsuarioRepository repository,
                           PerfilRepository perfilRepository
                           ,AuthenticationManager authenticationManager,
                           TokenConfigure tokenConfigure
    ){
        this.repository = repository;
        this.tokenConfigure = tokenConfigure;
        this.perfilRepository = perfilRepository;
        this.authenticationManager = authenticationManager;
    }

    public void registroUsuario(CadastroUsuarioDto dto){
       if(repository.findOneByEmail(dto.email()).isPresent()) {
           throw new RuntimeException("Usuario ja existe!");
       };
       PerfilEntity perfil =  perfilRepository.findById(dto.perfilId()).orElseThrow(
                ()-> new RuntimeException("Erro ao buscar Perfil"));
        Usuario usuario = new Usuario(dto, LocalDateTime.now(),true);
       var usuarioEntity = new UsuarioEntity(usuario,perfil);
        if (perfil.getUsuario() != null) {
            perfil.getUsuario().add(usuarioEntity);
        } else {
            perfil.setUsuario(new ArrayList<>());
            perfil.getUsuario().add(usuarioEntity);
        }
         repository.save(usuarioEntity);
    }
    public Page<UsuarioRequestDTO> listaUsuario(Pageable page,String busca){
        Page<UsuarioRequestDTO> pageLista;
       if(busca !=null && !busca.isEmpty()){
          pageLista = repository.findAllByUsuario(page,busca)
                   .map(UsuarioRequestDTO::new);
       }else {
           pageLista = repository.findAllByUsuarioRegistrado(page);
       }
        return pageLista;
    }

    @Override
    public TokenResponse authLogin(UsuarioLoginDTO dto) {
        var token = new UsernamePasswordAuthenticationToken(dto.email(), dto.password());
        var entity = authenticationManager.authenticate(token);

        var usuario = entity.getPrincipal();

        var usuarioModel = new Usuario((UsuarioEntity) usuario);
//        List<String> permissoes = usuarioModel.getRole().stream()
//                .flatMap(r -> r.getPermitions().stream())
//                .map(Permition::getName)
//                .toList();

        String tokenGerado = tokenConfigure.geraToken(usuarioModel);
        return new TokenResponse(tokenGerado,new usuarioRequestDTO(((UsuarioEntity) usuario).getId()));
    }

}

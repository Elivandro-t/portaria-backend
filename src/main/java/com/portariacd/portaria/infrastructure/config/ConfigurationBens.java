package com.portariacd.portaria.infrastructure.config;

import com.portariacd.portaria.infrastructure.persistence.*;
import com.portariacd.portaria.infrastructure.validation.ValidaStatusPortaria;
import com.portariacd.portaria.infrastructure.adapters.RegistroPortariaRepositoryAdapter;
import com.portariacd.portaria.infrastructure.adapters.UsuarioFacture;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;

@Configuration
public class ConfigurationBens {

    @Bean
  public UsuarioFacture usuarioFacture(UsuarioRepository repository,
                                       PerfilRepository perfilRepository,
                                       AuthenticationManager authenticationManager,
                                       TokenConfigure tokenConfigure
    ){
      return new UsuarioFacture(repository,perfilRepository,authenticationManager,tokenConfigure);
  }

//  @Bean
//  public RegistroPortariaRepositoryAdapter registroPortariaFacture(RegistroVisitanteRepository repository
//          , VisitanteRepository visitante
//          , UsuarioRepository usuarioRepository,
//                                                                   ValidaStatusPortaria validaStatusPortaria,
//                                                                   HistoryRepository history
//  ){
//        return new RegistroPortariaRepositoryAdapter(repository,visitante,usuarioRepository,validaStatusPortaria,history);
//  }
}

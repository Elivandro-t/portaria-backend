package com.portariacd.portaria.infrastructure.config;

import com.portariacd.portaria.application.services.UsuarioService;
import com.portariacd.portaria.domain.gateways.BlocoInterfaceGateway;
import com.portariacd.portaria.domain.models.vo.CadastroUsuarioDto;
import com.portariacd.portaria.domain.models.vo.bloco.RegistroBlocoDTO;
import com.portariacd.portaria.infrastructure.persistence.BlocoRepository;
import com.portariacd.portaria.infrastructure.persistence.PerfilEntity;
import com.portariacd.portaria.infrastructure.persistence.PerfilRepository;
import com.portariacd.portaria.infrastructure.persistence.UsuarioRepository;
import com.portariacd.portaria.infrastructure.persistence.blocos.BlocoEntity;
import com.portariacd.portaria.infrastructure.persistence.funcao.PermissionEntity;
import com.portariacd.portaria.infrastructure.persistence.funcao.PermissionRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.*;
@Component
public class DataInitializer implements CommandLineRunner {
    private final PerfilRepository perfilRepository;
    private final PermissionRepository permissionRepository;
    private final BlocoInterfaceGateway repository;
    private UsuarioService usuarioService;
    private UsuarioRepository usuarioRepository;
    BlocoRepository blocoRepository;
    public DataInitializer(PerfilRepository perfilRepository,
                           PermissionRepository permissionRepository,
                           UsuarioService service,
                           UsuarioRepository usuarioRepository,
                           BlocoInterfaceGateway repository,
                           BlocoRepository blocoRepository
    ) {
        this.perfilRepository = perfilRepository;
        this.permissionRepository = permissionRepository;
        this.usuarioService = service;
        this.usuarioRepository = usuarioRepository;
        this.repository = repository;
        this.blocoRepository = blocoRepository;
    }
    @Override
    @Transactional
    public void run(String... args) {
        criarPermissoes();
        criarPerfis();
        criarUsuario();
        save();
    }
    private void criarPermissoes() {
        List<String> permissoesDesejadas = List.of(
                "CRIAR_REGISTRO", "EDITAR_REGISTRO", "DELETAR_REGISTRO", "VISUALIZAR_REGISTRO",
                "GERENCIAR_USUARIOS", "REGISTRAR_ENTRADA", "REGISTRAR_SAIDA",
                "GERAR_RELATORIO", "VISUALIZAR_VISITANTES", "ALTERAR_CONFIGURACOES"
        );
        Set<String> existentes = new HashSet<>(permissionRepository.findAll()
                .stream()
                .map(PermissionEntity::getName)
                .toList());

        for (String nome : permissoesDesejadas) {
            if (!existentes.contains(nome)) {
                PermissionEntity p = new PermissionEntity();
                p.setName(nome);
                p.setAtivo(true);
                permissionRepository.save(p);
            }
        }
    }
    private void criarPerfis() {
        Map<String, PermissionEntity> permissoesMap = new HashMap<>();
        for (PermissionEntity p : permissionRepository.findAll()) {
            permissoesMap.put(p.getName(), p);
        }
        // === ADMIN ===
        if (!perfilRepository.existsByNome("ADMIN")) {
            PerfilEntity admin = new PerfilEntity();
            admin.setNome("ADMIN");
            perfilRepository.saveAndFlush(admin);
            admin.setPermissoes(new HashSet<>(Arrays.asList(
                    permissoesMap.get("CRIAR_REGISTRO"),
                    permissoesMap.get("EDITAR_REGISTRO"),
                    permissoesMap.get("DELETAR_REGISTRO"),
                    permissoesMap.get("MEUS_REGISTROS"),
                    permissoesMap.get("VISUALIZAR_REGISTRO"),
                    permissoesMap.get("GERENCIAR_USUARIOS"),
                    permissoesMap.get("GERAR_RELATORIO"),
                    permissoesMap.get("ALTERAR_CONFIGURACOES"),
                    permissoesMap.get("REGISTRAR_ENTRADA"),
                    permissoesMap.get("REGISTRAR_SAIDA"),
                    permissoesMap.get("VISUALIZAR_VISITANTES")
            )));            perfilRepository.save(admin);
        }
        // === FISCAL ===
        if (!perfilRepository.existsByNome("FISCAL")) {
            PerfilEntity fiscal = new PerfilEntity();
            fiscal.setNome("FISCAL");
            perfilRepository.saveAndFlush(fiscal);
            fiscal.setPermissoes(
                    new HashSet<>(Arrays.asList(
                            permissoesMap.get("REGISTRAR_ENTRADA"),
                            permissoesMap.get("REGISTRAR_SAIDA"),
                            permissoesMap.get("VISUALIZAR_REGISTRO"),
                            permissoesMap.get("VISUALIZAR_VISITANTES")

                            )));
            perfilRepository.save(fiscal);
        }
        // === AUTORIZADO ===
        if (!perfilRepository.existsByNome("AUTORIZADOR")) {
            PerfilEntity autorizado = new PerfilEntity();
            autorizado.setNome("AUTORIZADOR");
            perfilRepository.saveAndFlush(autorizado);
            autorizado.setPermissoes(
            new HashSet<>(Arrays.asList(
                    permissoesMap.get("VISUALIZAR_REGISTRO"),
                    permissoesMap.get("EDITAR_REGISTRO"),
                    permissoesMap.get("CRIAR_REGISTRO"),
                    permissoesMap.get("MEUS_REGISTROS")

                    ))
            );
            perfilRepository.save(autorizado);
        }
    }
    private void criarUsuario(){
        var usuario = new CadastroUsuarioDto("Grupomateus","admin@grupomateus.com.br","@Admin2025@#$","SUPORTE",116, 1l);
       var usuarioEntity = usuarioRepository.findOneByEmail(usuario.email());
        if(usuarioEntity.isEmpty()) {
            usuarioService.registroUsuario(usuario);
        }
    }
    private void save(){
        Set<String> existentes = new HashSet<>(blocoRepository.findAll()
                .stream()
                .map(BlocoEntity::getNome)
                .toList());
        Set<RegistroBlocoDTO> blocos = Set.of(
                new RegistroBlocoDTO("Secos"),
                new RegistroBlocoDTO("Hortifrúti"),
                new RegistroBlocoDTO("Frios"),
                new RegistroBlocoDTO("Fatiados"),
                new RegistroBlocoDTO("Indústria"),
                new RegistroBlocoDTO("Spazio"),
                new RegistroBlocoDTO("Oficina Caminhões"),
                new RegistroBlocoDTO("Material Logistico")
        );
        for(RegistroBlocoDTO e:blocos){
             if(!existentes.contains(e.nome())){
                 repository.registroBloco(e);
             }
        }
    }
}



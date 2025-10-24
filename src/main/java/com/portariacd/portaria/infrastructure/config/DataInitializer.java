package com.portariacd.portaria.infrastructure.config;

import com.portariacd.portaria.infrastructure.persistence.PerfilEntity;
import com.portariacd.portaria.infrastructure.persistence.PerfilRepository;
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
    public DataInitializer(PerfilRepository perfilRepository, PermissionRepository permissionRepository) {
        this.perfilRepository = perfilRepository;
        this.permissionRepository = permissionRepository;
    }
    @Override
    @Transactional
    public void run(String... args) {
        criarPermissoes();
        criarPerfis();
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
            autorizado.setNome("AUTORIZADO");
            perfilRepository.saveAndFlush(autorizado);
            autorizado.setPermissoes(
            new HashSet<>(Arrays.asList(
                    permissoesMap.get("VISUALIZAR_REGISTRO"),
                    permissoesMap.get("GERENCIAR_USUARIOS"),
                    permissoesMap.get("CRIAR_REGISTRO"),
                    permissoesMap.get("MEUS_REGISTROS")

                    ))
            );
            perfilRepository.save(autorizado);
        }
    }
}



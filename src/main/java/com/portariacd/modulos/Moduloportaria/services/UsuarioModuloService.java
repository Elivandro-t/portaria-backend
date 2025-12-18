package com.portariacd.modulos.Moduloportaria.services;

import com.portariacd.modulos.Moduloportaria.domain.gateways.ModuloGatewayRepository;
import com.portariacd.modulos.Moduloportaria.domain.models.vo.SistemaAcessoDTO;
import com.portariacd.modulos.Moduloportaria.domain.models.vo.usuarioVO.SistemaAcessoUsuarioDTO;
import com.portariacd.modulos.Moduloportaria.infrastructure.persistence.modulosPerfil.ModuleDTO;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UsuarioModuloService {
    private ModuloGatewayRepository repository;
    public UsuarioModuloService(ModuloGatewayRepository repository){
        this.repository = repository;
    }
    public void addPermission(@Valid List<ModuleDTO> pemission, Long usuarioId){
        repository.addPermission(pemission,usuarioId);
    }
    public List<SistemaAcessoUsuarioDTO> lista(){
       return repository.listaPermission();
    }
}

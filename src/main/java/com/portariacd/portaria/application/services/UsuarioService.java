package com.portariacd.portaria.application.services;

import com.portariacd.portaria.domain.gateways.UsuarioGatewayRepository;
import com.portariacd.portaria.domain.models.vo.CadastroUsuarioDto;
import com.portariacd.portaria.domain.models.vo.usuarioVO.ResponseUsuarioDTO;
import com.portariacd.portaria.domain.models.vo.usuarioVO.TokenResponse;
import com.portariacd.portaria.domain.models.vo.usuarioVO.UsuarioLoginDTO;
import com.portariacd.portaria.domain.models.vo.usuarioVO.UsuarioRequestDTO;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UsuarioService{
    private final UsuarioGatewayRepository repository;
    public UsuarioService(UsuarioGatewayRepository repository){
        this.repository = repository;
    }

    public void registroUsuario(CadastroUsuarioDto dto) {
       repository.registroUsuario(dto);
    }

    public Page<UsuarioRequestDTO> listaUsuario(Pageable page,String busca) {
        return repository.listaUsuario(page,busca);
    }

    public TokenResponse authLogin(@Valid UsuarioLoginDTO dto) {
        return repository.authLogin(dto);
    }
}

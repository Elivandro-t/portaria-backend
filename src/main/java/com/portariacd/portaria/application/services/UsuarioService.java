package com.portariacd.portaria.application.services;

import com.portariacd.portaria.domain.gateways.UsuarioGatewayRepository;
import com.portariacd.portaria.domain.models.vo.CadastroUsuarioDto;
import com.portariacd.portaria.domain.models.vo.usuarioVO.*;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
    public Map<String, UsuarioRequestPerfilDTO> buscaUsuarioPerfil(String email) {
        return repository.buscaUsuarioPerfil(email);
    }


    public void adicionarPerfil(long usuarioId, long perfilId) {
        repository.adicionarPerfil(usuarioId,perfilId);
    }
    public String AlteraSenha(String email){
        return repository.AlteraSenha(email);
    }

    public UsuarioBuscaRequestDTO buscaUsuarioId(Long id) {
        return repository.buscarUsuarioId(id);
    }
    public void salvaImagem(Long usuarioId, MultipartFile file) {
         repository.salvaImagem(usuarioId,file);
    }
}


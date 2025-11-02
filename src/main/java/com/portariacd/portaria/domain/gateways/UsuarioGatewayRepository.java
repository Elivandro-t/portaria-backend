package com.portariacd.portaria.domain.gateways;

import com.portariacd.portaria.domain.models.vo.CadastroUsuarioDto;
import com.portariacd.portaria.domain.models.vo.usuarioVO.*;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface UsuarioGatewayRepository {
     void registroUsuario(CadastroUsuarioDto dto);
     Page<UsuarioRequestDTO> listaUsuario(Pageable page, String busca);

     TokenResponse authLogin(@Valid UsuarioLoginDTO dto);

     Map<String,UsuarioRequestPerfilDTO> buscaUsuarioPerfil(String email);

     void adicionarPerfil(long usuarioId, long perfilId);
     String AlteraSenha(String email);

     UsuarioBuscaRequestDTO buscarUsuarioId(Long id);
     void salvaImagem(Long usuarioId, MultipartFile file);
}

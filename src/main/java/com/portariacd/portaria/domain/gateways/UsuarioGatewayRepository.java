package com.portariacd.portaria.domain.gateways;

import com.portariacd.portaria.domain.models.vo.CadastroUsuarioDto;
import com.portariacd.portaria.domain.models.vo.usuarioVO.ResponseUsuarioDTO;
import com.portariacd.portaria.domain.models.vo.usuarioVO.TokenResponse;
import com.portariacd.portaria.domain.models.vo.usuarioVO.UsuarioLoginDTO;
import com.portariacd.portaria.domain.models.vo.usuarioVO.UsuarioRequestDTO;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UsuarioGatewayRepository {
     void registroUsuario(CadastroUsuarioDto dto);
     Page<UsuarioRequestDTO> listaUsuario(Pageable page, String busca);

     TokenResponse authLogin(@Valid UsuarioLoginDTO dto);
}

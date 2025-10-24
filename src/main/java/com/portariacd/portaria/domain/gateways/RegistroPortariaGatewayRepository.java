package com.portariacd.portaria.domain.gateways;

import com.portariacd.portaria.domain.models.vo.RegistroPortaria.RequestPortariaDTO;
import com.portariacd.portaria.domain.models.vo.RegistroPortaria.RegistroPortariaDTO;
import com.portariacd.portaria.domain.models.vo.RegistroPortaria.StatusAtualizadoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface RegistroPortariaGatewayRepository {
     String registroPortaria(RegistroPortariaDTO request, MultipartFile file);

     Page<RequestPortariaDTO> listaPostaria(Pageable pageable, Integer filial,String busca);

     StatusAtualizadoDTO atualizaEntrada(Long ficalId, long registroId, MultipartFile file);
     StatusAtualizadoDTO atualizaSaida(Long ficalId,long registroId,MultipartFile file);

     Map<String, List<RequestPortariaDTO>> solitacaoUsuario(Long usuarioID);
     Map<String, List<RequestPortariaDTO>> FindAllStatus(String Status);

     Page<RequestPortariaDTO> listaPendentes(Pageable pageable, Integer filial,String busca);

    RequestPortariaDTO visulizarRegistro(Long registro);
}

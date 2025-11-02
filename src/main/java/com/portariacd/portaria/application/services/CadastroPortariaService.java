package com.portariacd.portaria.application.services;

import com.portariacd.portaria.domain.gateways.RegistroPortariaGatewayRepository;
import com.portariacd.portaria.domain.models.vo.RegistroPortaria.AtualizaRegistro;
import com.portariacd.portaria.domain.models.vo.RegistroPortaria.RequestPortariaDTO;
import com.portariacd.portaria.domain.models.vo.RegistroPortaria.RegistroPortariaDTO;
import com.portariacd.portaria.domain.models.vo.RegistroPortaria.StatusAtualizadoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Service
public class CadastroPortariaService {
    private final RegistroPortariaGatewayRepository repository;
   public CadastroPortariaService(RegistroPortariaGatewayRepository repository){
        this.repository = repository;
    }

    public String cadastro(RegistroPortariaDTO registroPortariaDTO, MultipartFile file){
       return repository.registroPortaria(registroPortariaDTO,file);

    }
    public Page<RequestPortariaDTO> lista(Pageable pageable, Integer filial,String busca){
     return repository.listaPostaria(pageable,filial,busca);
    }
    public StatusAtualizadoDTO atualizaEntrada(Long ficalId, long registroId, MultipartFile file) {
      return repository.atualizaEntrada(ficalId,registroId,file);
    }
    public StatusAtualizadoDTO atualizaSaida(Long ficalId, long registroId, MultipartFile file) {
        return repository.atualizaSaida(ficalId,registroId,file);
    }

    public Map<String, List<RequestPortariaDTO>> solicitacaoUsuario(Long usuarioID) {
        return repository.solitacaoUsuario(usuarioID);
    }
    public Map<String, List<RequestPortariaDTO>> FindaAllStatus(String status) {
        return repository.FindAllStatus(status);
    }

    public Page<RequestPortariaDTO> listaPendentes(Pageable pageable, Integer filial,String busca) {
        return repository.listaPendentes(pageable,filial,busca);
    }

    public RequestPortariaDTO visualizarRegistro(Long registro) {
        return repository.visulizarRegistro(registro);
    }
    public void deleteRegistroPortaria(Long registroId,Long usuarioId){
       repository.deleteRegistroPortaria(registroId,usuarioId);
    }

    public void atualizaRegistro(AtualizaRegistro update) {
        repository.atualizaRegistro(update);
    }
}

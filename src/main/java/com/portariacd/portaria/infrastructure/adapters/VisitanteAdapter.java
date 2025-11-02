package com.portariacd.portaria.infrastructure.adapters;

import com.portariacd.portaria.application.services.LogAcaoService;
import com.portariacd.portaria.domain.gateways.VisitanteGatewaysRepository;
import com.portariacd.portaria.domain.models.vo.VisitanteDTO;
import com.portariacd.portaria.domain.models.vo.usuarioVO.UsuarioRequestDTO;
import com.portariacd.portaria.infrastructure.persistence.UsuarioRepository;
import com.portariacd.portaria.infrastructure.persistence.VisitanteEntity;
import com.portariacd.portaria.infrastructure.persistence.VisitanteRepository;
import com.portariacd.portaria.infrastructure.persistence.registroVisitante.RegistroVisitantePortariaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class VisitanteAdapter implements VisitanteGatewaysRepository {
    private final VisitanteRepository repository;
    private final UsuarioRepository usuarioRepository;
    private final LogAcaoService service;
    public VisitanteAdapter(VisitanteRepository visitanteRepository,
                            UsuarioRepository usuarioRepository,
                            LogAcaoService service
    ){
        this.repository = visitanteRepository;
        this.usuarioRepository = usuarioRepository;
        this.service = service;
    }
    @Override
    public Page<VisitanteDTO> listaVisitante(Pageable page, String busca) {
        Page<VisitanteDTO>  pageLista;
        if(busca!=null && !busca.isEmpty()){
            pageLista =  repository.findbyBusca(page,busca).map(VisitanteDTO::new);
        }else {
            pageLista = repository.findAllByAtivoTrue(page).map(VisitanteDTO::new);
        }
        return pageLista;
    }

    @Override
    public void deleteRegistro(Long visitanteid, Long usuarioId) {
         var visitante = repository.findById(visitanteid).orElseThrow(
                  ()->new RuntimeException("Não foi possivel deletar visitante")
          );
      var usuario =  usuarioRepository.findById(usuarioId).orElseThrow(
                  ()->new RuntimeException("Não foi possivel deletar visitante")
          );
        salvaLog(new UsuarioRequestDTO(usuario),visitante,"UPDATE_ENTRADA_HISTORY");
       visitante.setAtivo(false);
        repository.save(visitante);

    }

    private void salvaLog(UsuarioRequestDTO usuario, VisitanteEntity visitante, String acao){
        service.registrarLog(
                usuario,
                acao,
                String.format(
                        "Usuário %s deletou o visitante de ID %d de Nome %s",
                        usuario.nome(),
                        visitante.getId(),
                        visitante.getNomeCompleto()
                )
        );
    }
}

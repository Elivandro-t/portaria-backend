package com.portariacd.portaria.infrastructure.adapters;

import com.portariacd.portaria.domain.gateways.BlocoInterfaceGateway;
import com.portariacd.portaria.domain.models.vo.bloco.RegistroBlocoDTO;
import com.portariacd.portaria.domain.models.vo.bloco.RequestBlocoDTO;
import com.portariacd.portaria.infrastructure.persistence.BlocoRepository;
import com.portariacd.portaria.infrastructure.persistence.blocos.BlocoEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class BlocoAdpter  implements BlocoInterfaceGateway {
    private BlocoRepository repository;
    public BlocoAdpter(BlocoRepository repository){
        this.repository = repository;
    }
    @Override
    public void registroBloco(RegistroBlocoDTO registro){
            if (repository.findByNome(registro.nome()).isPresent()) {
                throw new RuntimeException("Nome '" + registro.nome() + "' já cadastrado");
            }
        BlocoEntity bloco = new BlocoEntity(registro.nome());
        repository.save(bloco);
    }
    @Override
    public List<RequestBlocoDTO> lista(){
       return repository.findAll().stream().map(RequestBlocoDTO::new).collect(Collectors.toList());
    }
}

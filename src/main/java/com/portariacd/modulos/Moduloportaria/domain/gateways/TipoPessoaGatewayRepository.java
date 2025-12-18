package com.portariacd.modulos.Moduloportaria.domain.gateways;

import com.portariacd.modulos.Moduloportaria.domain.models.vo.tipoPessoa.TipoPessoaDTO;
import com.portariacd.modulos.Moduloportaria.domain.models.vo.tipoPessoa.TipoPessoaReuestDTO;

import java.util.List;
import java.util.Map;

public interface TipoPessoaGatewayRepository {
     void registro(TipoPessoaDTO response);
     void  atualiza(long idTipoPessoa,TipoPessoaDTO response);
     void delete(long id);
    Map<String, List<TipoPessoaReuestDTO>> lista();
}

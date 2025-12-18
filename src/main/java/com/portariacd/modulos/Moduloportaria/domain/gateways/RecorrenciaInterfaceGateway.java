package com.portariacd.modulos.Moduloportaria.domain.gateways;

import com.portariacd.modulos.Moduloportaria.domain.models.vo.recorrencia.RegistroRecorrenciaDTO;
import com.portariacd.modulos.Moduloportaria.domain.models.vo.recorrencia.RequestRecorrenciaDTO;

import java.util.List;

public interface RecorrenciaInterfaceGateway {
     void registroRecorrencia(RegistroRecorrenciaDTO registro);
    public List<RequestRecorrenciaDTO> lista(Long usuarioId);

    }

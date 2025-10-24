package com.portariacd.portaria.infrastructure.validation;

import com.portariacd.portaria.domain.models.registro_visitante.RegistroVisitantePortaria;

public interface ValidaStatusPortaria {
    public void validEntrada(RegistroVisitantePortaria registroVisitantePortaria);
    public void validSaida(RegistroVisitantePortaria registroVisitantePortaria);

}

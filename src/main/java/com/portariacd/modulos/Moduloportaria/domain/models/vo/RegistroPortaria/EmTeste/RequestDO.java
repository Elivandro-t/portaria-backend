package com.portariacd.modulos.Moduloportaria.domain.models.vo.RegistroPortaria.EmTeste;

import com.portariacd.modulos.Moduloportaria.infrastructure.factory.CadastroTypeFactory;
import jakarta.validation.Valid;

public class RequestDO {
    @Valid
   public CadastroTypeFactory type;

    public CadastroTypeFactory getType() {
        return type;
    }

    public void setType(CadastroTypeFactory type) {
        this.type = type;
    }
}

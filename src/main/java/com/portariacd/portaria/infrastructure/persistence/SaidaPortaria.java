package com.portariacd.portaria.infrastructure.persistence;

import com.portariacd.portaria.infrastructure.persistence.registroVisitante.SaidaVisitanteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaidaPortaria extends JpaRepository<SaidaVisitanteEntity,Long> {
}

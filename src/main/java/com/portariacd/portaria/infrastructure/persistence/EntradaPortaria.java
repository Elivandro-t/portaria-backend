package com.portariacd.portaria.infrastructure.persistence;

import com.portariacd.portaria.infrastructure.persistence.registroVisitante.EntradaVisitanteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntradaPortaria extends JpaRepository<EntradaVisitanteEntity,Long> {
}

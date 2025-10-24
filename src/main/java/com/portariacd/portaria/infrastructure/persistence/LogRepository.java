package com.portariacd.portaria.infrastructure.persistence;

import com.portariacd.portaria.infrastructure.persistence.log.LogAcaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends JpaRepository<LogAcaoEntity,Long> {
}

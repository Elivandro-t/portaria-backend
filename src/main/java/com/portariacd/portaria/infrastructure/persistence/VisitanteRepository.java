package com.portariacd.portaria.infrastructure.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface VisitanteRepository extends JpaRepository<VisitanteEntity,Long> {

    @Query("select  p from VisitanteEntity p where p.nomeCompleto=:nomeCompleto or p.numeroTelefone=:numero")
    Optional<VisitanteEntity> findByOneByNumeroTelefone(String nomeCompleto,String numero);

    @Query("""
            select p from VisitanteEntity p where 
            LOWER(p.nomeCompleto) LIKE LOWER(CONCAT('%',:busca,'%'))
            OR LOWER(p.numeroTelefone) LIKE LOWER(CONCAT('%', :busca, '%'))
            OR LOWER(p.placaCarro) LIKE LOWER(CONCAT('%', :busca, '%'))
            """)
    Page<VisitanteEntity> findbyBusca(Pageable page,@Param("busca") String busca);
}

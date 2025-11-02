package com.portariacd.portaria.infrastructure.persistence;

import com.portariacd.portaria.domain.models.registro_visitante.StatusPortaria;
import com.portariacd.portaria.domain.models.vo.RegistroPortaria.AtualizaRegistro;
import com.portariacd.portaria.infrastructure.persistence.registroVisitante.RegistroVisitantePortariaEntity;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RegistroVisitanteRepository extends JpaRepository<RegistroVisitantePortariaEntity,Long> {

    @Query("SELECT p FROM  RegistroVisitantePortariaEntity p WHERE p.filialSolicitado=:filial ORDER BY p.dataCriacao DESC")
    Page<RegistroVisitantePortariaEntity> findAllByFilial(@Param("filial") Integer filial, Pageable pageable);

    Optional<RegistroVisitantePortariaEntity> findTopByOrderByProtocoloDesc();
    List<RegistroVisitantePortariaEntity> findAllByCriador_idOrderByDataCriacaoDesc(Long usuarioID);

    List<RegistroVisitantePortariaEntity> findAllByStatus(StatusPortaria status);
    @Query("SELECT p FROM RegistroVisitantePortariaEntity p where p.ativo = true \n" +
            "  AND p.filialSolicitado = :filial ORDER BY CASE WHEN p.status = 'AGUARDANDO_ENTRADA' THEN 1 ELSE 0 END DESC,\n" +
            "             p.dataCriacao DESC")
    Page<RegistroVisitantePortariaEntity> findAllByFilialStatus(Integer filial, Pageable pageable,@Param("AGUARDANDO_ENTRADA") StatusPortaria aguardandoEntrada);
    @Query("SELECT p FROM RegistroVisitantePortariaEntity p WHERE p.placaVeiculo =:s and p.ativo = true")
    RegistroVisitantePortariaEntity findAllByPlacaVeiculo(@NotBlank String s);

    @Query("""
            SELECT p FROM RegistroVisitantePortariaEntity p 
            WHERE p.filialSolicitado = :filial 
            AND (
            LOWER(p.nomeCompleto) LIKE LOWER(CONCAT('%', :busca, '%'))
            OR LOWER(p.placaVeiculo) LIKE LOWER(CONCAT('%', :busca, '%'))
            OR LOWER(p.protocolo) LIKE LOWER(CONCAT('%', :busca, '%'))  
            )
            AND p.ativo = true ORDER BY p.dataCriacao DESC
            """)
    Page<RegistroVisitantePortariaEntity> findAllByFilialAndBusca(Integer filial, Pageable pageable,@Param("busca") String busca);
    @Query("SELECT p FROM  RegistroVisitantePortariaEntity p WHERE (p.placaVeiculo = :placa OR p.visitante.numeroTelefone = :numero ) and p.ativo = true")
    Optional<RegistroVisitantePortariaEntity> findAllByPlacaVeiculoAndNomeCompleto(@NotBlank String placa,@NotBlank String numero);

//    @Modifying
//    @Query("""
//            UPDATE RegistroVisitantePortariaEntity p
//            SET p.nomeCompleto = :#{#update.nomeCompleto},
//            p.placaVeiculo=:#{#update.placaVeiculo},
//            p.visitante.numeroTelefone = :#{#update.numeroTelefone}
//            WHERE p.id = :#{#update.id}
//
//            """)
//    void atualizarEntrada(AtualizaRegistro update);
}
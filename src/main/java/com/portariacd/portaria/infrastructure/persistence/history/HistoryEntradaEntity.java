package com.portariacd.portaria.infrastructure.persistence.history;

import com.portariacd.portaria.domain.models.history.HistoryEntrada;
import com.portariacd.portaria.domain.models.registro_visitante.StatusPortaria;
import com.portariacd.portaria.infrastructure.persistence.UsuarioEntity;
import com.portariacd.portaria.infrastructure.persistence.VisitanteEntity;
import com.portariacd.portaria.infrastructure.persistence.registroVisitante.RegistroVisitantePortariaEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Entity
@Table(name="history")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HistoryEntradaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long registroPortariaId;
    private String nomeCompleto;
    @ManyToOne
    @JoinColumn(name = "visitante_id", foreignKey = @ForeignKey(name = "fk_history_visitante"))
    private VisitanteEntity visitante;
    private String placaVeiculo;
    private LocalDateTime dataCriacao;
    @Lob
    @Column(name = "descricao",columnDefinition = "TEXT")
    @Basic(fetch = FetchType.LAZY)
    private String descricao;
    @Enumerated(EnumType.STRING)
    private StatusPortaria status;
    @ManyToOne
    @JoinColumn(name = "criador_id", foreignKey = @ForeignKey(name = "fk_history_criador"))
    private UsuarioEntity criador;
    private Integer filialSolicitado;
    private Boolean ativo;
    private String protocolo;
    private String bloco;
    private LocalDateTime dataEntrada;
    private LocalDateTime dataSaida;
    private Long ficalIdEntrada;
    private Long ficalIdSaida;
    private String nomeFiscaEntrada;
    private String nomeFiscaSaida;
    private String imagemEntrada;
    private String imagemSaida;

    public HistoryEntradaEntity(HistoryEntrada historyEntrada) {
        this.placaVeiculo = historyEntrada.getPlacaVeiculo();
        this.nomeCompleto = historyEntrada.getNomeCompleto();
        this.visitante = historyEntrada.getVisitante();
        this.dataCriacao = historyEntrada.getDataCriacao();
        this.descricao = historyEntrada.getDescricao();
        this.status = historyEntrada.getStatus();
        this.criador = historyEntrada.getCriador();
        this.filialSolicitado = historyEntrada.getFilialSolicitado();
        this.ativo = historyEntrada.getAtivo();
        this.protocolo = historyEntrada.getProtocolo();
        this.bloco = historyEntrada.getBloco();
        this.dataEntrada = historyEntrada.getDataEntrada();
        this.ficalIdSaida = historyEntrada.getFicalIdSaida();
        this.ficalIdEntrada = historyEntrada.getFicalIdEntrada();
        this.nomeFiscaEntrada = historyEntrada.getNomeFiscaEntrada();
        this.imagemEntrada = historyEntrada.getImagemEntrada();
        this.registroPortariaId = historyEntrada.getRegistroPortariaId();
    }

    public void UpdateHistoryInput(RegistroVisitantePortariaEntity r) {
        this.ficalIdEntrada = r.getEntradaVisitante().getFiscalEntradaId();
        this.nomeFiscaEntrada= r.getEntradaVisitante().getNomeFiscal();
        this.imagemEntrada = r.getEntradaVisitante().getImagem();
        this.dataEntrada = r.getEntradaVisitante().getDataEntrada();
        this.status = r.getStatus();
    }

    public void UpdateHistoryOuput(RegistroVisitantePortariaEntity r) {
        this.ficalIdSaida = r.getSaidaVisitante().getFicalSaidaId();
        this.nomeFiscaSaida = r.getSaidaVisitante().getNomeFiscal();
        this.imagemSaida = r.getSaidaVisitante().getImagem();
        this.dataSaida = r.getSaidaVisitante().getDataSaida();
        this.status = r.getStatus();
    }
}

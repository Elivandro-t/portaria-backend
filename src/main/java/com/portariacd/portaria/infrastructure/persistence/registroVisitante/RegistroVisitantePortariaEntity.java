package com.portariacd.portaria.infrastructure.persistence.registroVisitante;

import com.portariacd.portaria.domain.models.registro_visitante.StatusPortaria;
import com.portariacd.portaria.domain.models.vo.RegistroPortaria.AtualizaRegistro;
import com.portariacd.portaria.domain.models.vo.RegistroPortaria.RegistroPortariaDTO;
import com.portariacd.portaria.infrastructure.persistence.UsuarioEntity;
import com.portariacd.portaria.infrastructure.persistence.VisitanteEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "registro_portaria")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegistroVisitantePortariaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nomeCompleto;
    @ManyToOne
    private VisitanteEntity visitante;
    private String placaVeiculo;
    private String ocupacaoLiberada;
    private LocalDateTime dataCriacao;
    @Lob
    @Column(name = "descricao",columnDefinition = "TEXT")
    @Basic(fetch = FetchType.LAZY)
    private String descricao;
    @OneToOne(cascade = CascadeType.ALL)
    private SaidaVisitanteEntity saidaVisitante;
    @OneToOne(cascade = CascadeType.ALL)
    private EntradaVisitanteEntity entradaVisitante;
    @Enumerated(EnumType.STRING)
    private StatusPortaria status;
    @ManyToOne
    private UsuarioEntity criador;
    private Integer filialSolicitado;
    private Boolean ativo;
    private String protocolo;
    private String bloco;

    public RegistroVisitantePortariaEntity(RegistroPortariaDTO req, VisitanteEntity visitante, UsuarioEntity usuario) {
      this.nomeCompleto = req.nomeCompleto();
      this.visitante = visitante;
      this.placaVeiculo = req.placaVeiculo();
      this.dataCriacao = LocalDateTime.now();
      this.bloco = req.bloco();
      this.descricao = req.descricao();
      this.ocupacaoLiberada = req.ocupacaoLiberada();
      this.criador = usuario;
      this.filialSolicitado = usuario.getFilial();
      this.ativo = true;
    }

    public void atualizarEntrada(AtualizaRegistro update) {
        this.placaVeiculo = update.placaVeiculo();
        this.nomeCompleto = update.nomeCompleto();
        this.bloco = update.bloco();
    }
//    @PrePersist
//    public void gerarId() {
//
//        if (id == null) {
//            long timestamp = System.currentTimeMillis();
//            int random = new Random().nextInt(99999);
//            this.id = timestamp + "-" + random;
//        }
//    }



}


package com.portariacd.portaria.domain.models.registro_visitante;

import com.portariacd.portaria.domain.models.Visitante;
import com.portariacd.portaria.infrastructure.persistence.UsuarioEntity;
import com.portariacd.portaria.infrastructure.persistence.registroVisitante.RegistroVisitantePortariaEntity;

import java.time.LocalDateTime;

public class RegistroVisitantePortaria {
    private long id;
    private String nomeCompleto;
    private Visitante visitante;
    private String placaVeiculo;
    private  StatusPortaria status;
    private String numeroTelefone;
    private String descricao;
    private Integer filial;
    private SaidaVisitante saidaVisitante;
    private EntradaVisitante entradaVisitante;
    private UsuarioEntity criadorId;
    private String ocupacaoLiberada;
    private LocalDateTime dataCriacao;
    private Boolean ativo;
    private String protocolo;
    private String bloco;



    public RegistroVisitantePortaria(RegistroVisitantePortariaEntity items) {this.id = items.getId();
       this.nomeCompleto = items.getNomeCompleto();
       if(items.getVisitante()!=null){
           this.visitante = new Visitante(items.getVisitante());
       }
       this.descricao = items.getDescricao();
       this.placaVeiculo = items.getPlacaVeiculo();
       this.criadorId = items.getCriador();
       this.dataCriacao = items.getDataCriacao();
       this.status = items.getStatus();
       this.bloco = items.getBloco();
       if(items.getProtocolo()!=null){
           this.protocolo = items.getProtocolo();

       }
       if(items.getEntradaVisitante()!=null){
           this.entradaVisitante = new EntradaVisitante(items.getEntradaVisitante());
       }

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Visitante getVisitante() {
        return visitante;
    }

    public void setVisitante(Visitante visitante) {
        this.visitante = visitante;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getPlacaVeiculo() {
        return placaVeiculo;
    }

    public void setPlacaVeiculo(String placaVeiculo) {
        this.placaVeiculo = placaVeiculo;
    }


    public StatusPortaria getStatus() {
        return status;
    }

    public void setStatus(StatusPortaria status) {
        this.status = status;
    }

    public String getNumeroTelefone() {
        return numeroTelefone;
    }

    public void setNumeroTelefone(String numeroTelefone) {
        this.numeroTelefone = numeroTelefone;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getFilial() {
        return filial;
    }

    public void setFilial(Integer filial) {
        this.filial = filial;
    }

    public SaidaVisitante getSaidaVisitante() {
        return saidaVisitante;
    }

    public void setSaidaVisitante(SaidaVisitante saidaVisitante) {
        this.saidaVisitante = saidaVisitante;
    }

    public EntradaVisitante getEntradaVisitante() {
        return entradaVisitante;
    }

    public void setEntradaVisitante(EntradaVisitante entradaVisitante) {
        this.entradaVisitante = entradaVisitante;
    }

    public String getOcupacaoLiberad() {
        return ocupacaoLiberada;
    }

    public void setOcupacaoLiberada(String ocupacao) {
        this.ocupacaoLiberada = ocupacao;
    }

    public UsuarioEntity getCriadorId() {
        return criadorId;
    }

    public void setCriadorId(UsuarioEntity criadorId) {
        this.criadorId = criadorId;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public String getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }

    public String getBloco() {
        return bloco;
    }

    public void setBloco(String bloco) {
        this.bloco = bloco;
    }
}


package com.portariacd.portaria.domain.models;

import com.portariacd.portaria.infrastructure.persistence.VisitanteEntity;
import com.portariacd.portaria.infrastructure.persistence.registroVisitante.StatusTipoDeAcesso;

import java.time.LocalDateTime;
public class Visitante {
    private Long id;
    private String nomeCompleto;
    private String imagem;
    private String ocupacao;
    //opcional
    private Integer filial;
    private LocalDateTime dataCriacao;
    private StatusTipoDeAcesso tipoAcesso;
    private String tipoMotorista;
    private  String tipoPessoa;


    public Visitante(VisitanteEntity visitante) {
        this.id = visitante.getId();
        this.nomeCompleto = visitante.getNomeCompleto();
        this.dataCriacao = visitante.getDataCriacao();
        this.imagem = visitante.getImagem();
        this.ocupacao = visitante.getOcupacao();
        this.tipoAcesso = visitante.getTipoAcesso();
        this.tipoMotorista = visitante.getTipoMotorista();
        this.tipoPessoa = visitante.getTipoPessoa();

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(String tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    public String getTipoMotorista() {
        return tipoMotorista;
    }

    public void setTipoMotorista(String tipoMotorista) {
        this.tipoMotorista = tipoMotorista;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Integer getFilial() {
        return filial;
    }

    public void setFilial(Integer filial) {
        this.filial = filial;
    }

    public String getOcupacao() {
        return ocupacao;
    }

    public void setOcupacao(String ocupacao) {
        this.ocupacao = ocupacao;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public StatusTipoDeAcesso getTipoAcesso() {
        return tipoAcesso;
    }

    public void setTipoAcesso(StatusTipoDeAcesso tipoAcesso) {
        this.tipoAcesso = tipoAcesso;
    }
}

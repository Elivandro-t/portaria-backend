package com.portariacd.portaria.infrastructure.persistence;

import com.portariacd.portaria.domain.models.vo.RegistroPortaria.RegistroPortariaDTO;
import com.portariacd.portaria.infrastructure.persistence.registroVisitante.StatusTipoDeAcesso;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Entity
@Table(name = "visitante")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VisitanteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nomeCompleto;
    private String numeroTelefone;
    private String placaCarro;
    private String imagem;
    private String ocupacao;
    //opcional
    private Integer filial;
    private LocalDateTime dataCriacao;
    @Enumerated(EnumType.STRING)
    private StatusTipoDeAcesso tipoAcesso;
    private  String tipoPessoa;
    private Boolean ativo;

    public VisitanteEntity(RegistroPortariaDTO request, String image) {
        this.nomeCompleto = request.nomeCompleto();
        if(request.filial()!=null){
            this.filial = request.filial();
        }
        this.ocupacao = request.tipPessoa();
        this.numeroTelefone = request.numeroTelefone();
        this.imagem = image;
        this.dataCriacao = LocalDateTime.now();
        this.ativo = true;

        this.placaCarro = request.placaVeiculo();
        this.tipoAcesso = StatusTipoDeAcesso.StatusAdd(request.tipoAcesso());
        this.tipoPessoa = request.tipPessoa();
    }

}

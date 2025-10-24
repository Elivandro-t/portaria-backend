package com.portariacd.portaria.infrastructure.persistence;

import com.portariacd.portaria.domain.models.vo.RegistroPortaria.RegistroPortariaDTO;
import com.portariacd.portaria.infrastructure.persistence.registroVisitante.StatusTipoDeAcesso;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Entity
@Table(name = "visitante")
@AllArgsConstructor
@NoArgsConstructor
@Getter
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
    private String tipoMotorista;
    private  String tipoPessoa;

    public VisitanteEntity(RegistroPortariaDTO request, String image) {
        this.nomeCompleto = request.nomeCompleto();
        if(request.filial()!=null){
            this.filial = request.filial();
        }
        this.ocupacao = request.ocupacao();
        this.numeroTelefone = request.numeroTelefone();
        this.imagem = image;
        this.dataCriacao = LocalDateTime.now();
        this.placaCarro = request.placaVeiculo();
        this.tipoAcesso = StatusTipoDeAcesso.StatusAdd(request.tipoAcesso());
        this.tipoMotorista = request.tipoMotorista();
        this.tipoPessoa = request.tipPessoa();
    }

}

package com.portariacd.modulos.Moduloportaria.services;

import com.portariacd.modulos.Moduloportaria.infrastructure.persistence.RegistroVisitanteRepository;
import com.portariacd.modulos.Moduloportaria.infrastructure.persistence.registroVisitante.RegistroVisitantePortariaEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TarefasServices {
   private final RegistroVisitanteRepository repository;
    TarefasServices(RegistroVisitanteRepository repository){
        this.repository = repository;
    }
    @Transactional
    @Scheduled(fixedRate = 43200000) // 12 horas em ms
    protected void Rodaservice(){
        System.out.println("Execultando tarefa");

        var registroEncontrados = repository.findAllByAtivoTrue();
        LocalDateTime horaAtual = LocalDateTime.now();
        List<RegistroVisitantePortariaEntity> listaDeRegistro = new ArrayList<>();

        for(var registro:registroEncontrados){

            var visitante = registro.getVisitante();
            if(visitante==null){
                continue;
            }
            var recorrencia = visitante.getRecorrencia();
            if(recorrencia==null){
                continue;
            }
                if(recorrencia.getNome().trim().equals("RECORRENTE TEMPORARIO")){
                    if(registro.getVisitante().getDataRestritoAcesso()!=null){
                    LocalDate expiredDate = registro.getVisitante().getDataRestritoAcesso();
                    LocalDate hoje = LocalDate.now();
                    boolean expirouHoje = expiredDate.isEqual(hoje);
                    boolean expirouAntes = expiredDate.isBefore(hoje);
                    if(expirouHoje ||expirouAntes){
                        registro.getVisitante().setAtivo(false);
                        registro.getVisitante().setBloqueioAcesso(true);
                        registro.getVisitante().setDataRestritoAcesso(null);
                        registro.setAtivo(false);
                        listaDeRegistro.add(registro);
                }
                    }
            }
            if (registro.getEntradaVisitante() == null) {
                // Não tem saída lançada, pula
                continue;
            }
            if(registro.getEntradaVisitante().getDataEntrada()!=null) {
                LocalDateTime entrada = registro.getEntradaVisitante().getDataEntrada();
                    var horas = Duration.between(entrada, horaAtual).toHours();
                    if (horas >= 10 && horas < 12) {
                        registro.setPrioridadeAviso("ATENÇÃO: MOTORISTA COM SAÍDA PENDENTE");
                        listaDeRegistro.add(registro);
                        registro.setPrioridadeAtrasoAtivo(true);

                    }
                    // ⛔ Passou de 12 horas → PRIORIDADE ATRASO + inativa
                    else if (horas >= 12) {
                        registro.setPrioridadeAtraso("ATENÇÃO: MOTORISTA COM ATRASO CRITICO DE SAIDA");
                        registro.setPrioridadeAtrasoAtivo(true);
                        listaDeRegistro.add(registro);
                    }
//                    else if (horas >= 24) {
//                        registro.setPrioridadeAtraso("FECHADO POR FALTA DE LIBERAÇÃO");
//                        registro.setAtivo(false);
//                        registro.setPrioridadeAtrasoAtivo(true);
//                        listaDeRegistro.add(registro);
//                    }

            }
        }
        repository.saveAll(listaDeRegistro);
    }
}

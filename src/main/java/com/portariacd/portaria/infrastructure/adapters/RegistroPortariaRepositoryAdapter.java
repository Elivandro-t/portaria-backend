package com.portariacd.portaria.infrastructure.adapters;

import com.portariacd.portaria.domain.gateways.RegistroPortariaGatewayRepository;
import com.portariacd.portaria.infrastructure.persistence.*;
import com.portariacd.portaria.infrastructure.validation.ValidaNomeImagem;
import com.portariacd.portaria.infrastructure.validation.ValidaStatusPortaria;
import com.portariacd.portaria.domain.models.history.HistoryEntrada;
import com.portariacd.portaria.domain.models.registro_visitante.RegistroVisitantePortaria;
import com.portariacd.portaria.domain.models.registro_visitante.StatusPortaria;
import com.portariacd.portaria.domain.models.vo.RegistroPortaria.RequestPortariaDTO;
import com.portariacd.portaria.domain.models.vo.RegistroPortaria.RegistroPortariaDTO;
import com.portariacd.portaria.domain.models.vo.RegistroPortaria.StatusAtualizadoDTO;
import com.portariacd.portaria.infrastructure.persistence.history.HistoryEntradaEntity;
import com.portariacd.portaria.infrastructure.persistence.registroVisitante.EntradaVisitanteEntity;
import com.portariacd.portaria.infrastructure.persistence.registroVisitante.RegistroVisitantePortariaEntity;
import com.portariacd.portaria.infrastructure.persistence.registroVisitante.SaidaVisitanteEntity;
import com.portariacd.portaria.infrastructure.persistence.registroVisitante.StatusTipoDeAcesso;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class RegistroPortariaRepositoryAdapter implements RegistroPortariaGatewayRepository {
    @Value("${avatar}")
    private String endpoint;
    private String endpointUrl = "http://localhost:8080/portaria/v1";
    private final RegistroVisitanteRepository repository;
    private final VisitanteRepository visitante;
    private final HistoryRepository history;
    private final UsuarioRepository usuarioRepository;
    private final ValidaStatusPortaria validaStatusPortaria;
    public RegistroPortariaRepositoryAdapter(
            RegistroVisitanteRepository repository
            ,VisitanteRepository visitante
            ,UsuarioRepository usuarioRepository
            ,ValidaStatusPortaria validaStatusPortaria,
            HistoryRepository history
    ){
        this.repository = repository;
        this.visitante = visitante;
        this.usuarioRepository = usuarioRepository;
        this.validaStatusPortaria = validaStatusPortaria;
        this.history = history;
    }
    @Override
    public String registroPortaria(RegistroPortariaDTO request, MultipartFile file){
      var visitanteEcontrado = visitante.findByOneByNumeroTelefone(request.nomeCompleto(),request.numeroTelefone());
      if(visitanteEcontrado.isEmpty()){
              String nameImagem = ValidaNomeImagem.criarDiretorio(file,"avatar",endpointUrl);;
              var visitanteEntity = new VisitanteEntity(request,nameImagem);
             VisitanteEntity novoVisitante =  visitante.save(visitanteEntity);
            CadastroVisitante(request,novoVisitante);
              return "Entrada solicitada";
      }

        visitanteEcontrado.ifPresent(v -> CadastroVisitante(request, v));
      return "Solictacao registrada com sucesso: aguardando entrada";

    }
    private String geraProtocolo(){
        Optional<RegistroVisitantePortariaEntity> ultimoProtocolo = repository.findTopByOrderByProtocoloDesc();
        long proximoProtocolo = ultimoProtocolo.map(e->e.getId()+1).orElse(1l);
        Integer quantidadeDigitos = String.valueOf(proximoProtocolo).length();
        int tamanho = Math.max(6, quantidadeDigitos);
        String numeroPedido = String.format("%0" + tamanho + "d", proximoProtocolo);
        return  numeroPedido;
    }

    private void CadastroVisitante(RegistroPortariaDTO req, VisitanteEntity visitante){
        System.out.println("bloco"+ req.bloco());
        var registroEncontrado = repository.findAllByPlacaVeiculoAndNomeCompleto(req.nomeCompleto(),req.placaVeiculo());
        if(registroEncontrado.isPresent()){
            throw  new RuntimeException("Visitante já possou um registr de entrada ativo");
        }
        var usuario = usuarioRepository.findById(req.criadorId()).orElseThrow(
                ()->new RuntimeException("Erro ao buscar usuario!")
        );
        var validaRegistro = repository.findAllByPlacaVeiculo(req.placaVeiculo());
        if(validaRegistro!=null){
            if( validaRegistro.getPlacaVeiculo().equals(req.placaVeiculo())){
                throw new RuntimeException("Usuario ja Possuai uma entrada em andamento");
            }
        }
        RegistroVisitantePortariaEntity registro = new RegistroVisitantePortariaEntity(
                req,visitante,usuario
        );
        registro.setStatus(StatusPortaria.AGUARDANDO_ENTRADA);
        registro.setProtocolo(geraProtocolo());
       var resposta = repository.save(registro);
        var historyNovo = new HistoryEntrada(resposta);
        var historyEli = new HistoryEntradaEntity(historyNovo);
        history.save(historyEli);


    }
    // valida nome da imagem caso exista retorna uma nova imagem

    @Override
    @Transactional
    public Page<RequestPortariaDTO> listaPostaria(Pageable pageable, Integer filial,String busca) {
        Page<RequestPortariaDTO> page = repository.findAllByFilial(filial,pageable).map(entity->new RequestPortariaDTO(new RegistroVisitantePortaria(entity)));

        return page;

    }
//atualiza status para aguadando saida
    @Override
    public StatusAtualizadoDTO atualizaEntrada(Long ficalId, long registroId, MultipartFile file) {
          UsuarioEntity usuarioFiscal =  usuarioRepository.findById(ficalId).orElseThrow(
                    ()->new RuntimeException("Usuario não existe ou estagio invalido")
            );
           RegistroVisitantePortariaEntity resposta =  repository.findById(registroId).orElseThrow(
                     ()-> new RuntimeException("Erro ao buscar registro, registro não encontrado!")
           );
            RegistroVisitantePortaria re = new RegistroVisitantePortaria(resposta);
            validaStatusPortaria.validEntrada(re);
            String pasta = "entrada";
            String nomeUrl = ValidaNomeImagem.criarDiretorio(file,pasta,endpointUrl);
            // cria a entrada de motorista
            EntradaVisitanteEntity entradaVisitante = new EntradaVisitanteEntity();
            entradaVisitante.setDataEntrada(LocalDateTime.now());
            entradaVisitante.setFiscalEntradaId(usuarioFiscal.getId());
            entradaVisitante.setNomeFiscal(usuarioFiscal.getNome());
            entradaVisitante.setImagem(nomeUrl);
            resposta.setEntradaVisitante(entradaVisitante);
            // atualiza o status da entrada do motorista
            resposta.setStatus(StatusPortaria.AGUARDANDO_SAIDA);
         RegistroVisitantePortariaEntity respostaSalva =  repository.save(resposta);
        var historyEntrada = history.findByRegistroPortariaId(registroId);
        if(historyEntrada!=null){
            historyEntrada.UpdateHistoryInput(respostaSalva);
             history.save(historyEntrada);
         }
          return new StatusAtualizadoDTO(respostaSalva,"Status alterado");


    }

    @Override
    public StatusAtualizadoDTO atualizaSaida(Long ficalId, long registroId,MultipartFile file) {
            UsuarioEntity usuarioFiscal =  usuarioRepository.findById(ficalId).orElseThrow(
                    ()->new RuntimeException("Usuario não existe ou estagio invalido")
            );
            RegistroVisitantePortariaEntity resposta =  repository.findById(registroId).orElseThrow(
                    ()-> new RuntimeException("Erro ao buscar registro, registro não encontrado!")
            );
            RegistroVisitantePortaria re = new RegistroVisitantePortaria(resposta);

            validaStatusPortaria.validSaida(re);
            String pasta = "saida";
            String nomeUrl = ValidaNomeImagem.criarDiretorio(file,pasta,endpointUrl);
        var historyEntrada = history.findByRegistroPortariaId(registroId);

        // valida o statas
            // cria a entrada de motorista
            SaidaVisitanteEntity saidaVisitante = new SaidaVisitanteEntity();
            saidaVisitante.setDataSaida(LocalDateTime.now());
            saidaVisitante.setFicalSaidaId(usuarioFiscal.getId());
            saidaVisitante.setNomeFiscal(usuarioFiscal.getNome());
            resposta.setSaidaVisitante(saidaVisitante);
            VisitanteEntity visitanteEntity = visitante.findById(resposta.getVisitante().getId()).orElseThrow(
                    ()->new RuntimeException("Visitante nao encontado")
            );
        if(visitanteEntity.getTipoAcesso().equals(StatusTipoDeAcesso.RECORRENTE)){
            resposta.setStatus(StatusPortaria.AGUARDANDO_ENTRADA);
            if (historyEntrada != null) {
                if (resposta.getSaidaVisitante() != null) {
                    // Atualiza o histórico com os
                // dados atuais da saída
                historyEntrada.setImagemEntrada(nomeUrl); // ou saída, dependendo
                historyEntrada.UpdateHistoryOuput(resposta);
                history.save(historyEntrada);
                }
                re.setProtocolo(geraProtocolo());
                if (resposta.getEntradaVisitante() != null) {

                    resposta.getEntradaVisitante().setImagem(null);
                }
                    resposta.getSaidaVisitante().setImagem(null);
                }
                re.setAtivo(true);

            }else {
                resposta.setStatus(StatusPortaria.SAIDA_LIBERADA);
               saidaVisitante.setImagem(nomeUrl);

               resposta.setAtivo(false);
                if(historyEntrada!=null){
                    historyEntrada.UpdateHistoryOuput(resposta);
                    history.save(historyEntrada);
                }
            }
            // atualiza o status da entrada do motorista
            RegistroVisitantePortariaEntity respostaSalva =  repository.save(resposta);

            return new StatusAtualizadoDTO(respostaSalva,"Status alterado");

    }

    @Override
    @Transactional
    public Map<String, List<RequestPortariaDTO>> solitacaoUsuario(Long usuarioID) {
        Map<String,List<RequestPortariaDTO>> listMap = new HashMap<>();
        List<RequestPortariaDTO> solicitacao = repository.findAllByCriador_idOrderByDataCriacaoDesc(usuarioID).stream().map(
                RequestPortariaDTO::new
        ).toList();
        listMap.put("registros",solicitacao);
        return listMap;
    }

    @Override
    @Transactional
    public Map<String, List<RequestPortariaDTO>> FindAllStatus(String Status) {
        Map<String,List<RequestPortariaDTO>> listMap = new HashMap<>();
        List<RequestPortariaDTO> solicitacao = repository.findAllByStatus(StatusPortaria.buscaStatus(Status)).stream().map(
                RequestPortariaDTO::new
        ).toList();
        listMap.put("registros",solicitacao);
        return listMap;
    }

    @Override
    @Transactional
    public Page<RequestPortariaDTO> listaPendentes(Pageable pageable, Integer filial,String busca) {
        Page<RequestPortariaDTO> page;
        System.out.println("busca vindo "+busca);
        if (busca != null && !busca.isEmpty()) {
            page = repository.findAllByFilialAndBusca(filial, pageable, busca).map(entity -> new RequestPortariaDTO(new RegistroVisitantePortaria(entity)));
            System.out.println("buscou aqui no filtro");
        } else{
            System.out.println("buscou aqui no default");
            page = repository.findAllByFilialStatus(filial, pageable, StatusPortaria.AGUARDANDO_ENTRADA).map(entity -> new RequestPortariaDTO(new RegistroVisitantePortaria(entity)));
        }
       return  page;
    }

    @Override
    public RequestPortariaDTO visulizarRegistro(Long registro) {
        RequestPortariaDTO response = repository.findById(registro).map(RequestPortariaDTO::new).orElseThrow(
                ()->new RuntimeException("Registro não encontrado!")
        );
        return response;
    }


}

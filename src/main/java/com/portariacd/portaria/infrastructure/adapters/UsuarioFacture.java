package com.portariacd.portaria.infrastructure.adapters;

import com.portariacd.portaria.domain.gateways.UsuarioGatewayRepository;
import com.portariacd.portaria.domain.models.vo.RegistroPortaria.RequestPortariaDTO;
import com.portariacd.portaria.domain.models.vo.usuarioVO.*;
import com.portariacd.portaria.infrastructure.config.TokenConfigure;
import com.portariacd.portaria.domain.models.auth.Usuario;
import com.portariacd.portaria.domain.models.vo.CadastroUsuarioDto;
import com.portariacd.portaria.infrastructure.persistence.PerfilEntity;
import com.portariacd.portaria.infrastructure.persistence.PerfilRepository;
import com.portariacd.portaria.infrastructure.persistence.UsuarioEntity;
import com.portariacd.portaria.infrastructure.persistence.UsuarioRepository;
import com.portariacd.portaria.infrastructure.validation.ValidaNomeImagem;
import com.portariacd.portaria.infrastructure.validation.Validator;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.*;

@Component
public class UsuarioFacture implements UsuarioGatewayRepository {
    private final UsuarioRepository repository;
    private final PerfilRepository perfilRepository;
    private AuthenticationManager authenticationManager;
    private final TokenConfigure tokenConfigure;
    private final Validator validator;
    @Value("${endpoint}")
    private String endpointUrl;

    public  UsuarioFacture(UsuarioRepository repository,
                           PerfilRepository perfilRepository
                           ,AuthenticationManager authenticationManager,
                           TokenConfigure tokenConfigure,
                           Validator validator
    ){
        this.repository = repository;
        this.tokenConfigure = tokenConfigure;
        this.perfilRepository = perfilRepository;
        this.authenticationManager = authenticationManager;
        this.validator = validator;
    }

    public void registroUsuario(CadastroUsuarioDto dto){
        validator.valid(dto.password());
       if(repository.findOneByEmail(dto.email()).isPresent()) {
           throw new RuntimeException("Usuario ja existe!");
       };
       PerfilEntity perfil =  perfilRepository.findById(dto.perfilId()).orElseThrow(
                ()-> new RuntimeException("Erro ao buscar Perfil"));
        Usuario usuario = new Usuario(dto, LocalDateTime.now(),true);
       var usuarioEntity = new UsuarioEntity(usuario,perfil);
        if (perfil.getUsuario() != null) {
            perfil.getUsuario().add(usuarioEntity);
        } else {
            perfil.setUsuario(new ArrayList<>());
            perfil.getUsuario().add(usuarioEntity);
        }
         repository.save(usuarioEntity);
    }
    public Page<UsuarioRequestDTO> listaUsuario(Pageable page,String busca){
        Page<UsuarioRequestDTO> pageLista;
       if(busca !=null && !busca.isEmpty()){
          pageLista = repository.findAllByUsuario(page,busca)
                   .map(UsuarioRequestDTO::new);
       }else {
           pageLista = repository.findAllByUsuarioRegistrado(page).map(UsuarioRequestDTO::new);
       }
        return pageLista;
    }
    @Override
    public TokenResponse authLogin(UsuarioLoginDTO dto) {
        var token = new UsernamePasswordAuthenticationToken(dto.email(), dto.password());
        var entity = authenticationManager.authenticate(token);

        var usuario = entity.getPrincipal();

        var usuarioModel = new Usuario((UsuarioEntity) usuario);
//        List<String> permissoes = usuarioModel.getRole().stream()
//                .flatMap(r -> r.getPermitions().stream())
//                .map(Permition::getName)
//                .toList();

        String tokenGerado = tokenConfigure.geraToken(usuarioModel);
        return new TokenResponse(tokenGerado,new usuarioRequestDTO(((UsuarioEntity) usuario).getId()));
    }

    @Override
    public Map<String, UsuarioRequestPerfilDTO> buscaUsuarioPerfil(String email) {
        Map<String, UsuarioRequestPerfilDTO> map = new HashMap<>();
    var response =  repository.findOneByEmail(email).orElseThrow(
                ()-> new RuntimeException("Usuario não existe!")
        );
     map.put("usuario",new UsuarioRequestPerfilDTO(response));

        return map;
    }

    @Override
    public void adicionarPerfil(long usuarioId, long perfilId) {
        var response =  repository.findById(usuarioId).orElseThrow(
                ()-> new RuntimeException("Usuario não existe!")
        );
       var perfil = perfilRepository.findById(perfilId).orElseThrow(
                ()-> new RuntimeException("Perfil não existe!")
        );
       response.setPerfil(perfil);
       repository.save(response);

    }
    @Override
    @Transactional
    public String AlteraSenha(String email) {
        var response =  repository.findOneByEmail(email).orElseThrow(
                ()-> new RuntimeException("Usuario não existe!")
        );
        String novaPasswd = geraSenha();
       response.atualizaSenha(novaPasswd);
        repository.save(response);
     return novaPasswd;
    }

    @Override
    public UsuarioBuscaRequestDTO buscarUsuarioId(Long id) {
        UsuarioEntity usuario =repository.findById(id).orElseThrow(
                ()->new RuntimeException("não foi possivel encontrar usuario!")
        );
        return new UsuarioBuscaRequestDTO(usuario);
    }

    @Override
    @Transactional
    public void salvaImagem(Long usuarioId, MultipartFile file) {
       var usuario = repository.findById(usuarioId).orElseThrow(
               ()->new RuntimeException("Não foi possivel localizar usuario!")
       );
     String nomeImagem = ValidaNomeImagem.criarDiretorio(file,"usuario",endpointUrl);
     repository.salvaImagem(nomeImagem,usuario.getId());

    }

    private String geraSenha(){
       String numeros = "0123456789";

       String maiuscula = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String minucula = "abcdefghijklmnopqrstuvwxyz";
        String caracter = "!@#$%^&*=?";
        String todos = maiuscula+minucula+caracter+numeros;

        StringBuilder senha = new StringBuilder();
        var random = new Random();
        for(int i=0;i<8;i++){
            int index = random.nextInt(todos.length());
            senha.append(todos.charAt(index));
        }
        return senha.toString();

   }

}

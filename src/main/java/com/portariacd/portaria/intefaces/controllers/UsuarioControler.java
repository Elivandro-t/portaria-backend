package com.portariacd.portaria.intefaces.controllers;

import com.portariacd.portaria.application.services.UsuarioService;
import com.portariacd.portaria.domain.models.vo.CadastroUsuarioDto;
import com.portariacd.portaria.domain.models.vo.usuarioVO.*;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("portaria/v1/usuario")
public class UsuarioControler {
    private final UsuarioService service;
    public UsuarioControler(UsuarioService usuarioService){
        this.service = usuarioService;
    }
    @PreAuthorize("@permissaoService.hasPermission(authentication, 'GERENCIAR_USUARIOS')")
    @PostMapping("/cadastro")
    @Transactional
    public ResponseEntity<?> cadastroUsuario(@RequestBody @Valid CadastroUsuarioDto dto){
        System.out.println("usaurio "+dto.filial() + " "+dto.perfilId());
            service.registroUsuario(dto);
            return ResponseEntity.ok().body(Map.of("msg","cadastrado com sucesso!"));
    }
    @PreAuthorize("@permissaoService.hasPermission(authentication, 'GERENCIAR_USUARIOS')")
    @GetMapping("/lista")
    public ResponseEntity<Page<UsuarioRequestDTO>> findAll(Pageable page, @RequestParam(name = "busca", required = false) String busca){
      var lista = service.listaUsuario(page,busca);
        return ResponseEntity.ok().body(lista);
    }

    @PostMapping("/login")
    @Transactional
    public ResponseEntity<TokenResponse> login(@RequestBody @Valid UsuarioLoginDTO dto){
      var resposta = service.authLogin(dto);
        return ResponseEntity.ok().body(resposta);
    }
    @PreAuthorize("@permissaoService.hasPermission(authentication, 'GERENCIAR_USUARIOS')")
    @GetMapping("/usuario-lista-perfil")
    public Map<String, UsuarioRequestPerfilDTO> buscaUsuarioPerfil(@RequestParam("email") String email) {
        return service.buscaUsuarioPerfil(email);
    }
    @PreAuthorize("@permissaoService.hasPermission(authentication, 'GERENCIAR_USUARIOS')")
    @GetMapping("/usuario-add-perfil")
    public ResponseEntity<Map<String,String>> AdicionarPerfil(@RequestParam("usuarioId") long usuarioId,
                                                              @RequestParam("perfilId") long perfilId
                                                              ) {
        try {
            service.adicionarPerfil(usuarioId,perfilId);
            return ResponseEntity.ok(Map.of("msg","Perfil adicionando"));
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    @PreAuthorize("@permissaoService.hasPermission(authentication, 'GERENCIAR_USUARIOS')")
    @PostMapping("/alterar/senha")
    @Transactional
    public ResponseEntity<?> AlteraSenha(@RequestParam("email") String email){
        String pwd = service.AlteraSenha(email);
        return ResponseEntity.ok(Map.of("psw",pwd));
    }
    @GetMapping("/busca/unit")
    public ResponseEntity<UsuarioBuscaRequestDTO> BuscaUsuarioUnico(@RequestParam("usuarioId") Long id){
        UsuarioBuscaRequestDTO usuario = service.buscaUsuarioId(id);
        return ResponseEntity.ok().body(usuario);
    }
    @PutMapping("/avatar")
    public ResponseEntity<?> adicionarImagem(@RequestParam("usuarioId") Long usuarioId, @RequestParam("file") MultipartFile file){
        try {
            service.salvaImagem(usuarioId,file);
            return ResponseEntity.ok().body(Map.of("msg","Imagem Adicionada"));
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

}
package com.portariacd.portaria.intefaces.controllers;

import com.portariacd.portaria.application.services.UsuarioService;
import com.portariacd.portaria.domain.models.vo.CadastroUsuarioDto;
import com.portariacd.portaria.domain.models.vo.usuarioVO.ResponseUsuarioDTO;
import com.portariacd.portaria.domain.models.vo.usuarioVO.TokenResponse;
import com.portariacd.portaria.domain.models.vo.usuarioVO.UsuarioLoginDTO;
import com.portariacd.portaria.domain.models.vo.usuarioVO.UsuarioRequestDTO;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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
}

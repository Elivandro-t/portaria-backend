package com.portariacd.portaria.domain.models.auth;

import com.portariacd.portaria.domain.models.vo.CadastroUsuarioDto;
import com.portariacd.portaria.infrastructure.persistence.UsuarioEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;

public class Usuario {
    private  long id;
    private String nome;
    private String email;
    private String password;
    private LocalDateTime createDate;
    private String ocupacaoOperacional;
    private Boolean ativo;
    private int filial;
    private String avatar;
    private Perfil perfil;
    public Usuario(CadastroUsuarioDto dto, LocalDateTime now,Boolean ativo) {
        this.nome = dto.nome();
        this.email = dto.email();
        this.ativo = ativo;
        this.password = criptofrafiaDeSenha(dto.password());
        this.createDate = now;
        this.ocupacaoOperacional = dto.ocupacaoOperacional();
        this.filial = dto.filial();
    }

    public Usuario(UsuarioEntity entidade) {
        this.id = entidade.getId();
        this.nome = entidade.getNome();
        this.ativo = entidade.getAtivo();
        this.email = entidade.getEmail();
        this.createDate = entidade.getDataCriacao();
        this.ocupacaoOperacional = entidade.getOcupacaoOperacional();
        this.filial = entidade.getFilial();
        if(entidade.getPerfil()!=null){
            this.perfil = new Perfil(entidade.getPerfil());
        }
      }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getOcupacaoOperacional() {
        return ocupacaoOperacional;
    }

    public void setOcupacaoOperacional(String ocupacaoOperacional) {
        this.ocupacaoOperacional = ocupacaoOperacional;
    }


    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        createDate = createDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getFilial() {
        return filial;
    }

    public void setFilial(int filial) {
        this.filial = filial;
    }

    public String criptofrafiaDeSenha(String password) {
            return new BCryptPasswordEncoder().encode(password);
    }
    public String getAvatar() {
        return avatar;
    }
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}

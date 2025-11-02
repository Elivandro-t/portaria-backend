package com.portariacd.portaria.infrastructure.persistence;
import com.portariacd.portaria.domain.models.auth.Usuario;
import com.portariacd.portaria.infrastructure.persistence.registroVisitante.RegistroVisitantePortariaEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
@Entity
@Table(name = "usuario")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UsuarioEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private String avatar;
    private String email;
    private String password;
    private LocalDateTime dataCriacao;
    private String ocupacaoOperacional;
    private int filial;
    @ManyToOne
    private PerfilEntity perfil;
    @OneToMany(mappedBy = "criador",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<RegistroVisitantePortariaEntity> registros;
    private Boolean ativo;

    public UsuarioEntity(Usuario usuario,PerfilEntity perfil) {
        this.nome  = usuario.getNome();
        this.email = usuario.getEmail();
        this.ocupacaoOperacional = usuario.getOcupacaoOperacional();
        this.ativo =usuario.getAtivo();
        this.filial = usuario.getFilial();
        this.dataCriacao = usuario.getCreateDate();
        this.password = usuario.getPassword();
        if(perfil!=null){
            this.perfil = perfil;
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<String> rolesPermition = Set.of(
                "ROLE_ADMIN",
                "FISCAL",
                "AUTORIZADO"
        );
        List<GrantedAuthority> authorities = new ArrayList<>();
//        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
           if(perfil.permissoes!=null) {
               if (rolesPermition.contains(perfil.getNome())) {
                   authorities.add(new SimpleGrantedAuthority(perfil.getNome()));
               }
               perfil.getPermissoes().forEach(permitionEntity -> {
                   authorities.add(new SimpleGrantedAuthority(permitionEntity.getName()));
               });
           }
        return authorities;
    }
    public String criptofrafiaDeSenha(
            String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

    public void atualizaSenha(String password){
      this.password =  criptofrafiaDeSenha(password);
    }


    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

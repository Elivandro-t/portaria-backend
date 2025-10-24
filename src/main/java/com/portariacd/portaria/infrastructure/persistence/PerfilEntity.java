package com.portariacd.portaria.infrastructure.persistence;

import com.portariacd.portaria.domain.models.auth.Perfil;
import com.portariacd.portaria.infrastructure.persistence.funcao.PermissionEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "perfil")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PerfilEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String nome;
    @OneToMany(mappedBy = "perfil", cascade = CascadeType.ALL)
    List<UsuarioEntity> usuario;
    @ManyToMany(fetch = FetchType.EAGER)
    Set<PermissionEntity> permissoes;

    public PerfilEntity(Perfil pefil) {
        this.nome = pefil.getNome();
    }


}

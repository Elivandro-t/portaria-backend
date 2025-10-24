package com.portariacd.portaria.infrastructure.persistence.log;

import com.portariacd.portaria.domain.models.vo.usuarioVO.UsuarioRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Entity
@Table(name = "log_acao")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LogAcaoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long usuarioId;
    private String usuario;
    private String acao;
    private String descricao;
    private LocalDateTime dataHora;

    public LogAcaoEntity(UsuarioRequestDTO usuario, String acao,String descricao) {
        this.usuarioId = usuario.id();
        this.usuario = usuario.nome();
        this.acao = acao;
        this.descricao = descricao;
        this.dataHora = LocalDateTime.now();

}
}

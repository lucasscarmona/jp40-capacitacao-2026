package br.com.indra.jp_capacitacao_2026.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transacoes_estoque")
public class TransacaoEstoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produtos produto;

    @Column(nullable = false)
    private String tipo;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(name = "data_transacao", updatable = false)
    private LocalDateTime dataTransacao;

    @PrePersist
    protected void onCreate() {
        dataTransacao = LocalDateTime.now();
    }
}
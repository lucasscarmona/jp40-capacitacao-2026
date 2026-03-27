package br.com.indra.jp_capacitacao_2026.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "itens_carrinho")
public class ItemCarrinho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "carrinho_id", nullable = false)
    @JsonIgnore
    private Carrinho carrinho;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produtos produto;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(name = "preco_congelado", nullable = false)
    private BigDecimal precoCongelado;
}
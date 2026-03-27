package br.com.indra.jp_capacitacao_2026.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String status = "AGUARDANDO_PAGAMENTO";

    @Column(name = "valor_total", nullable = false)
    private BigDecimal valorTotal = BigDecimal.ZERO;

    @Column(name = "data_pedido", updatable = false)
    private LocalDateTime dataPedido;


    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<ItemPedido> itens = new ArrayList<>();

    @Column(name = "valor_desconto")
    private BigDecimal valorDesconto = BigDecimal.ZERO;

    @ManyToOne
    @JoinColumn(name = "cupom_id")
    private Cupom cupom;

    @PrePersist
    protected void onCreate() {
        dataPedido = LocalDateTime.now();
    }
}
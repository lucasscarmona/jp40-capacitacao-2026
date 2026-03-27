package br.com.indra.jp_capacitacao_2026.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produtos produto;

    @ManyToOne
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;

    @NotNull(message = "A nota é obrigatória")
    @Min(value = 1, message = "A nota mínima é 1")
    @Max(value = 5, message = "A nota máxima é 5")
    @Column(nullable = false)
    private Integer nota;

    @NotBlank(message = "O comentário não pode estar vazio")
    @Column(length = 500)
    private String comentario;

    @Column(name = "data_avaliacao", updatable = false)
    private LocalDateTime dataAvaliacao;

    @PrePersist
    protected void onCreate() {
        dataAvaliacao = LocalDateTime.now();
    }
}
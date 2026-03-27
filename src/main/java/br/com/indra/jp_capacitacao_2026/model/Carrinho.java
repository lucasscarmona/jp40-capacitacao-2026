package br.com.indra.jp_capacitacao_2026.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "carrinhos")
public class Carrinho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String status = "ABERTO";

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "carrinho", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemCarrinho> itens;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
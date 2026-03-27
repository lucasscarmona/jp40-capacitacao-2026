package br.com.indra.jp_capacitacao_2026.controller;

import br.com.indra.jp_capacitacao_2026.model.Review;
import br.com.indra.jp_capacitacao_2026.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Reviews", description = "Endpoints para avaliações de produtos vinculadas a pedidos")
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @Operation(summary = "Adicionar uma avaliação", description = "Permite avaliar um produto (nota 1-5 e comentário) desde que o cliente o tenha comprado em um pedido válido.")
    @PostMapping("/adiciona")
    public ResponseEntity<Review> adicionarReview(
            @RequestParam Long pedidoId,
            @RequestParam Long produtoId,
            @RequestParam Integer nota,
            @RequestParam String comentario) {

        return ResponseEntity.ok(reviewService.adicionarReview(pedidoId, produtoId, nota, comentario));
    }

    @Operation(summary = "Buscar avaliações de um produto", description = "Retorna todas as avaliações feitas para um produto específico.")
    @GetMapping("/produto/{produtoId}")
    public ResponseEntity<List<Review>> buscarReviewsPorProduto(@PathVariable Long produtoId) {
        return ResponseEntity.ok(reviewService.buscarReviewsPorProduto(produtoId));
    }
}
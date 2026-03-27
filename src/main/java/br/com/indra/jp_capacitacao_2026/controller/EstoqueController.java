package br.com.indra.jp_capacitacao_2026.controller;

import br.com.indra.jp_capacitacao_2026.service.EstoqueService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "Estoque", description = "Endpoints para gerenciamento de entrada e saída de produtos")
@RequestMapping("/estoque")
public class EstoqueController {

    private final EstoqueService estoqueService;

    @Operation(summary = "Registrar entrada de mercadoria", description = "Aumenta o saldo de estoque de um produto específico.")
    @PostMapping("/entrada/{produtoId}")
    public ResponseEntity<String> registrarEntrada(
            @PathVariable Long produtoId,
            @RequestParam Integer quantidade) {

        estoqueService.registrarEntrada(produtoId, quantidade);
        return ResponseEntity.ok("Entrada de " + quantidade + " unidades registrada com sucesso para o produto ID: " + produtoId);
    }
}
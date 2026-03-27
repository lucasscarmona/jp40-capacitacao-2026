package br.com.indra.jp_capacitacao_2026.controller;

import br.com.indra.jp_capacitacao_2026.model.Carrinho;
import br.com.indra.jp_capacitacao_2026.service.CarrinhoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "Carrinho", description = "Endpoints para gerenciamento do carrinho de compras")
@RequestMapping("/carrinho")
public class CarrinhoController {

    private final CarrinhoService carrinhoService;

    @Operation(summary = "Criar um carrinho vazio", description = "Inicia um novo carrinho com status ABERTO.")
    @PostMapping("/cria")
    public ResponseEntity<Carrinho> criarCarrinho() {
        return ResponseEntity.ok(carrinhoService.criarCarrinho());
    }

    @Operation(summary = "Adicionar produto", description = "Adiciona um produto ao carrinho (congela o preço atual).")
    @PostMapping("/{carrinhoId}/adiciona-produto/{produtoId}")
    public ResponseEntity<Carrinho> adicionarProduto(
            @PathVariable Long carrinhoId,
            @PathVariable Long produtoId,
            @RequestParam Integer quantidade) {

        return ResponseEntity.ok(carrinhoService.adicionarProduto(carrinhoId, produtoId, quantidade));
    }

    @Operation(summary = "Ver carrinho", description = "Retorna o carrinho com a lista de todos os itens e preços congelados.")
    @GetMapping("/{carrinhoId}")
    public ResponseEntity<Carrinho> buscarCarrinho(@PathVariable Long carrinhoId) {
        return ResponseEntity.ok(carrinhoService.buscarCarrinho(carrinhoId));
    }
}
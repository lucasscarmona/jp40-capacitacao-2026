package br.com.indra.jp_capacitacao_2026.controller;

import br.com.indra.jp_capacitacao_2026.model.Produtos;
import br.com.indra.jp_capacitacao_2026.service.ProdutosService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Produtos", description = "Endpoints para gerenciamento de produtos")
@RequestMapping("/produtos")
public class ProdutosController {

    private final ProdutosService produtosService;

    //C

    /**
     * Recomendação de desenvolvimento, ampliar responses(responseEntity)
     * possíveis além do ok.
     */
    @Operation(description = "Endpoint para criar um novo produto",
            summary = "Criação de produto")
    @PostMapping("/cria")
    public ResponseEntity<Produtos> criarProduto(@Valid @RequestBody Produtos produto){
        return ResponseEntity.ok(produtosService.createdProduto(produto));
    }

    /**
     * GET
     * localhost:9090/produtos
     * @return
     */
    @GetMapping
    public ResponseEntity<List<Produtos>> getAll(){
        return ResponseEntity.ok(produtosService.getAll());
    }

    /**
     * GET
     * localhost:9090/produtos/1
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<Produtos> getById(@PathVariable Long id){
        return ResponseEntity.ok(produtosService.getById(id));
    }

    @Operation(summary = "Buscar produtos por nome", description = "Retorna uma lista de produtos que contêm a string informada.")
    @GetMapping("/busca")
    public ResponseEntity<List<Produtos>> getByNome(@RequestParam String nome) {
        return ResponseEntity.ok(produtosService.buscarPorNome(nome));
    }

    @Operation(summary = "Buscar produtos por categoria", description = "Retorna uma lista de produtos vinculados a um ID de categoria específico.")
    @GetMapping("/categoria/{categoriaId}")
    public ResponseEntity<List<Produtos>> getByCategoria(@PathVariable Long categoriaId) {
        return ResponseEntity.ok(produtosService.buscarPorCategoria(categoriaId));
    }

    @PutMapping("/atualiza")
    public ResponseEntity<Produtos> atualizarProduto(@RequestParam Long id,
                                                     @Valid @RequestBody Produtos produto){
        return ResponseEntity.ok(produtosService.atualiza(produto));
    }

    @PatchMapping("/atualiza-preco/{id}")
    public ResponseEntity<Produtos> atualizarProdutoParcial(@PathVariable Long id,
                                                            @RequestParam BigDecimal preco) {
        return ResponseEntity.ok(produtosService.atualizaPreco(id, preco));
    }

    @DeleteMapping("/deleta/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id) {
        produtosService.deletarProduto(id);
        return ResponseEntity.noContent().build();
    }
}
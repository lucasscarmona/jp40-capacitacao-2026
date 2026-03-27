package br.com.indra.jp_capacitacao_2026.controller;

import br.com.indra.jp_capacitacao_2026.model.Categoria;
import br.com.indra.jp_capacitacao_2026.service.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Categorias", description = "Endpoints para gerenciamento de categorias do catálogo")
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    @Operation(summary = "Criar nova categoria", description = "Cria uma categoria com nome obrigatório e único.")
    @PostMapping("/cria")
    public ResponseEntity<Categoria> criarCategoria(@RequestBody Categoria categoria) {
        Categoria novaCategoria = categoriaService.criarCategoria(categoria);
        return ResponseEntity.ok(novaCategoria);
    }

    @Operation(summary = "Listar todas", description = "Retorna a lista de todas as categorias cadastradas.")
    @GetMapping
    public ResponseEntity<List<Categoria>> getAll() {
        return ResponseEntity.ok(categoriaService.listarTodas());
    }

    @Operation(summary = "Buscar por ID", description = "Busca uma categoria específica pelo seu identificador único.")
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> getById(@PathVariable Long id) {
        return ResponseEntity.ok(categoriaService.buscarPorId(id));
    }

    @Operation(summary = "Deletar categoria", description = "Deleta uma categoria do banco de dados de forma definitiva.")
    @DeleteMapping("/deleta/{id}")
    public ResponseEntity<Void> deletarCategoria(@PathVariable Long id) {
        categoriaService.deletarCategoria(id);
        return ResponseEntity.noContent().build();
    }
}
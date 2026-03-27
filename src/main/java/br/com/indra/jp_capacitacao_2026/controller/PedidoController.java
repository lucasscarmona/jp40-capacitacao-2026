package br.com.indra.jp_capacitacao_2026.controller;

import br.com.indra.jp_capacitacao_2026.model.Pedido;
import br.com.indra.jp_capacitacao_2026.service.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "Pedidos", description = "Endpoints para gerenciamento de pedidos e checkout")
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    @Operation(summary = "Fechar pedido (Checkout)", description = "Transforma um carrinho ABERTO em um Pedido. Aceita um cupom de desconto opcional.")
    @PostMapping("/checkout/{carrinhoId}")
    public ResponseEntity<Pedido> fecharPedido(
            @PathVariable Long carrinhoId,
            @RequestParam(required = false) String cupom) {

        return ResponseEntity.ok(pedidoService.fecharPedido(carrinhoId, cupom));
    }

    @Operation(summary = "Buscar pedido", description = "Retorna os detalhes de um pedido finalizado pelo seu ID.")
    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscarPedido(@PathVariable Long id) {
        return ResponseEntity.ok(pedidoService.buscarPedido(id));
    }
}
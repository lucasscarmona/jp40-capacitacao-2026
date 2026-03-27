package br.com.indra.jp_capacitacao_2026.service;

import br.com.indra.jp_capacitacao_2026.model.Carrinho;
import br.com.indra.jp_capacitacao_2026.model.Cupom;
import br.com.indra.jp_capacitacao_2026.model.ItemCarrinho;
import br.com.indra.jp_capacitacao_2026.model.ItemPedido;
import br.com.indra.jp_capacitacao_2026.model.Pedido;
import br.com.indra.jp_capacitacao_2026.repository.CarrinhoRepository;
import br.com.indra.jp_capacitacao_2026.repository.CupomRepository;
import br.com.indra.jp_capacitacao_2026.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final CarrinhoRepository carrinhoRepository;
    private final CupomRepository cupomRepository; // Injetamos o repositório de cupons!

    @Transactional
    public Pedido fecharPedido(Long carrinhoId, String codigoCupom) {
        Carrinho carrinho = carrinhoRepository.findById(carrinhoId)
                .orElseThrow(() -> new RuntimeException("Carrinho não encontrado."));

        if ("FINALIZADO".equals(carrinho.getStatus())) {
            throw new RuntimeException("Este carrinho já foi transformado em pedido.");
        }
        if (carrinho.getItens() == null || carrinho.getItens().isEmpty()) {
            throw new RuntimeException("Não é possível fechar um pedido com o carrinho vazio.");
        }

        Pedido pedido = new Pedido();
        pedido.setStatus("AGUARDANDO_PAGAMENTO");
        BigDecimal valorTotal = BigDecimal.ZERO;

        for (ItemCarrinho itemCarrinho : carrinho.getItens()) {
            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setPedido(pedido);
            itemPedido.setProduto(itemCarrinho.getProduto());
            itemPedido.setQuantidade(itemCarrinho.getQuantidade());
            itemPedido.setPrecoUnitario(itemCarrinho.getPrecoCongelado());

            pedido.getItens().add(itemPedido);

            BigDecimal subtotal = itemCarrinho.getPrecoCongelado()
                    .multiply(new BigDecimal(itemCarrinho.getQuantidade()));

            valorTotal = valorTotal.add(subtotal);
        }

        if (codigoCupom != null && !codigoCupom.trim().isEmpty()) {
            Cupom cupom = cupomRepository.findByCodigo(codigoCupom.toUpperCase())
                    .orElseThrow(() -> new RuntimeException("Cupom inválido ou não encontrado."));

            if (!cupom.getAtivo()) {
                throw new RuntimeException("Este cupom não está mais ativo.");
            }

            BigDecimal valorDesconto = valorTotal.multiply(cupom.getPercentualDesconto())
                    .divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);

            pedido.setValorDesconto(valorDesconto);
            pedido.setCupom(cupom);

            valorTotal = valorTotal.subtract(valorDesconto);
        }

        pedido.setValorTotal(valorTotal);

        Pedido pedidoSalvo = pedidoRepository.save(pedido);

        carrinho.setStatus("FINALIZADO");
        carrinhoRepository.save(carrinho);

        return pedidoSalvo;
    }

    public Pedido buscarPedido(Long pedidoId) {
        return pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado."));
    }
}
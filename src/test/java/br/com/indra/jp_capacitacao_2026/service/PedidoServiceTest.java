package br.com.indra.jp_capacitacao_2026.service;

import br.com.indra.jp_capacitacao_2026.model.*;
import br.com.indra.jp_capacitacao_2026.repository.CarrinhoRepository;
import br.com.indra.jp_capacitacao_2026.repository.CupomRepository;
import br.com.indra.jp_capacitacao_2026.repository.PedidoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PedidoServiceTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @Mock
    private CarrinhoRepository carrinhoRepository;

    @Mock
    private CupomRepository cupomRepository;

    @InjectMocks
    private PedidoService pedidoService;

    private Carrinho carrinhoTeste;

    @BeforeEach
    void setUp() {
        carrinhoTeste = new Carrinho();
        carrinhoTeste.setId(1L);
        carrinhoTeste.setStatus("ABERTO");
        carrinhoTeste.setItens(new ArrayList<>());

        Produtos produto = new Produtos();
        produto.setId(1L);
        produto.setNome("Smartphone de Teste");

        ItemCarrinho item = new ItemCarrinho();
        item.setProduto(produto);
        item.setQuantidade(2);
        item.setPrecoCongelado(new BigDecimal("1000.00")); // O total do carrinho será 2000

        carrinhoTeste.getItens().add(item);
    }

    @Test
    void fecharPedido_SemCupom_DeveCalcularTotalCorretamente() {
        when(carrinhoRepository.findById(1L)).thenReturn(Optional.of(carrinhoTeste));
        when(pedidoRepository.save(any(Pedido.class))).thenAnswer(i -> i.getArgument(0));

        Pedido pedidoResult = pedidoService.fecharPedido(1L, null);

        assertNotNull(pedidoResult);
        assertEquals(new BigDecimal("2000.00"), pedidoResult.getValorTotal());
        assertEquals(BigDecimal.ZERO, pedidoResult.getValorDesconto());
        assertEquals("AGUARDANDO_PAGAMENTO", pedidoResult.getStatus());
    }

    @Test
    void fecharPedido_ComCupomValido_DeveAplicarDesconto() {
        Cupom cupom = new Cupom();
        cupom.setCodigo("DEZ");
        cupom.setPercentualDesconto(new BigDecimal("10"));
        cupom.setAtivo(true);

        when(carrinhoRepository.findById(1L)).thenReturn(Optional.of(carrinhoTeste));
        when(cupomRepository.findByCodigo("DEZ")).thenReturn(Optional.of(cupom));
        when(pedidoRepository.save(any(Pedido.class))).thenAnswer(i -> i.getArgument(0));

        Pedido pedidoResult = pedidoService.fecharPedido(1L, "DEZ");

        assertNotNull(pedidoResult);
        assertEquals(new BigDecimal("1800.00"), pedidoResult.getValorTotal());
        assertEquals(new BigDecimal("200.00"), pedidoResult.getValorDesconto());
    }
}
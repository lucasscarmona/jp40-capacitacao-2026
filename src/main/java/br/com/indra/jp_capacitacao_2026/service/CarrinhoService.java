package br.com.indra.jp_capacitacao_2026.service;

import br.com.indra.jp_capacitacao_2026.model.Carrinho;
import br.com.indra.jp_capacitacao_2026.model.ItemCarrinho;
import br.com.indra.jp_capacitacao_2026.model.Produtos;
import br.com.indra.jp_capacitacao_2026.repository.CarrinhoRepository;
import br.com.indra.jp_capacitacao_2026.repository.ItemCarrinhoRepository;
import br.com.indra.jp_capacitacao_2026.repository.ProdutosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CarrinhoService {

    private final CarrinhoRepository carrinhoRepository;
    private final ItemCarrinhoRepository itemCarrinhoRepository;
    private final ProdutosRepository produtosRepository;

    private final EstoqueService estoqueService;

    public Carrinho criarCarrinho() {
        Carrinho carrinho = new Carrinho();
        carrinho.setItens(new ArrayList<>());
        return carrinhoRepository.save(carrinho);
    }

    public Carrinho adicionarProduto(Long carrinhoId, Long produtoId, Integer quantidade) {
        Carrinho carrinho = carrinhoRepository.findById(carrinhoId)
                .orElseThrow(() -> new RuntimeException("Carrinho não encontrado."));

        Produtos produto = produtosRepository.findById(produtoId)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado."));

        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser maior que zero.");
        }

        estoqueService.registrarSaida(produtoId, quantidade);

        ItemCarrinho item = new ItemCarrinho();
        item.setCarrinho(carrinho);
        item.setProduto(produto);
        item.setQuantidade(quantidade);
        item.setPrecoCongelado(produto.getPreco());

        carrinho.getItens().add(item);
        itemCarrinhoRepository.save(item);

        return carrinhoRepository.save(carrinho);
    }

    public Carrinho buscarCarrinho(Long carrinhoId) {
        return carrinhoRepository.findById(carrinhoId)
                .orElseThrow(() -> new RuntimeException("Carrinho não encontrado."));
    }
}
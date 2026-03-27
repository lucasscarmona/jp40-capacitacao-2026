package br.com.indra.jp_capacitacao_2026.service;

import br.com.indra.jp_capacitacao_2026.model.Produtos;
import br.com.indra.jp_capacitacao_2026.model.TransacaoEstoque;
import br.com.indra.jp_capacitacao_2026.repository.ProdutosRepository;
import br.com.indra.jp_capacitacao_2026.repository.TransacaoEstoqueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EstoqueService {

    private final TransacaoEstoqueRepository transacaoEstoqueRepository;
    private final ProdutosRepository produtosRepository;

    @Transactional
    public void registrarEntrada(Long produtoId, Integer quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade de entrada deve ser maior que zero.");
        }

        Produtos produto = produtosRepository.findById(produtoId)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado."));

        produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() + quantidade);
        produtosRepository.save(produto);

        TransacaoEstoque transacao = new TransacaoEstoque();
        transacao.setProduto(produto);
        transacao.setTipo("ENTRADA");
        transacao.setQuantidade(quantidade);
        transacaoEstoqueRepository.save(transacao);
    }

    @Transactional
    public void registrarSaida(Long produtoId, Integer quantidade) {
        Produtos produto = produtosRepository.findById(produtoId)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado."));

        if (produto.getQuantidadeEstoque() < quantidade) {
            throw new RuntimeException("Estoque insuficiente para o produto: " + produto.getNome() + ". Saldo atual: " + produto.getQuantidadeEstoque());
        }

        produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - quantidade);
        produtosRepository.save(produto);

        TransacaoEstoque transacao = new TransacaoEstoque();
        transacao.setProduto(produto);
        transacao.setTipo("SAIDA");
        transacao.setQuantidade(quantidade);
        transacaoEstoqueRepository.save(transacao);
    }
}
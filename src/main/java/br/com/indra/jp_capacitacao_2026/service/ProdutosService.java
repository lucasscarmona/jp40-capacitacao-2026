package br.com.indra.jp_capacitacao_2026.service;

import br.com.indra.jp_capacitacao_2026.model.HistoricoPreco;
import br.com.indra.jp_capacitacao_2026.model.Produtos;
import br.com.indra.jp_capacitacao_2026.repository.HistoricoPrecoRepository;
import br.com.indra.jp_capacitacao_2026.repository.ProdutosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutosService {

    private final ProdutosRepository produtosRepository;
    private final HistoricoPrecoRepository historicoPrecoRepository;

    public List<Produtos> getAll() {
        return produtosRepository.findAll();
    }

    public Produtos createdProduto(Produtos produto) {
        return produtosRepository.save(produto);
    }

    public Produtos atualiza(Produtos produto) {
        return produtosRepository.save(produto);
    }

    public void deletarProduto(Long id) {
        produtosRepository.deleteById(id);
    }

    public Produtos getById(Long id) {
        return produtosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    @Transactional
    public Produtos atualizaPreco(Long id, BigDecimal novoPreco) {
        final var produto = produtosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        final var historico = new HistoricoPreco();
        historico.setPrecoAntigo(produto.getPreco());
        historico.setProdutos(produto);
        historico.setPrecoNovo(novoPreco);
        historico.setDataAlteracao(LocalDateTime.now());

        historicoPrecoRepository.save(historico);

        produto.setPreco(novoPreco);
        return produtosRepository.saveAndFlush(produto);
    }

    public List<Produtos> buscarPorNome(String nome) {
        return produtosRepository.findByNomeContainingIgnoreCase(nome);
    }

    public List<Produtos> buscarPorCategoria(Long categoriaId) {
        return produtosRepository.findByCategoriaId(categoriaId);
    }
}
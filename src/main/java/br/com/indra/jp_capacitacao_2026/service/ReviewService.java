package br.com.indra.jp_capacitacao_2026.service;

import br.com.indra.jp_capacitacao_2026.model.ItemPedido;
import br.com.indra.jp_capacitacao_2026.model.Pedido;
import br.com.indra.jp_capacitacao_2026.model.Produtos;
import br.com.indra.jp_capacitacao_2026.model.Review;
import br.com.indra.jp_capacitacao_2026.repository.PedidoRepository;
import br.com.indra.jp_capacitacao_2026.repository.ProdutosRepository;
import br.com.indra.jp_capacitacao_2026.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final PedidoRepository pedidoRepository;
    private final ProdutosRepository produtosRepository;

    public Review adicionarReview(Long pedidoId, Long produtoId, Integer nota, String comentario) {

        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado."));

        Produtos produto = produtosRepository.findById(produtoId)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado."));

        boolean comprouProduto = false;
        for (ItemPedido item : pedido.getItens()) {
            if (item.getProduto().getId().equals(produtoId)) {
                comprouProduto = true;
                break;
            }
        }

        if (!comprouProduto) {
            throw new RuntimeException("Você não pode avaliar um produto que não faz parte deste pedido.");
        }

        if (reviewRepository.existsByPedidoIdAndProdutoId(pedidoId, produtoId)) {
            throw new RuntimeException("Você já avaliou este produto para este pedido.");
        }

        Review review = new Review();
        review.setPedido(pedido);
        review.setProduto(produto);
        review.setNota(nota);
        review.setComentario(comentario);

        return reviewRepository.save(review);
    }

    public List<Review> buscarReviewsPorProduto(Long produtoId) {
        return reviewRepository.findByProdutoId(produtoId);
    }
}
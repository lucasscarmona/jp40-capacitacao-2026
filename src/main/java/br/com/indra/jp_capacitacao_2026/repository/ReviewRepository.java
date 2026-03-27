package br.com.indra.jp_capacitacao_2026.repository;

import br.com.indra.jp_capacitacao_2026.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByProdutoId(Long produtoId);

    boolean existsByPedidoIdAndProdutoId(Long pedidoId, Long produtoId);
}
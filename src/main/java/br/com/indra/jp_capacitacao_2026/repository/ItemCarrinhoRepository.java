package br.com.indra.jp_capacitacao_2026.repository;

import br.com.indra.jp_capacitacao_2026.model.ItemCarrinho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemCarrinhoRepository extends JpaRepository<ItemCarrinho, Long> {
}
package br.com.indra.jp_capacitacao_2026.repository;

import br.com.indra.jp_capacitacao_2026.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
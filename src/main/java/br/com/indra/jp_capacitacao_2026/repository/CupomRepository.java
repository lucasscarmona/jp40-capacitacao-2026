package br.com.indra.jp_capacitacao_2026.repository;

import br.com.indra.jp_capacitacao_2026.model.Cupom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CupomRepository extends JpaRepository<Cupom, Long> {

    Optional<Cupom> findByCodigo(String codigo);
}
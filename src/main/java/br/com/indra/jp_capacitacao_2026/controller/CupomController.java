package br.com.indra.jp_capacitacao_2026.controller;

import br.com.indra.jp_capacitacao_2026.model.Cupom;
import br.com.indra.jp_capacitacao_2026.repository.CupomRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "Cupons", description = "Endpoints para criar e gerenciar promoções")
@RequestMapping("/cupons")
public class CupomController {

    private final CupomRepository cupomRepository;

    @Operation(summary = "Criar novo cupom", description = "Cadastra um código de desconto na loja.")
    @PostMapping("/cria")
    public ResponseEntity<Cupom> criarCupom(@RequestBody Cupom cupom) {
        cupom.setCodigo(cupom.getCodigo().toUpperCase());
        return ResponseEntity.ok(cupomRepository.save(cupom));
    }
}
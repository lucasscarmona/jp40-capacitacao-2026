package br.com.indra.jp_capacitacao_2026.service;

import br.com.indra.jp_capacitacao_2026.model.Categoria;
import br.com.indra.jp_capacitacao_2026.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public Categoria criarCategoria(Categoria categoria) {
        if (categoria.getNome() == null || categoria.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome da categoria é obrigatório.");
        }

        Optional<Categoria> categoriaExistente = categoriaRepository.findByNome(categoria.getNome());
        if (categoriaExistente.isPresent()) {
            throw new IllegalArgumentException("Já existe uma categoria com este nome.");
        }

        return categoriaRepository.save(categoria);
    }

    public List<Categoria> listarTodas() {
        return categoriaRepository.findAll();
    }

    public Categoria buscarPorId(Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada com o ID: " + id));
    }

    public void deletarCategoria(Long id) {
        Categoria categoria = buscarPorId(id);
        categoriaRepository.delete(categoria);
    }
}
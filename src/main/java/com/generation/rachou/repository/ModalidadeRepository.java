package com.generation.rachou.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.generation.rachou.model.Modalidade;

@Repository
public interface ModalidadeRepository extends JpaRepository<Modalidade, Long> {
    
    // método de busca customizado pelo nome da modalidade
    public List<Modalidade> findAllByNomeContainingIgnoreCase(String nome);
}
package com.generation.rachou.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.generation.rachou.model.Viagem;

@Repository
public interface ViagemRepository extends JpaRepository<Viagem, Long> {
    
    // método para buscar viagens pelo destino 
    public List<Viagem> findAllByDestinoContainingIgnoreCase(String destino);
}
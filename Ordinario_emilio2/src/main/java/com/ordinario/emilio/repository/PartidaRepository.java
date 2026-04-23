package com.ordinario.emilio.repository;

import com.ordinario.emilio.entity.Partida;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PartidaRepository extends JpaRepository<Partida, Long> {
    List<Partida> findByJugadorId(Long jugadorId);
}

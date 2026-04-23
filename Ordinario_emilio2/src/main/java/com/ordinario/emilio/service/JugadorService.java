package com.ordinario.emilio.service;

import com.ordinario.emilio.dto.CrearJugadorRequest;
import com.ordinario.emilio.entity.Jugador;
import com.ordinario.emilio.repository.JugadorRepository;
import org.springframework.stereotype.Service;

@Service
public class JugadorService {

    private final JugadorRepository jugadorRepository;

    public JugadorService(JugadorRepository jugadorRepository) {
        this.jugadorRepository = jugadorRepository;
    }

    public Jugador crearJugador(CrearJugadorRequest request) {
        Jugador jugador = new Jugador();
        jugador.setNombre(request.getNombre());
        jugador.setSaldo(request.getSaldo());
        jugador.setActivo(request.isActivo());
        return jugadorRepository.save(jugador);
    }

    public Jugador obtenerPorId(Long jugadorId) {
        return jugadorRepository.findById(jugadorId)
                .orElseThrow(() -> new RuntimeException("El jugador no existe."));
    }

    public Jugador recargarSaldo(Long jugadorId, double monto) {
        Jugador jugador = obtenerPorId(jugadorId);
        jugador.setSaldo(jugador.getSaldo() + monto);
        return jugadorRepository.save(jugador);
    }
}

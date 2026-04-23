package com.ordinario.emilio.service;

import com.ordinario.emilio.entity.EstadoPartida;
import com.ordinario.emilio.entity.Jugador;
import com.ordinario.emilio.entity.Partida;
import com.ordinario.emilio.entity.Tiro;
import com.ordinario.emilio.repository.JugadorRepository;
import com.ordinario.emilio.repository.PartidaRepository;
import com.ordinario.emilio.repository.TiroRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
public class PartidaService {

    private final JugadorRepository jugadorRepository;
    private final PartidaRepository partidaRepository;
    private final TiroRepository tiroRepository;
    private final Random random = new Random();

    public PartidaService(JugadorRepository jugadorRepository,
                          PartidaRepository partidaRepository,
                          TiroRepository tiroRepository) {
        this.jugadorRepository = jugadorRepository;
        this.partidaRepository = partidaRepository;
        this.tiroRepository = tiroRepository;
    }

    @Transactional
    public Partida iniciarPartida(Long jugadorId, double apuesta) {
        Jugador jugador = jugadorRepository.findById(jugadorId)
                .orElseThrow(() -> new RuntimeException("El jugador no existe."));

        if (!jugador.isActivo()) {
            throw new RuntimeException("El jugador no esta activo.");
        }

        if (jugador.getSaldo() < apuesta) {
            throw new RuntimeException("El jugador no tiene saldo suficiente.");
        }

        jugador.setSaldo(jugador.getSaldo() - apuesta);
        jugadorRepository.save(jugador);

        Partida partida = new Partida();
        partida.setFecha(LocalDateTime.now());
        partida.setEstado(EstadoPartida.EN_JUEGO);
        partida.setJugador(jugador);
        partida.setApuesta(apuesta);
        return partidaRepository.save(partida);
    }

    @Transactional
    public Tiro realizarTiro(Long partidaId) {
        Partida partida = partidaRepository.findById(partidaId)
                .orElseThrow(() -> new RuntimeException("La partida no existe."));

        if (partida.getEstado() == EstadoPartida.FINALIZADA) {
            throw new RuntimeException("La partida ya esta finalizada.");
        }

        int valorDado1 = random.nextInt(6) + 1;
        int valorDado2 = random.nextInt(6) + 1;
        int suma = valorDado1 + valorDado2;

        boolean gana = suma == 7 || suma == 11;
        boolean pierde = suma == 2 || suma == 3 || suma == 12;

        if (gana) {
            partida.setEstado(EstadoPartida.FINALIZADA);
            Jugador jugador = partida.getJugador();
            jugador.setSaldo(jugador.getSaldo() + (partida.getApuesta() * 2));
            jugadorRepository.save(jugador);
        } else if (pierde) {
            partida.setEstado(EstadoPartida.FINALIZADA);
        }

        partidaRepository.save(partida);

        Tiro tiro = new Tiro();
        tiro.setPartida(partida);
        tiro.setValorDado1(valorDado1);
        tiro.setValorDado2(valorDado2);
        tiro.setSuma(suma);
        tiro.setEsGanador(gana);
        return tiroRepository.save(tiro);
    }

    @Transactional
    public Partida finalizarPartidaManual(Long partidaId) {
        Partida partida = partidaRepository.findById(partidaId)
                .orElseThrow(() -> new RuntimeException("La partida no existe."));

        partida.setEstado(EstadoPartida.FINALIZADA);
        return partidaRepository.save(partida);
    }

    public List<Partida> obtenerHistorialPartidas(Long jugadorId) {
        if (!jugadorRepository.existsById(jugadorId)) {
            throw new RuntimeException("El jugador no existe.");
        }

        return partidaRepository.findByJugadorId(jugadorId);
    }
}

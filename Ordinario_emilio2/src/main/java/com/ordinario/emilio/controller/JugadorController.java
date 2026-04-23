package com.ordinario.emilio.controller;

import com.ordinario.emilio.dto.CrearJugadorRequest;
import com.ordinario.emilio.dto.RecargarSaldoRequest;
import com.ordinario.emilio.entity.Jugador;
import com.ordinario.emilio.entity.Partida;
import com.ordinario.emilio.service.JugadorService;
import com.ordinario.emilio.service.PartidaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/jugadores")
@CrossOrigin({"*"})
public class JugadorController {

    private final JugadorService jugadorService;
    private final PartidaService partidaService;

    public JugadorController(JugadorService jugadorService, PartidaService partidaService) {
        this.jugadorService = jugadorService;
        this.partidaService = partidaService;
    }

    @PostMapping
    public ResponseEntity<?> crearJugador(@Valid @RequestBody CrearJugadorRequest request) {
        return new ResponseEntity<>(jugadorService.crearJugador(request), HttpStatus.CREATED);
    }

    @PostMapping("/{jugadorId}/recargar")
    public ResponseEntity<?> recargarSaldo(@PathVariable Long jugadorId, @Valid @RequestBody RecargarSaldoRequest request) {
        return new ResponseEntity<>(jugadorService.recargarSaldo(jugadorId, request.getMonto()), HttpStatus.OK);
    }

    @GetMapping("/{jugadorId}/partidas")
    public ResponseEntity<?> obtenerHistorialPartidas(@PathVariable Long jugadorId) {
        return new ResponseEntity<>(partidaService.obtenerHistorialPartidas(jugadorId), HttpStatus.OK);
    }
}

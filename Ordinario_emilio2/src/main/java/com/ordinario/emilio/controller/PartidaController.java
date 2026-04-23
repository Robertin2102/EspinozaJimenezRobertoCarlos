package com.ordinario.emilio.controller;

import com.ordinario.emilio.dto.IniciarPartidaRequest;
import com.ordinario.emilio.entity.Partida;
import com.ordinario.emilio.entity.Tiro;
import com.ordinario.emilio.service.PartidaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/partidas")
@CrossOrigin({"*"})
public class PartidaController {

    private final PartidaService partidaService;

    public PartidaController(PartidaService partidaService) {
        this.partidaService = partidaService;
    }

    @PostMapping("/iniciar")
    public ResponseEntity<?> iniciarPartida(@Valid @RequestBody IniciarPartidaRequest request) {
        return new ResponseEntity<>(partidaService.iniciarPartida(request.getJugadorId(), request.getApuesta()), HttpStatus.CREATED);
    }

    @PostMapping("/{partidaId}/tiros")
    public ResponseEntity<?> realizarTiro(@PathVariable Long partidaId) {
        return new ResponseEntity<>(partidaService.realizarTiro(partidaId), HttpStatus.OK);
    }

    @PostMapping("/{partidaId}/finalizar")
    public ResponseEntity<?> finalizarPartidaManual(@PathVariable Long partidaId) {
        return new ResponseEntity<>(partidaService.finalizarPartidaManual(partidaId), HttpStatus.OK);
    }
}

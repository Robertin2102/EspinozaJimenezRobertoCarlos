package com.ordinario.emilio.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public class IniciarPartidaRequest {

    @NotNull(message = "El id del jugador es obligatorio.")
    private Long jugadorId;

    @DecimalMin(value = "0.0", inclusive = false, message = "La apuesta debe ser mayor a 0.")
    private double apuesta;

    public Long getJugadorId() {
        return jugadorId;
    }

    public void setJugadorId(Long jugadorId) {
        this.jugadorId = jugadorId;
    }

    public double getApuesta() {
        return apuesta;
    }

    public void setApuesta(double apuesta) {
        this.apuesta = apuesta;
    }
}

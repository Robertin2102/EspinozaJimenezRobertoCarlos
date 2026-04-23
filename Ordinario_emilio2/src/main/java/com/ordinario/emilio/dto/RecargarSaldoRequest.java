package com.ordinario.emilio.dto;

import jakarta.validation.constraints.DecimalMin;

public class RecargarSaldoRequest {

    @DecimalMin(value = "0.0", inclusive = false, message = "El monto debe ser mayor a 0.")
    private double monto;

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }
}

package com.ordinario.emilio.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;

public class CrearJugadorRequest {

    @NotBlank(message = "El nombre es obligatorio.")
    private String nombre;

    @DecimalMin(value = "0.0", inclusive = true, message = "El saldo inicial no puede ser negativo.")
    private double saldo;

    private boolean activo;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}

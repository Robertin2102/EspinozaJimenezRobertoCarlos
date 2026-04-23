package com.ordinario.emilio.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Tiro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnoreProperties({"tiros", "jugador"})
    private Partida partida;

    private int valorDado1;

    private int valorDado2;

    private int suma;

    private boolean esGanador;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Partida getPartida() {
        return partida;
    }

    public void setPartida(Partida partida) {
        this.partida = partida;
    }

    public int getValorDado1() {
        return valorDado1;
    }

    public void setValorDado1(int valorDado1) {
        this.valorDado1 = valorDado1;
    }

    public int getValorDado2() {
        return valorDado2;
    }

    public void setValorDado2(int valorDado2) {
        this.valorDado2 = valorDado2;
    }

    public int getSuma() {
        return suma;
    }

    public void setSuma(int suma) {
        this.suma = suma;
    }

    public boolean isEsGanador() {
        return esGanador;
    }

    public void setEsGanador(boolean esGanador) {
        this.esGanador = esGanador;
    }
}

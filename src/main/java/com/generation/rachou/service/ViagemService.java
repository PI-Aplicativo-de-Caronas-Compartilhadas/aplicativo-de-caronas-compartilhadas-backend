package com.generation.rachou.service;

import org.springframework.stereotype.Service;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class ViagemService {

    private static final double VELOCIDADE_MEDIA = 60.0;

    public double calcularTempoViagemAleatoria() {
        // Gera uma distância aleatória entre 5.0 e 35.0 km para simular SP
        double distanciaSimulada = ThreadLocalRandom.current().nextDouble(5.0, 35.0);

        // Tempo em horas = Distância / Velocidade
        double tempoHoras = distanciaSimulada / VELOCIDADE_MEDIA;

        // Retorna o tempo em minutos
        return tempoHoras * 60;
    }
}

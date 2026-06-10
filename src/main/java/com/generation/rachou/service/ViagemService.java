package com.generation.rachou.service;

import com.generation.rachou.model.Viagem;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class ViagemService {

    private static final double VELOCIDADE_MEDIA = 60.0;

    public Viagem prepararHorariosViagem(Viagem viagem) {
        // 1. Previsão de Saída recebe o horário atual do servidor
        LocalDateTime horarioAtual = LocalDateTime.now();
        viagem.setPrevisaoSaida(horarioAtual);

        // 2. Lógica para calcular a distância e o tempo simulado
        double distanciaSimulada = ThreadLocalRandom.current().nextDouble(5.0, 35.0);
        double tempoHoras = distanciaSimulada / VELOCIDADE_MEDIA;
        long tempoMinutos = Math.round(tempoHoras * 60);

        // 3. Previsão de Chegada recebe o horário atual + os minutos calculados
        LocalDateTime previsaoChegada = horarioAtual.plusMinutes(tempoMinutos);
        viagem.setPrevisaoChegada(previsaoChegada);

        // Retorna o objeto completamente preenchido pelas regras de negócio
        return viagem;
    }
}
package com.crymuzz.evotingapispring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EvotingApiSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(EvotingApiSpringApplication.class, args);
    }

}
// Eleccion 1 -> 29 de octubre 2:30pm - 30 de octubre a las 5pm
// TreadScrolling -> Clase -> en base a hilos -> 1 método -> Recursos
// Scheduling -> cron("* * * * * *") -> ("15 1 * * * * ") -> ("* 1 * * * * ") -> Elección (false)
// Entidad Eleccion -> Inicio y terminar elección -> LocalDate ("2024-10-29") -> cron("* * 1 * * *")
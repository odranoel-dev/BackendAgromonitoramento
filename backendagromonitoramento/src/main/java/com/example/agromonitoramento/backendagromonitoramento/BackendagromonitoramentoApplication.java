package com.example.agromonitoramento.backendagromonitoramento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.example.agromonitoramento.backendagromonitoramento.model")
public class BackendagromonitoramentoApplication {
    public static void main(String[] args) {
        SpringApplication.run(BackendagromonitoramentoApplication.class, args);
    }
}

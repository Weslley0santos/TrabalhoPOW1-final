package br.ufsm.csi; // <--- ATENÇÃO: Verifique e ajuste o pacote conforme a sua estrutura

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class TrabalhoFinalPoApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // Aponta para esta própria classe principal para configuração do WAR
        return builder.sources(TrabalhoFinalPoApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(TrabalhoFinalPoApplication.class, args);
    }

}
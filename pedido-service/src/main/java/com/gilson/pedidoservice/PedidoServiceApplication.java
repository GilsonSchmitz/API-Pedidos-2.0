package com.gilson.pedidoservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@OpenAPIDefinition(info = @Info(title = "Pedido-Microsservice", version = "1", description = "API de pedidos"))
@SpringBootApplication
public class PedidoServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PedidoServiceApplication.class, args);
    }

}

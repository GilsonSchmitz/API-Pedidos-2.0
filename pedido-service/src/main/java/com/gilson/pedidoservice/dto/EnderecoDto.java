package com.gilson.pedidoservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component
public class EnderecoDto {

    private Long id;

    private String cep;

    private String cidade;

    private String bairro;

    private String rua;

    private Long numero;

    private String complemento;
}
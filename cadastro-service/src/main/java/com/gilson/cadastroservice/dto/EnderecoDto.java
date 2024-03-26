package com.gilson.cadastroservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoDto {


    private Long id;

    private String cep;

    private String cidade;

    private String bairro;

    private String rua;

    private Long numero;

    private String complemento;
}
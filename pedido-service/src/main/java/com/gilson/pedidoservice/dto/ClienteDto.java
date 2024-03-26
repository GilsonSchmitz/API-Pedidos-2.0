package com.gilson.pedidoservice.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDto {


    private Long id;

    @NotEmpty
    private String nome;

    @NotEmpty
    private String cpf;

    @NotEmpty
    private String email;

    private EnderecoDto enderecoDto;

    private String dadosDoCartao;

}
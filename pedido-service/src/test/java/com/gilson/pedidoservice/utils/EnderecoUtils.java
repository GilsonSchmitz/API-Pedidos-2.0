package com.gilson.pedidoservice.utils;

import com.gilson.pedidoservice.dto.EnderecoDto;
import org.springframework.stereotype.Component;

@Component
public class EnderecoUtils {

    public EnderecoDto retornarEndereco() {
        return EnderecoDto.builder().complemento("Casa branca").numero(123l).rua("Luiz Ghizoni").cep("88750-00").bairro("Santa Augusta").cidade("Tubarao").build();


    }
}
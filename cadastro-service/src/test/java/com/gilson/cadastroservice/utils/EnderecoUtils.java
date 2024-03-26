package com.gilson.cadastroservice.utils;

import com.gilson.cadastroservice.model.Endereco;
import org.springframework.stereotype.Component;


@Component
public class EnderecoUtils {

    public Endereco retornarEndereco() {
        return Endereco.builder()
                .complemento("Casa branca")
                .numero(123l)
                .rua("Luiz Ghizoni")
                .cep("88750-00")
                .bairro("Santa Augusta")
                .cidade("Tubarao")
                .build();


    }
}
package com.gilson.pedidoservice.utils;

import com.gilson.pedidoservice.dto.ClienteDto;
import org.springframework.stereotype.Component;

@Component
public class ClienteUtils {
    public ClienteDto retornarCliente() {
        return ClienteDto.builder().id(2l).nome("Gilson").cpf("10598454900").email("gilsonschmj@gmail.com").dadosDoCartao("123123123").build();

    }
}

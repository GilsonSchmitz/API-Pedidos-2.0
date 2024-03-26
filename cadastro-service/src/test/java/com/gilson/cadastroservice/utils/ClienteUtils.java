package com.gilson.cadastroservice.utils;

import com.gilson.cadastroservice.model.Cliente;
import org.springframework.stereotype.Component;

@Component
public class ClienteUtils {
    public Cliente retornarCliente() {
        return Cliente.builder().id(2l).nome("Gilson").cpf("10598454900").email("gilsonschmj@gmail.com").dadosDoCartao("123123123").build();

    }
}

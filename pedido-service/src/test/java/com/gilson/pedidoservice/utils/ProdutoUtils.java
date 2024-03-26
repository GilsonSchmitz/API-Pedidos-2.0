package com.gilson.pedidoservice.utils;

import com.gilson.pedidoservice.dto.ProdutoDto;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class ProdutoUtils {

    public ProdutoDto retornarObjeto() {
        BigDecimal valor = BigDecimal.valueOf(23.0);
        valor = valor.setScale(2, RoundingMode.DOWN);

        return ProdutoDto.builder().id(1l).nome("Gas").valor(valor).build();
    }
}

package com.gilson.cadastroservice.utils;

import com.gilson.cadastroservice.model.Produto;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ProdutoUtils {

    public Produto retornarObjeto() {
        BigDecimal valor = BigDecimal.valueOf(23.0);
        return Produto.builder()
                .id(1l)
                .nome("Gas")
                .valor(valor)
                .build();
    }
}

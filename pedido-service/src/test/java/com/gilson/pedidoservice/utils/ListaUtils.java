package com.gilson.pedidoservice.utils;

import com.gilson.pedidoservice.model.ItemPedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class ListaUtils {

    @Autowired
    ProdutoUtils produtoUtils;


    public List<ItemPedido> retornarItens() {
        List<ItemPedido> itens = new ArrayList<>();
        ItemPedido itemPedido1 = ItemPedido.builder().idProduto(3l).quantidade(4l).valor(new BigDecimal("20.00")).build();
        ItemPedido itemPedido2 = ItemPedido.builder().idProduto(1l).quantidade(2L).valor(new BigDecimal("21.00")).build();
        itens.add(itemPedido1);
        itens.add(itemPedido2);
        return itens;
    }
}
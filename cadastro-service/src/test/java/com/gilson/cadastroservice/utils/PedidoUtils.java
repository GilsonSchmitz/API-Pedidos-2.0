package com.gilson.cadastroservice.utils;

import com.gilson.pedidoservice.dto.PedidoDto;
import com.gilson.pedidoservice.model.ItemPedido;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class PedidoUtils {

    public PedidoDto retornarObjeto() {
        return PedidoDto.builder().codigoRastreio("ABC123").cupomDesconto("DESCONTO").formaPgto("PIX").valorFrete(BigDecimal.ONE)
                .valorTotal(BigDecimal.valueOf(80.00))
                .dataPedido(LocalDateTime.now())
                .idCliente(retornarObjeto().getIdCliente())
                .listaItens(retornarObjeto().getListaItens())
                .idEndereco(retornarObjeto().getIdEndereco())
                .build();
    }

    public List<ItemPedido> retornarItens() {
        List<ItemPedido> itens = new ArrayList<>();
        ItemPedido itemPedido = ItemPedido.builder()
                .idProduto(retornarObjeto().getId())
                .quantidade(2l)
                .valor(new BigDecimal("20.00"))
                .build();
        ItemPedido itemPedido2 = ItemPedido.builder()
                .idProduto(retornarObjeto().getId())
                .quantidade(2L)
                .valor(new BigDecimal("20.00"))
                .build();
        itens.add(itemPedido);
        itens.add(itemPedido2);
        return itens;
    }

}

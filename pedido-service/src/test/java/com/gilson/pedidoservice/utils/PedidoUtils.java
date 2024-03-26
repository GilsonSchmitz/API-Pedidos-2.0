package com.gilson.pedidoservice.utils;

import com.gilson.pedidoservice.dto.ItemPedidoDto;
import com.gilson.pedidoservice.dto.PedidoDto;
import com.gilson.pedidoservice.model.ItemPedido;
import com.gilson.pedidoservice.model.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class PedidoUtils {

    @Autowired
    EnderecoUtils enderecoUtils;

    @Autowired
    ClienteUtils clienteUtils;

    @Autowired
    ListaUtils listaUtils;



public Pedido criarPedido() {
    List<ItemPedido> itensPedido = listaUtils.retornarItens();

    if (enderecoUtils == null || clienteUtils == null || listaUtils == null) {
        throw new IllegalStateException("Dependências não estão corretamente configuradas.");
    }
            return Pedido.builder().codigoRastreio("ABC123")
                    .cupomDesconto("DESCONTO")
                    .formaPgto("PIX")
                    .valorFrete(BigDecimal.ONE)
                    .valorTotal(BigDecimal.valueOf(80.00))
                    .dataPedido(LocalDateTime.now())
                    .idCliente(3l)
                    .listaItens(itensPedido)
                    .idEndereco(2l)
                    .build();
        }
    }


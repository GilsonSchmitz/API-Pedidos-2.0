package com.gilson.pedidoservice.service;

import com.gilson.pedidoservice.dto.ClienteDto;
import com.gilson.pedidoservice.dto.ProdutoDto;
import com.gilson.pedidoservice.model.ItemPedido;
import com.gilson.pedidoservice.model.Pedido;
import com.gilson.pedidoservice.proxy.ProxyObjetos;
import com.gilson.pedidoservice.repository.PedidoRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Tag(name = "Pedido service")
@Service
@RequiredArgsConstructor
public class PedidoService {

    @Autowired
    ProxyObjetos proxy;

    @Autowired
    PedidoRepository repository;

    public BigDecimal calcularSomaDosValores(List<ItemPedido> itens) {
        if (ObjectUtils.isEmpty(itens)) {
            throw new RuntimeException("A lista de itens esta vazia ou nula");
        }

        BigDecimal soma = BigDecimal.ZERO;
        for (ItemPedido item : itens) {
            BigDecimal subtotal = item.getValor().multiply(BigDecimal.valueOf(item.getQuantidade()));
            soma = soma.add(subtotal);
        }

        return soma;
    }

    public void validarPedido(Pedido pedido) {
        if (pedido.getListaItens() == null) {
            throw new RuntimeException("Lista de itens não pode ser nulo");
        }

        if (pedido.getIdCliente() == null) {
            throw new RuntimeException("ID do Cliente não pode ser nulo");
        }
    }

    public List<Pedido> retornoLista() {
        return repository.findAll();
    }

    public Pedido salvar(Pedido pedido) {
        ClienteDto cliente = proxy.obterCliente(pedido.getIdCliente());

        if (ObjectUtils.isEmpty(cliente)) {
            throw new RuntimeException("Cliente nao encontrado");
        }

        pedido.setIdCliente(cliente.getId());
        pedido.setDataPedido(LocalDateTime.now());
        this.validarPedido(pedido);
        pedido.setValorTotal(calcularSomaDosValores(pedido.getListaItens()));

        pedido.getListaItens().forEach(itemPedido -> {
            ProdutoDto produto = proxy.obterProduto(itemPedido.getIdProduto());
            itemPedido.setIdProduto(produto.getId());
            itemPedido.setPedido(pedido);
        });

        return repository.save(pedido);
    }

    @Transactional
    public Pedido atualizar(Long id, Pedido pedido) {
        this.validarPedido(pedido);

        pedido.getListaItens().forEach(itemPedido -> {
            if (itemPedido.getId() == null) {
                ProdutoDto produto = proxy.obterProduto(itemPedido.getIdProduto());
                itemPedido.setIdProduto(produto.getId());
                itemPedido.setPedido(pedido);
            }
        });

        return repository.save(pedido);
    }


    public void deletarPorId(Long id) {
        repository.deleteById(id);
    }

    public Pedido buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
    }

}

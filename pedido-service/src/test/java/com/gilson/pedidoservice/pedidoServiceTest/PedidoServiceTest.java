package com.gilson.pedidoservice.pedidoServiceTest;

import com.gilson.pedidoservice.model.Pedido;
import com.gilson.pedidoservice.service.PedidoService;
import com.gilson.pedidoservice.utils.PedidoUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY, connection = EmbeddedDatabaseConnection.H2)
@AutoConfigureMockMvc
class PedidoServiceTest {

    @Autowired
    PedidoService service;

    @Autowired
    PedidoUtils pedidoUtils;

    @Test
    public void salvarPedidoTest() {
        Pedido pedido = service.salvar(pedidoUtils.criarPedido());

        Assertions.assertEquals(pedido.getCodigoRastreio(), "ABC123");
        Assertions.assertEquals(pedido.getCupomDesconto(), "DESCONTO");
        Assertions.assertEquals(pedido.getFormaPgto(), "PIX");
    }

    @Test
    public void deletarPedidoTest() {
        Pedido pedido = pedidoUtils.criarPedido();
        service.salvar(pedido);
        service.deletarPorId(pedido.getId());

        Assertions.assertThrows(RuntimeException.class, () -> {
            service.buscarPorId(pedido.getId());
        });
    }

    @Test
    public void atualizarPedidoTest() {
        Pedido pedido = service.salvar(pedidoUtils.criarPedido());
        pedido.setFormaPgto("CARTAO");
        pedido.setCupomDesconto("DESCONTAO10");
        service.salvar(pedido);

        Assertions.assertEquals(pedido.getFormaPgto(), "CARTAO");
        Assertions.assertEquals(pedido.getCupomDesconto(), "DESCONTAO10");
    }

    @Test
    public void buscarPedidoTest() {
        Pedido pedido = service.salvar(pedidoUtils.criarPedido());
        Pedido pedidoAchado = service.buscarPorId(pedido.getId());
        Assertions.assertEquals(pedidoAchado.getFormaPgto(), "PIX");
        Assertions.assertEquals(pedidoAchado.getCupomDesconto(), "DESCONTO");
    }
}

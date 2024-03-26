package com.gilson.pedidoservice.pedidoRepositoryTest;

import com.gilson.pedidoservice.dto.PedidoDto;
import com.gilson.pedidoservice.model.Pedido;
import com.gilson.pedidoservice.repository.PedidoRepository;
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
public class PedidoRepositoryTest {

    @Autowired
    PedidoUtils pedidoUtils;

    @Autowired
    PedidoRepository repository;


    @Test
    public void criarRepositoryTest() {
        Pedido pedido = repository.save(pedidoUtils.criarPedido());

        Assertions.assertEquals(pedido.getFormaPgto(), "PIX");
        Assertions.assertEquals(pedido.getCupomDesconto(), "DESCONTO");
    }

    @Test
    public void buscarRepositoryTest() {
        Pedido pedido = repository.save(pedidoUtils.criarPedido());
        repository.findById(pedido.getId());

        Assertions.assertEquals(pedido.getFormaPgto(), "PIX");
        Assertions.assertEquals(pedido.getCupomDesconto(), "DESCONTO");
    }

    @Test
    public void atualizarRepositoryTest() {
        Pedido pedido = repository.save(pedidoUtils.criarPedido());
        pedido.setFormaPgto("CARTAO");
        pedido.setCupomDesconto("DESCONTAO10");
        Pedido pedidoAchado = repository.save(pedido);

        Assertions.assertEquals(pedidoAchado.getFormaPgto(), "CARTAO");
        Assertions.assertEquals(pedidoAchado.getCupomDesconto(), "DESCONTAO10");
    }

    @Test
    public void deletarRepositoryTest() {
        Pedido pedido = repository.save(pedidoUtils.criarPedido());
        repository.deleteById(pedido.getId());
        Pedido pedidoExcluido = repository.findById(pedido.getId()).orElse(null);

        Assertions.assertNull(pedidoExcluido);
    }
}



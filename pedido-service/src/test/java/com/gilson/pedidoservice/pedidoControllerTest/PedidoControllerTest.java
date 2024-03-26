package com.gilson.pedidoservice.pedidoControllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.gilson.pedidoservice.dto.PedidoDto;
import com.gilson.pedidoservice.model.Pedido;
import com.gilson.pedidoservice.repository.PedidoRepository;
import com.gilson.pedidoservice.utils.PedidoUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.converter.JavaTimeConversionPattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ActiveProfiles("test")
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY, connection = EmbeddedDatabaseConnection.H2)
@AutoConfigureMockMvc
public class PedidoControllerTest {
    static String PEDIDO_API = "/pedido-service/api/pedidos/";

    @Autowired
    JavaTimeModule mapper;

    @Autowired
    MockMvc mvc;

    @Autowired
    PedidoRepository repository;

    @Autowired
    PedidoUtils pedidoUtils;


    @Test
    @DisplayName("Criar pedido normal")
    public void inserirPedido_teste() throws Exception {
        Pedido pedido = pedidoUtils.criarPedido();

        String json = new ObjectMapper().writeValueAsString(pedido);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(PEDIDO_API).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(json);

        mvc.perform(request).andExpect(status().isCreated()).andExpect(MockMvcResultMatchers.jsonPath("id").isNotEmpty()).andExpect(MockMvcResultMatchers.jsonPath("idEndereco").value(pedido.getIdEndereco())).andExpect(MockMvcResultMatchers.jsonPath("idCliente").value(pedido.getIdCliente()));
    }

    @Test
    @DisplayName("Pegar pedido por ID")
    public void pegarPedido_teste() throws Exception {
        Pedido pedido = pedidoUtils.criarPedido();
        repository.save(pedido);

        String json = new ObjectMapper().writeValueAsString(pedido);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(PEDIDO_API + "/" + pedido.getId()).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(json);

        mvc.perform(request).andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("id").isNotEmpty()).andExpect(MockMvcResultMatchers.jsonPath("idEndereco").value(pedido.getIdEndereco())).andExpect(MockMvcResultMatchers.jsonPath("idCliente").value(pedido.getIdCliente()));
    }

    @Test
    @DisplayName("Atualizar pedido Test")
    public void atualizarPedidoTest() throws Exception {
        Pedido pedido = pedidoUtils.criarPedido();
        repository.save(pedido);

        pedido.setValorTotal(BigDecimal.valueOf(23));
        pedido.setFormaPgto("PIX");
        repository.save(pedido);
        String json = new ObjectMapper().writeValueAsString(pedido);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.put(PEDIDO_API + "/" + pedido.getId()).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(json);

        mvc.perform(request).andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("id").value(pedido.getId())).andExpect(MockMvcResultMatchers.jsonPath("idEndereco").value(pedido.getIdEndereco())).andExpect(MockMvcResultMatchers.jsonPath("idCliente").value(pedido.getIdCliente()));
    }

    @Test
    @DisplayName("apagar pedido")
    public void apagar_PedidoTeste() throws Exception {
        Pedido pedido = pedidoUtils.criarPedido();
        repository.save(pedido);
        String json = new ObjectMapper().writeValueAsString(pedido);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete(PEDIDO_API + "/" + pedido.getId()).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(json);

        mvc.perform(request).andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}

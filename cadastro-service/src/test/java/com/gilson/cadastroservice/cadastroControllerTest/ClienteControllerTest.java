package com.gilson.cadastroservice.cadastroControllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gilson.cadastroservice.model.Cliente;
import com.gilson.cadastroservice.repository.ClienteRepository;
import com.gilson.cadastroservice.utils.ClienteUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY, connection = EmbeddedDatabaseConnection.H2)
@AutoConfigureMockMvc
public class ClienteControllerTest {
    static String CLIENTE_API = "/cadastro-service/api/clientes/";

    @Autowired
    ClienteUtils clienteUtils;

    @Autowired
    MockMvc mvc;

    @Autowired
    ClienteRepository repository;

    @Test
    @DisplayName("Criar cliente normal")
    public void inserirCliente_teste() throws Exception {
        Cliente clienteSalvo = repository.save(clienteUtils.retornarCliente());

        String json = new ObjectMapper().writeValueAsString(clienteSalvo);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(CLIENTE_API).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(json);

        mvc.perform(request).andExpect(status().isCreated()).andExpect(MockMvcResultMatchers.jsonPath("id").isNotEmpty()).andExpect(MockMvcResultMatchers.jsonPath("nome").value(clienteSalvo.getNome())).andExpect(MockMvcResultMatchers.jsonPath("cpf").value(clienteSalvo.getCpf()));
    }

    @Test
    @DisplayName("Pegar cliente por ID")
    public void pegarCliente_teste() throws Exception {
        Cliente cliente = repository.save(clienteUtils.retornarCliente());

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(CLIENTE_API + "/" + cliente.getId()).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

        mvc.perform(request).andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("id").isNotEmpty()).andExpect(MockMvcResultMatchers.jsonPath("nome").value(cliente.getNome())).andExpect(MockMvcResultMatchers.jsonPath("cpf").value(cliente.getCpf()));
    }

    @Test
    @DisplayName("Atualizar cliente Test")
    public void atualizarClienteTest() throws Exception {
        Cliente cliente = repository.save(clienteUtils.retornarCliente());

        cliente.setCpf("10598454900");
        cliente.setNome("Bomba para gás");
        repository.save(cliente);

        String json = new ObjectMapper().writeValueAsString(cliente);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.put(CLIENTE_API + "/" + cliente.getId()).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(json);

        mvc.perform(request).andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("id").value(cliente.getId())).andExpect(MockMvcResultMatchers.jsonPath("nome").value(cliente.getNome())).andExpect(MockMvcResultMatchers.jsonPath("cpf").value(cliente.getCpf()));
    }

    @Test
    @DisplayName("apagar cliente")
    public void apagar_ClienteTeste() throws Exception {
        Cliente cliente = repository.save(clienteUtils.retornarCliente());

        String json = new ObjectMapper().writeValueAsString(cliente);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete(CLIENTE_API + "/" + cliente.getId()).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(json);

        mvc.perform(request).andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}


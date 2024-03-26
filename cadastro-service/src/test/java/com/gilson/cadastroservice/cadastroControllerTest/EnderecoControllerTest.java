package com.gilson.cadastroservice.cadastroControllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gilson.cadastroservice.model.Endereco;
import com.gilson.cadastroservice.repository.EnderecoRepository;
import com.gilson.cadastroservice.utils.EnderecoUtils;
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
public class EnderecoControllerTest {
    static String ENDERECO_API = "/cadastro-service/api/enderecos/";

    @Autowired
    EnderecoUtils enderecoUtils;

    @Autowired
    MockMvc mvc;

    @Autowired
    EnderecoRepository repository;

    @Test
    @DisplayName("Criar endereco normal")
    public void inserirEndereco_teste() throws Exception {
        Endereco endereco = repository.save(enderecoUtils.retornarEndereco());

        String json = new ObjectMapper().writeValueAsString(endereco);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(ENDERECO_API).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(json);

        mvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("id").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("cep").value(endereco.getCep()))
                .andExpect(MockMvcResultMatchers.jsonPath("numero").value(endereco.getNumero()));
    }

    @Test
    @DisplayName("Pegar endereco por ID")
    public void pegarEndereco_teste() throws Exception {
        Endereco endereco = repository.save(enderecoUtils.retornarEndereco());

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(ENDERECO_API + "/" + endereco.getId()).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

        mvc.perform(request).andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("id").isNotEmpty()).andExpect(MockMvcResultMatchers.jsonPath("cidade").value(endereco.getCidade())).andExpect(MockMvcResultMatchers.jsonPath("rua").value(endereco.getRua()));
    }

    @Test
    @DisplayName("Atualizar endereco Test")
    public void atualizarEnderecoTest() throws Exception {
        Endereco endereco = repository.save(enderecoUtils.retornarEndereco());

        endereco.setBairro("Vila lobos");
        endereco.setRua("Vidal Ramos");
        repository.save(endereco);

        String json = new ObjectMapper().writeValueAsString(endereco);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.put(ENDERECO_API + "/" + endereco.getId()).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(json);

        mvc.perform(request).andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("id").value(endereco.getId())).andExpect(MockMvcResultMatchers.jsonPath("bairro").value(endereco.getBairro())).andExpect(MockMvcResultMatchers.jsonPath("rua").value(endereco.getRua()));
    }

    @Test
    @DisplayName("endereco produto")
    public void apagar_EnderecoTeste() throws Exception {
        Endereco endereco = repository.save(enderecoUtils.retornarEndereco());

        String json = new ObjectMapper().writeValueAsString(endereco);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete(ENDERECO_API + "/" + endereco.getId()).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(json);

        mvc.perform(request).andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}


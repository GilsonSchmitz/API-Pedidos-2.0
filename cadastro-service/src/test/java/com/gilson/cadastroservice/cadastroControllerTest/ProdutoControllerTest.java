package com.gilson.cadastroservice.cadastroControllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gilson.cadastroservice.model.Produto;
import com.gilson.cadastroservice.repository.ProdutoRepository;
import com.gilson.cadastroservice.utils.ProdutoUtils;
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

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY, connection = EmbeddedDatabaseConnection.H2)
@AutoConfigureMockMvc
public class ProdutoControllerTest {

    static String PRODUTO_API = "/cadastro-service/api/produtos/";

    @Autowired
    ProdutoUtils produtoUtils;

    @Autowired
    MockMvc mvc;

    @Autowired
    ProdutoRepository produtoRepository;

    @Test
    @DisplayName("Criar produto normal")
    public void inserirProduto_teste() throws Exception {
        Produto produtoSalvo = produtoUtils.retornarObjeto();

        String json = new ObjectMapper().writeValueAsString(produtoSalvo);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(PRODUTO_API).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(json);

        mvc.perform(request).andExpect(status().isCreated()).andExpect(MockMvcResultMatchers.jsonPath("id").isNotEmpty()).andExpect(MockMvcResultMatchers.jsonPath("nome").value(produtoSalvo.getNome())).andExpect(MockMvcResultMatchers.jsonPath("valor").value(produtoSalvo.getValor()));
    }

    @Test
    @DisplayName("Pegar produto por ID")
    public void pegarProduto_teste() throws Exception {
        Produto produtoPegar = produtoRepository.save(produtoUtils.retornarObjeto());

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(PRODUTO_API + "/" + produtoPegar.getId()).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

        mvc.perform(request).andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("id").isNotEmpty()).andExpect(MockMvcResultMatchers.jsonPath("nome").value(produtoPegar.getNome())).andExpect(MockMvcResultMatchers.jsonPath("valor").value(produtoPegar.getValor()));
    }

    @Test
    @DisplayName("Atualizar produto Test")
    public void atualizarProdutoTest() throws Exception {
        Produto produto = produtoRepository.save(produtoUtils.retornarObjeto());

        produto.setValor(BigDecimal.valueOf(44.2));
        produto.setNome("Bomba para gás");
        produtoRepository.save(produto);

        String json = new ObjectMapper().writeValueAsString(produto);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.put(PRODUTO_API + "/" + produto.getId()).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(json);

        mvc.perform(request).andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("id").value(produto.getId())).andExpect(MockMvcResultMatchers.jsonPath("nome").value(produto.getNome())).andExpect(MockMvcResultMatchers.jsonPath("valor").value(produto.getValor()));
    }

    @Test
    @DisplayName("apagar produto")
    public void apagar_ProdutoTeste() throws Exception {
        Produto produto = produtoRepository.save(produtoUtils.retornarObjeto());

        String json = new ObjectMapper().writeValueAsString(produto);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete(PRODUTO_API + "/" + produto.getId()).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(json);

        mvc.perform(request).andExpect(MockMvcResultMatchers.status().isNoContent());

    }
}



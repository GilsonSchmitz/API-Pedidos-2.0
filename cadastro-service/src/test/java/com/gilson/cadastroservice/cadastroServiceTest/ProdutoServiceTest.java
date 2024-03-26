package com.gilson.cadastroservice.cadastroServiceTest;

import com.gilson.cadastroservice.model.Produto;
import com.gilson.cadastroservice.service.ProdutoService;
import com.gilson.cadastroservice.utils.ProdutoUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

@ActiveProfiles("test")
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY, connection = EmbeddedDatabaseConnection.H2)
@AutoConfigureMockMvc
public class ProdutoServiceTest {

    @Autowired
    ProdutoService service;

    @Autowired
    ProdutoUtils produtoUtils;

    @Test
    public void buscarProdutoTest() {
        Produto produto = service.save(produtoUtils.retornarObjeto());
        service.buscarPor(produto.getId());

        Assertions.assertEquals(produto.getValor(), BigDecimal.valueOf(23.00));
        Assertions.assertEquals(produto.getNome(), "Gas");
    }

    @Test
    public void salvarProdutoTest() {
        Produto produto = service.save(produtoUtils.retornarObjeto());

        Assertions.assertEquals(produto.getValor(), BigDecimal.valueOf(23.00));
        Assertions.assertEquals(produto.getNome(), "Gas");
    }

    @Test
    public void atualizarProduoTest() {
        Produto produto = service.save(produtoUtils.retornarObjeto());
        produto.setNome("Aguinha");
        produto.setValor(BigDecimal.valueOf(24.4));
        Produto produtoSalvo = service.save(produto);

        Assertions.assertEquals(produtoSalvo.getValor(), BigDecimal.valueOf(24.4));
        Assertions.assertEquals(produtoSalvo.getNome(), "Aguinha");
    }

    @Test
    public void deletarProdutoTeste() {
        Produto produto = service.save(produtoUtils.retornarObjeto());
        service.delete(produto.getId());
        Assertions.assertThrows(RuntimeException.class, () -> {
            service.buscarPor(produto.getId());
        });
    }
}

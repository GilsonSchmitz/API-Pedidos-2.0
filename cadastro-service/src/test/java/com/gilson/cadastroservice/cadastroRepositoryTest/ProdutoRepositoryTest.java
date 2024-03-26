package com.gilson.cadastroservice.cadastroRepositoryTest;

import com.gilson.cadastroservice.model.Produto;
import com.gilson.cadastroservice.repository.ProdutoRepository;
import com.gilson.cadastroservice.utils.ProdutoUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
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
public class ProdutoRepositoryTest {

    @Autowired
    ProdutoUtils produtoUtils;

    @Autowired
    ProdutoRepository repository;

    @Test
    @DisplayName("Buscar Produto Repository")
    public void testBuscarPorProdutoRepository() {
        Produto produto = repository.save(produtoUtils.retornarObjeto());
        repository.findById(produto.getId()).get();

        Assertions.assertNotNull(produto.getId());
        Assertions.assertEquals(produto.getNome(), "Gas");
        Assertions.assertEquals(produto.getValor(), BigDecimal.valueOf(23.0));
    }

    @Test
    @DisplayName("Salvar Produto Repository")
    public void testSalvarProdutoRepository() {
        Produto produto = repository.save(produtoUtils.retornarObjeto());

        Assertions.assertNotNull(produto.getId());
        Assertions.assertEquals(produto.getNome(), "Gas");
        Assertions.assertEquals(produto.getValor(), BigDecimal.valueOf(23.0));
    }

    @Test
    @DisplayName("Deletar Produto Repository")
    public void testDeletarProdutoRepository() {
        Produto produto = repository.save(produtoUtils.retornarObjeto());
        repository.deleteById(produto.getId());
        Produto produtoExcluido = repository.findById(produto.getId()).orElse(null);

        Assertions.assertNull(produtoExcluido);
    }

    @Test
    @DisplayName("Atualizar Cliente Repository")
    public void testUpdatedRepository() {
        Produto produto = repository.save(produtoUtils.retornarObjeto());
        produto.setNome("Bomba Dagua");
        produto.setValor(BigDecimal.valueOf(2039.00));
        repository.save(produto);

        Assertions.assertNotNull(produto.getId());
        Assertions.assertEquals(produto.getNome(), "Bomba Dagua");
        Assertions.assertEquals(produto.getValor(), BigDecimal.valueOf(2039.00));

    }
}

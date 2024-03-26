package com.gilson.cadastroservice.cadastroServiceTest;

import com.gilson.cadastroservice.model.Endereco;
import com.gilson.cadastroservice.service.EnderecoService;
import com.gilson.cadastroservice.utils.EnderecoUtils;
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
public class EnderecoServiceTest {

    @Autowired
    EnderecoUtils enderecoUtils;

    @Autowired
    EnderecoService service;


    @Test
    public void salvarEnderecoTest() {
        Endereco endereco = service.salvarEnderecoService(enderecoUtils.retornarEndereco());

        Assertions.assertEquals(endereco.getNumero(), 123l);
        Assertions.assertEquals(endereco.getBairro(), "Santa Augusta");
        Assertions.assertEquals(endereco.getCep(), "88750-00");
    }

    @Test
    public void atualizarEnderecoTest() {
        Endereco endereco = service.salvarEnderecoService(enderecoUtils.retornarEndereco());
        endereco.setBairro("Centro");
        endereco.setCidade("Braco Do Norte");
        endereco.setNumero(2323l);

        Assertions.assertEquals(endereco.getBairro(), "Centro");
        Assertions.assertEquals(endereco.getCidade(), "Braco Do Norte");
        Assertions.assertEquals(endereco.getNumero(), 2323l);
    }

    @Test
    public void buscarEnderecoTest() {
        Endereco endereco = service.salvarEnderecoService(enderecoUtils.retornarEndereco());
        Endereco enderecoEncontrado = service.buscarPorEnderecoService(endereco.getId());

        Assertions.assertEquals(endereco.getCep(), "88750-00");
        Assertions.assertEquals(endereco.getBairro(), "Santa Augusta");
        Assertions.assertEquals(endereco.getCidade(), "Tubarao");
    }

    @Test
    public void deletarEnderecoTest() {
        Endereco endereco = service.salvarEnderecoService(enderecoUtils.retornarEndereco());
        service.deletarEnderecoService(endereco.getId());

        Assertions.assertThrows(RuntimeException.class, () -> {
            service.buscarPorEnderecoService(endereco.getId());
        });
    }
}

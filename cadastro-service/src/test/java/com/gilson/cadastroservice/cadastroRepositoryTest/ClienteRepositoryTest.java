package com.gilson.cadastroservice.cadastroRepositoryTest;

import com.gilson.cadastroservice.model.Cliente;
import com.gilson.cadastroservice.repository.ClienteRepository;
import com.gilson.cadastroservice.utils.ClienteUtils;
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

@ActiveProfiles("test")
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY, connection = EmbeddedDatabaseConnection.H2)
@AutoConfigureMockMvc
public class ClienteRepositoryTest {

    @Autowired
    ClienteRepository repository;

    @Autowired
    ClienteUtils clienteUtils;

    @Test
    @DisplayName("Buscar Cliente Repository")
    public void testBuscarPorIdRepository() {
        Cliente cliente = repository.save(clienteUtils.retornarCliente());
        repository.findById(cliente.getId());

        Assertions.assertNotNull(cliente.getId());
        Assertions.assertEquals(cliente.getNome(), "Gilson");
        Assertions.assertEquals(cliente.getCpf(), "10598454900");
    }

    @Test
    @DisplayName("Salvar Cliente Repository")
    public void testSalvarRepository() {
        Cliente cliente = repository.save(clienteUtils.retornarCliente());

        Assertions.assertNotNull(cliente.getId());
        Assertions.assertEquals(cliente.getNome(), "Gilson");
        Assertions.assertEquals(cliente.getCpf(), "10598454900");
    }

    @Test
    @DisplayName("Deletar Cliente Repository")
    public void testDeletarRepository() {
        Cliente cliente = repository.save(clienteUtils.retornarCliente());
        repository.deleteById(cliente.getId());
        Cliente clienteApagado = repository.findById(cliente.getId()).orElse(null);

        Assertions.assertNull(clienteApagado);
    }

    @Test
    @DisplayName("Atualizar Cliente Repository")
    public void testUpdateRepository() {
        Cliente cliente = repository.save(clienteUtils.retornarCliente());
        cliente.setCpf("00000000");
        cliente.setNome("Antonio");
        cliente.setEmail("toninho@gmail.com");
        repository.save(cliente);

        Cliente clienteEncontrado = repository.findById(cliente.getId()).get();

        Assertions.assertEquals(clienteEncontrado.getEmail(), "toninho@gmail.com");
        Assertions.assertEquals(clienteEncontrado.getNome(), "Antonio");
        Assertions.assertEquals(clienteEncontrado.getCpf(), "00000000");
    }


}

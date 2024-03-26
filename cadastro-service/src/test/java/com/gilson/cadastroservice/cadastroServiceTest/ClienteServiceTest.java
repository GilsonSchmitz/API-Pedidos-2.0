package com.gilson.cadastroservice.cadastroServiceTest;

import com.gilson.cadastroservice.model.Cliente;
import com.gilson.cadastroservice.service.ClienteService;
import com.gilson.cadastroservice.utils.ClienteUtils;
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
public class ClienteServiceTest {

    @Autowired
    ClienteUtils clienteUtils;

    @Autowired
    ClienteService service;

    @Test
    public void buscarPorId() {
        Cliente cliente = service.save(clienteUtils.retornarCliente());
        Cliente clienteGet = service.buscarPor(cliente.getId());

        Assertions.assertEquals(clienteGet.getCpf(), "10598454900");
        Assertions.assertEquals(clienteGet.getNome(), "Gilson");

    }

    @Test
    public void saveClienteServiceTest() {
        Cliente clienteSalvo = service.save(clienteUtils.retornarCliente());

        Assertions.assertEquals(clienteSalvo.getCpf(), "10598454900");
        Assertions.assertEquals(clienteSalvo.getNome(), "Gilson");
    }

    @Test
    public void atualizarClienteServiceTest() {
        Cliente clienteAntigo = service.save(clienteUtils.retornarCliente());
        clienteAntigo.setCpf("123123123123");
        clienteAntigo.setNome("Gustavo");
        Cliente clienteAtualizado = service.save(clienteAntigo);
        Assertions.assertEquals(clienteAtualizado.getCpf(), clienteAntigo.getCpf());
        Assertions.assertEquals(clienteAtualizado.getNome(), clienteAntigo.getNome());
    }


    @Test
    public void deletarClienteServiceTest() {
        Cliente cliente = service.save(clienteUtils.retornarCliente());
        service.delete(cliente.getId());

        Assertions.assertThrows(RuntimeException.class, () -> {
            service.buscarPor(cliente.getId());
        });
    }
}




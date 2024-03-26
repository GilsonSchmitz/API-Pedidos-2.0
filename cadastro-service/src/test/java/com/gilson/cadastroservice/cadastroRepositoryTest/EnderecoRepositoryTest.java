package com.gilson.cadastroservice.cadastroRepositoryTest;

import com.gilson.cadastroservice.model.Endereco;
import com.gilson.cadastroservice.repository.EnderecoRepository;
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

import java.util.Optional;

@ActiveProfiles("test")
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY, connection = EmbeddedDatabaseConnection.H2)
@AutoConfigureMockMvc
public class EnderecoRepositoryTest {

    @Autowired
    EnderecoRepository repository;

    @Autowired
    EnderecoUtils enderecoUtils;


    @Test
    public void salvarEnderecoTest() {

        Endereco endereco = repository.save(enderecoUtils.retornarEndereco());
        Assertions.assertNotNull(endereco.getId());
        Assertions.assertEquals(endereco.getNumero(), 123l);
        Assertions.assertEquals(endereco.getRua(), "Luiz Ghizoni");
    }

    @Test
    public void deletarEnderecoPorIdTest() {

        Endereco endereco = repository.save(enderecoUtils.retornarEndereco());
        repository.deleteById(endereco.getId());
        Optional<Endereco> pedidoDeletar = repository.findById(endereco.getId());

        Assertions.assertTrue(pedidoDeletar.isEmpty());
    }

    @Test
    public void encontrarEnderecoPorIdTest() {
        Endereco endereco = repository.save(enderecoUtils.retornarEndereco());
        Endereco enderecoEncontrado = repository.findById(endereco.getId()).get();

        Assertions.assertNotNull(enderecoEncontrado.getId());
        Assertions.assertEquals(enderecoEncontrado.getCidade(), "Tubarao");
        Assertions.assertNotNull(enderecoEncontrado.getCep(), "88750-00");
    }

    @Test
    public void atualizarEnderecoTest() {
        Endereco endereco = repository.save(enderecoUtils.retornarEndereco());
        Endereco enderecoEncontrado = repository.findById(endereco.getId()).get();
        enderecoEncontrado.setNumero(123l);
        enderecoEncontrado.setRua("Rua Luiz G");
        enderecoEncontrado.setBairro("Santa Augusta");
        Assertions.assertNotNull(enderecoEncontrado.getId());
        Assertions.assertEquals(enderecoEncontrado.getCidade(), "Tubarao");
        Assertions.assertEquals(enderecoEncontrado.getCep(), "88750-00");
        Assertions.assertEquals(enderecoEncontrado.getRua(), "Rua Luiz G");
        Assertions.assertEquals(enderecoEncontrado.getBairro(), "Santa Augusta");
    }

}

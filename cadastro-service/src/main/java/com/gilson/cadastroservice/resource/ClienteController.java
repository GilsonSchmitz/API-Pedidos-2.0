package com.gilson.cadastroservice.resource;

import com.gilson.cadastroservice.dto.ClienteDto;
import com.gilson.cadastroservice.mapper.ClienteMapper;
import com.gilson.cadastroservice.model.Cliente;
import com.gilson.cadastroservice.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Cliente Endpoint")
@RestController
@RequestMapping("cadastro-service/api/clientes/")
@Validated
public class ClienteController {

    @Autowired
    ClienteMapper clienteMapper;

    @Autowired
    ClienteService service;

    @Operation(summary = "Realiza a busca por ID de clientes", method = "GET")
    @GetMapping(value = {"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public ClienteDto obterCliente(@PathVariable Long id) {
        return clienteMapper.toDto(service.buscarPor(id));
    }

    @Operation(summary = "Realiza a criação de clientes", method = "POST")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteDto inserirCliente(@RequestBody @Valid ClienteDto dto) {
        Cliente cliente = clienteMapper.toEntity(dto);
        return clienteMapper.toDto(service.save(cliente));
    }

    @Operation(summary = "Realiza a deleção de clientes", method = "DELETE")
    @DeleteMapping(value = {"/{id}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarCliente(@PathVariable Long id) {
        service.delete(id);
    }

    @Operation(summary = "Realiza a atualização de clientes", method = "PUT")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClienteDto atualizarCliente(@PathVariable Long id, @RequestBody ClienteDto dto) {
        Cliente cliente = clienteMapper.toEntity(dto);
        return clienteMapper.toDto(service.update(id, cliente));
    }
}

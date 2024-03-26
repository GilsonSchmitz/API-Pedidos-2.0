package com.gilson.cadastroservice.resource;

import com.gilson.cadastroservice.dto.EnderecoDto;
import com.gilson.cadastroservice.mapper.EnderecoMapper;
import com.gilson.cadastroservice.model.Endereco;
import com.gilson.cadastroservice.service.EnderecoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@Tag(name = "Endereco Endpoint")
@RestController
@RequestMapping("/cadastro-service/api/enderecos/")
public class EnderecoController {


    @Autowired
    EnderecoService service;

    @Autowired
    EnderecoMapper mapper;

    @Operation(summary = "Realiza a busca por ID de endereços", method = "GET")
    @GetMapping(value = {"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public EnderecoDto obterEndereco(@PathVariable Long id) {
        return mapper.toDto(service.buscarPorEnderecoService(id));
    }

    @Operation(summary = "Realiza a criação de endereços", method = "POST")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EnderecoDto inserirEndereco(@RequestBody @Valid EnderecoDto dto) {
        Endereco endereco = mapper.toEntity(dto);
        return mapper.toDto(service.salvarEnderecoService(endereco));
    }

    @Operation(summary = "Realiza a deleção de endereços", method = "DELETE")
    @DeleteMapping(value = {"/{id}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarEndereco(@PathVariable Long id) {
        service.deletarEnderecoService(id);
    }

    @Operation(summary = "Realiza a atualização de endereços", method = "PUT")
    @PutMapping(value = {"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public EnderecoDto atualizarProduto(@PathVariable Long id, @RequestBody EnderecoDto dto) {
        Endereco endereco = mapper.toEntity(dto);
        return mapper.toDto(service.atualzarEnderecoService(id, endereco));
    }


}


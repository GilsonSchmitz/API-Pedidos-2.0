package com.gilson.pedidoservice.resource;

import com.gilson.pedidoservice.dto.PedidoDto;
import com.gilson.pedidoservice.mapper.PedidoMapper;
import com.gilson.pedidoservice.model.Pedido;
import com.gilson.pedidoservice.service.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Pedido Endpoint")
@RestController
@RequestMapping("/pedido-service/api/pedidos")
@Validated
public class PedidoController {

    @Autowired
    PedidoMapper mapper;

    @Autowired
    PedidoService service;

    @Operation(summary = "Busca um pedido por ID", method = "GET")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PedidoDto obterPedido(@PathVariable Long id) {
        Pedido pedido = service.buscarPorId(id);
        return mapper.toDto(pedido);
    }

    @Operation(summary = "Cria um novo pedido", method = "POST")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoDto inserirPedido(@RequestBody PedidoDto pedidoDto) {
        Pedido pedido = mapper.toEntity(pedidoDto);
        Pedido pedidoSalvo = service.salvar(pedido);
        return mapper.toDto(pedidoSalvo);
    }

    @Operation(summary = "Atualiza um pedido existente", method = "PUT")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PedidoDto atualizarPedido(@PathVariable Long id, @RequestBody PedidoDto pedidoDto) {
        Pedido pedido = mapper.toEntity(pedidoDto);
        Pedido pedidoAtualizado = service.atualizar(id, pedido);
        return mapper.toDto(pedidoAtualizado);
    }

    @Operation(summary = "Exclui um pedido por ID", method = "DELETE")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluirPedido(@PathVariable Long id) {
        service.deletarPorId(id);
    }
}

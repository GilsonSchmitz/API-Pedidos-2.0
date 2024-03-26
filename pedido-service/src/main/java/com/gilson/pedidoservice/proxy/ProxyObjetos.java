package com.gilson.pedidoservice.proxy;

import com.gilson.pedidoservice.dto.ClienteDto;
import com.gilson.pedidoservice.dto.EnderecoDto;
import com.gilson.pedidoservice.dto.ProdutoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

@FeignClient(name = "cadastro-service", url = "http://localhost:8000/")
public interface ProxyObjetos {

    @GetMapping(value = "cadastro-service/api/produtos/{id}")
    @ResponseStatus(HttpStatus.OK)
    ProdutoDto obterProduto(@PathVariable Long id);

    @GetMapping(value = "cadastro-service/api/clientes/{id}")
    @ResponseStatus(HttpStatus.OK)
    ClienteDto obterCliente(@PathVariable Long id);

    @GetMapping(value = "cadastro-service/api/enderecos/{id}")
    @ResponseStatus(HttpStatus.OK)
    EnderecoDto obterEndereco(@PathVariable Long id);
}

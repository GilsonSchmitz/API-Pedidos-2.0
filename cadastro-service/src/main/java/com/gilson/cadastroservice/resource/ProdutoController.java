package com.gilson.cadastroservice.resource;


import com.gilson.cadastroservice.dto.ProdutoDto;
import com.gilson.cadastroservice.mapper.ProdutoMapper;
import com.gilson.cadastroservice.model.Produto;
import com.gilson.cadastroservice.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Tag(name = "Produto Endpoint")
@RestController
@RequestMapping("/cadastro-service/api/produtos/")
@Validated
public class ProdutoController {


    @Autowired
    ProdutoMapper produtoMapper;

    @Autowired
    ProdutoService produtoService;

    @Operation(summary = "Realiza a deleção de produtos", method = "DELETE")
    @DeleteMapping(value = {"/{id}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluirProduto(@PathVariable Long id) {
        produtoService.delete(id);
    }

    @Operation(summary = "Realiza a criação de produtos", method = "POST")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoDto inserir(@RequestBody ProdutoDto produtoDto) {
        Produto produto = produtoMapper.toEntity(produtoDto);
        return produtoMapper.toDto(produtoService.save(produto));
    }

    @Operation(summary = "Realiza a busca por ID de produtos", method = "GET")
    @GetMapping(value = {"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public ProdutoDto obterProduto(@PathVariable Long id) {
        return produtoMapper.toDto(produtoService.buscarPor(id));
    }

    @Operation(summary = "Realiza a atualização de produtos", method = "PUT")
    @PutMapping(value = {"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public ProdutoDto atualizarProduto(@PathVariable Long id, @RequestBody ProdutoDto produtoDto) {
        Produto produto = produtoMapper.toEntity(produtoDto);
        return produtoMapper.toDto(produtoService.update(id, produto));
    }
}



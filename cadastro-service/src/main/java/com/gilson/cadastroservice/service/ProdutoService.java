package com.gilson.cadastroservice.service;

import com.gilson.cadastroservice.model.Produto;
import com.gilson.cadastroservice.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    ProdutoRepository repository;

    @Transactional
    public List<Produto> findAll() {
        return repository.findAll();
    }

    @Transactional
    public Produto buscarPor(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    @Transactional
    public void validarValorNulo(Produto produto) {
        if (produto.getValor() == null) {
            throw new RuntimeException("O valor do produto não pode estar nulo");
        }
    }

    @Transactional
    public Produto save(Produto produto) {
        validarValorNulo(produto);
        return repository.save(produto);
    }

    @Transactional
    public Produto update(Long id, Produto produto) {
        Produto produtoFinded = repository.findById(id).orElseThrow(() -> new RuntimeException("Não encontrado"));
        produtoFinded.setNome(produto.getNome());
        produtoFinded.setValor(produto.getValor());
        return repository.save(produto);
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }


}
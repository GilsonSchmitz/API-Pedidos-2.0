package com.gilson.cadastroservice.service;

import com.gilson.cadastroservice.model.Endereco;
import com.gilson.cadastroservice.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoService {

    @Autowired
    EnderecoRepository repository;

    public List<Endereco> findAll() {
        return repository.findAll();
    }

    public Endereco salvarEnderecoService(Endereco endereco) {
        return repository.save(endereco);
    }

    public Endereco buscarPorEnderecoService(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Registro não encontrado!"));
    }

    public void deletarEnderecoService(Long id) {
        repository.deleteById(id);
    }

    public Endereco atualzarEnderecoService(Long id, Endereco enderecoAtualizado) {
        Endereco enderecoExistente = repository.save(enderecoAtualizado);
        enderecoExistente.setCep(enderecoAtualizado.getCep());
        enderecoExistente.setCidade(enderecoAtualizado.getCidade());
        enderecoExistente.setBairro(enderecoAtualizado.getBairro());
        enderecoExistente.setRua(enderecoAtualizado.getRua());
        enderecoExistente.setNumero(enderecoAtualizado.getNumero());
        enderecoExistente.setComplemento(enderecoAtualizado.getComplemento());
        return repository.save(enderecoExistente);
    }


}


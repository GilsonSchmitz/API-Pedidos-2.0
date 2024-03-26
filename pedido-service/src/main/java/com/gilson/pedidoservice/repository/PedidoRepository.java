package com.gilson.pedidoservice.repository;


import com.gilson.pedidoservice.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    @Override
    @Query(value = "SELECT p FROM Pedido p" + " JOIN FETCH p.listaItens" + " WHERE p.id = :id")
    Optional<Pedido> findById(Long id);
}
package com.gilson.pedidoservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component
public class ProdutoDto {

    private Long id;

    private String nome;

    private BigDecimal valor;
}

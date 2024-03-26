package com.gilson.pedidoservice.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Component
public class ItemPedidoDto implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;

    @NotEmpty
    private PedidoDto pedido;

    @NotEmpty
    private Long idProduto;

    @NotEmpty
    private Long quantidade;

    @NotEmpty
    private BigDecimal valor;

    private Boolean opcaoGarantia;

}


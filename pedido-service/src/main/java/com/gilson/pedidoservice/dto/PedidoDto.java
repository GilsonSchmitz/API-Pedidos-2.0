package com.gilson.pedidoservice.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Component
public class PedidoDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long idCliente;

    private List<ItemPedidoDto> listaItens;

    @NotEmpty
    private LocalDateTime dataPedido;

    private String cupomDesconto;

    private BigDecimal valorFrete;

    private BigDecimal valorTotal;

    private String formaPgto;

    private String codigoRastreio;

    @NotEmpty
    private Long idEndereco;

}

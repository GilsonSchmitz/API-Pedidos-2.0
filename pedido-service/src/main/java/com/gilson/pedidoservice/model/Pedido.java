package com.gilson.pedidoservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "pedidos")
public class Pedido implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long idCliente;

    @NotNull

    @Builder.Default
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "pedido", fetch = FetchType.LAZY)
    private List<ItemPedido> listaItens = new ArrayList<>();

    @NotNull
    private Long idEndereco;

    @NotNull
    private LocalDateTime dataPedido;

    private String cupomDesconto;

    private BigDecimal valorFrete;

    @NotNull
    private BigDecimal valorTotal;

    private String formaPgto;

    private String codigoRastreio;


}
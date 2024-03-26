package com.gilson.pedidoservice.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "itempedido")
public class ItemPedido implements Serializable {

    private static final long serialVersionUID = 1l;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pedido", referencedColumnName = "id", nullable = false)
    @JsonIgnoreProperties("listaItens")
    private Pedido pedido;

    @NotNull
    private Long idProduto;

    @NotNull
    private Long quantidade;

    @NotNull
    private BigDecimal valor;

    private Boolean opcaoGarantia;


}
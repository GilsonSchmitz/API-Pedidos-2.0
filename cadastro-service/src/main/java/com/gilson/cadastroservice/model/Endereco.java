package com.gilson.cadastroservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "endereco")
public class Endereco implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cep;

    private String cidade;

    private String bairro;

    private String rua;

    private Long numero;

    private String complemento;

}
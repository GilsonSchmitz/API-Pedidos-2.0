package com.gilson.pedidoservice.mapper;


import com.gilson.pedidoservice.dto.PedidoDto;
import com.gilson.pedidoservice.model.Pedido;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PedidoMapper {

    PedidoDto toDto(Pedido pedido);

    @InheritInverseConfiguration
    Pedido toEntity(PedidoDto pedidoDto);


}

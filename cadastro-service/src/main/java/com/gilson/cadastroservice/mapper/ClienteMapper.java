package com.gilson.cadastroservice.mapper;

import com.gilson.cadastroservice.dto.ClienteDto;
import com.gilson.cadastroservice.model.Cliente;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface ClienteMapper {


    ClienteDto toDto(Cliente cliente);

    @InheritInverseConfiguration
    Cliente toEntity(ClienteDto clienteDto);

}

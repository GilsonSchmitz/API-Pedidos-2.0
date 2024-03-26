package com.gilson.cadastroservice.mapper;

import com.gilson.cadastroservice.dto.EnderecoDto;
import com.gilson.cadastroservice.model.Endereco;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EnderecoMapper {

    EnderecoDto toDto(Endereco endereco);

    @InheritInverseConfiguration
    Endereco toEntity(EnderecoDto enderecoDto);


}
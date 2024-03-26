package com.gilson.cadastroservice.mapper;

import com.gilson.cadastroservice.dto.ProdutoDto;
import com.gilson.cadastroservice.model.Produto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {

    ProdutoDto toDto(Produto produto);

    @InheritInverseConfiguration
    Produto toEntity(ProdutoDto produtoDto);

}
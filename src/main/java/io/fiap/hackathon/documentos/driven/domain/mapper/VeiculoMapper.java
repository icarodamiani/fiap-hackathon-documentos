package io.fiap.hackathon.documentos.driven.domain.mapper;

import io.fiap.hackathon.documentos.driven.domain.Veiculo;
import io.fiap.hackathon.documentos.driver.controller.dto.VeiculoDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VeiculoMapper extends BaseMapper<VeiculoDTO, Veiculo> {
}

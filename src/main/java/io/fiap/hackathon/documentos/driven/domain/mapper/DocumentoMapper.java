package io.fiap.hackathon.documentos.driven.domain.mapper;

import io.fiap.hackathon.documentos.driven.domain.Documento;
import io.fiap.hackathon.documentos.driver.controller.dto.DocumentoDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {DocumentoMapper.class, PessoaMapper.class, VeiculoMapper.class})
public interface DocumentoMapper extends BaseMapper<DocumentoDTO, Documento> {
}

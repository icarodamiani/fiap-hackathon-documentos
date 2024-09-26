package io.fiap.hackathon.documentos.driven.domain.mapper;

import io.fiap.hackathon.documentos.driven.domain.DocumentoPessoa;
import io.fiap.hackathon.documentos.driver.controller.dto.DocumentoPessoaDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {DocumentoPessoaMapper.class})
public interface DocumentoPessoaMapper extends BaseMapper<DocumentoPessoaDTO, DocumentoPessoa> {
}

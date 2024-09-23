package io.fiap.hackathon.veiculos.driven.domain.mapper;

import io.fiap.hackathon.veiculos.driven.domain.DocumentoPessoa;
import io.fiap.hackathon.veiculos.driver.controller.dto.DocumentoPessoaDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {DocumentoPessoaMapper.class})
public interface DocumentoPessoaMapper extends BaseMapper<DocumentoPessoaDTO, DocumentoPessoa> {
}

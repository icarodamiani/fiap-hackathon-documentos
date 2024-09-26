package io.fiap.hackathon.veiculos.driven.domain.mapper;

import io.fiap.hackathon.veiculos.driven.domain.Pessoa;
import io.fiap.hackathon.veiculos.driver.controller.dto.PessoaDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {DocumentoPessoaMapper.class})
public interface PessoaMapper extends BaseMapper<PessoaDTO, Pessoa> {
}

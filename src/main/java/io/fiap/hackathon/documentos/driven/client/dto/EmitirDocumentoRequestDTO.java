package io.fiap.hackathon.documentos.driven.client.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.fiap.hackathon.documentos.driven.domain.Pessoa;
import io.fiap.hackathon.documentos.driven.domain.Veiculo;
import org.immutables.value.Value;

@JsonSerialize(as = ImmutableEmitirDocumentoRequestDTO.class)
@JsonDeserialize(as = ImmutableEmitirDocumentoRequestDTO.class)
@Value.Immutable
@Value.Style(privateNoargConstructor = true, jdkOnly = true)
public abstract class EmitirDocumentoRequestDTO {
    public abstract String getId();
    public abstract String getTipo();
    public abstract String getOrgao();
    public abstract Veiculo getVeiculo();
    public abstract Pessoa getPessoa();
}
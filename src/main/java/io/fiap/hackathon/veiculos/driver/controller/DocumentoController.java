package io.fiap.hackathon.veiculos.driver.controller;

import io.fiap.hackathon.veiculos.driven.domain.mapper.DocumentoMapper;
import io.fiap.hackathon.veiculos.driven.service.DocumentoService;
import io.fiap.hackathon.veiculos.driver.controller.dto.DetranResponseDTO;
import io.fiap.hackathon.veiculos.driver.controller.dto.DocumentoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SecurityRequirement(name = "OAuth2")
@RestController
@RequestMapping(value = "/documentos", produces = MediaType.APPLICATION_JSON_VALUE)
public class DocumentoController {

    private final DocumentoService documentoService;
    private final DocumentoMapper mapper;

    public DocumentoController(DocumentoService documentoService, DocumentoMapper mapper) {
        this.documentoService = documentoService;
        this.mapper = mapper;
    }

    @PostMapping("/detran/receive")
    @Operation(description = "Recebe respostas da integração junto ao orgão emissor")
    public Mono<Void> receive(@RequestBody DetranResponseDTO detranResponseDTO) {
        return documentoService.updateEmitido(detranResponseDTO.getId(), detranResponseDTO.getEmitido());
    }


    @GetMapping
    @Operation(description = "Busca todos os documentos")
    public Flux<DocumentoDTO> fetch(@RequestParam Boolean emitido) {
        return documentoService.fetch(emitido)
            .map(mapper::dtoFromDomain);
    }
}

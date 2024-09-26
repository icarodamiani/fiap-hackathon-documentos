package io.fiap.hackathon.documentos.driven.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.fiap.hackathon.documentos.driven.client.DetranClient;
import io.fiap.hackathon.documentos.driven.client.dto.EmitirDocumentoRequestDTO;
import io.fiap.hackathon.documentos.driven.domain.Documento;
import io.fiap.hackathon.documentos.driven.domain.ImmutableDocumento;
import io.fiap.hackathon.documentos.driven.exception.BusinessException;
import io.fiap.hackathon.documentos.driven.port.MessagingPort;
import io.fiap.hackathon.documentos.driven.repository.DocumentoRepository;
import io.vavr.CheckedFunction1;
import io.vavr.Function1;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.services.sqs.model.Message;

@Service
public class DocumentoService {
    private final String queue;
    private final DocumentoRepository repository;
    private final DetranClient detranClient;
    private final MessagingPort messagingPort;
    private final ObjectMapper objectMapper;

    public DocumentoService(@Value("${aws.sqs.documentosEmitir.queue}")
                            String queue,
                            DocumentoRepository repository,
                            DetranClient detranClient,
                            MessagingPort messagingPort,
                            ObjectMapper objectMapper) {
        this.repository = repository;
        this.detranClient = detranClient;
        this.queue = queue;
        this.messagingPort = messagingPort;
        this.objectMapper = objectMapper;
    }

    public Mono<Void> save(Documento pessoa) {
        return repository.save(pessoa);
    }

    public Mono<Void> updateEmitido(String id, Boolean emitido) {
        return repository.findById(id)
            .switchIfEmpty(Mono.defer(() -> Mono.error(new BusinessException("Documento não encontrado."))))
            .map(documento -> ImmutableDocumento.copyOf(documento).withEmitido(emitido))
            .flatMap(repository::save);
            //.flatMap(notify()); TODO notificar
    }

    public Mono<Void> deleteById(String id) {
        return repository.deleteById(id);
    }

    public Flux<Documento> fetch(Boolean emitido) {
        return repository.fetch(emitido);
    }

    public Mono<Documento> findById(String id) {
        return repository.findById(id);
    }


    public Flux<Message> handleEmitirDocumento() {
        return messagingPort.read(queue, handle(), readEvent());
    }

    private CheckedFunction1<Message, EmitirDocumentoRequestDTO> readEvent() {
        return message -> objectMapper.readValue(message.body(), EmitirDocumentoRequestDTO.class);
    }

    private Function1<EmitirDocumentoRequestDTO, Mono<EmitirDocumentoRequestDTO>> handle() {
        return request -> detranClient.emitirDetran(request.getId())
            .then(Mono.just(request));
    }
}

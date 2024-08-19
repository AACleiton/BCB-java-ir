package com.me.personal.Specification;

import com.me.personal.domains.HistoricoEnvioMensagem;
import com.me.personal.enumerated.TipoEnvioMensagem;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import static java.util.Optional.ofNullable;

@Component
public class HistoricoEnvioMensagemSpecification implements SpecificationDefault<HistoricoEnvioMensagem> {

    private Specification<HistoricoEnvioMensagem> dataEnvio(LocalDateTime dataHoraInicio, LocalDateTime dataHoraFim) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.between(root.get("dataHoraEnvio"), dataHoraInicio, dataHoraFim);
    }

    private Specification<HistoricoEnvioMensagem> tipo(TipoEnvioMensagem tipo) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("tipoEnvio"), tipo);
    }

    private Specification<HistoricoEnvioMensagem> cliente(Long clienteId) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.join("cliente").get("id"), clienteId);
    }

    public Specification<HistoricoEnvioMensagem> filter(String search, LocalDateTime dataHoraInicio, LocalDateTime dataHoraFim,
                                                        TipoEnvioMensagem tipo, Long clienteId) {
        var builder = this.builder();

        builder.and(this.dataEnvio(dataHoraInicio, dataHoraFim));

        ofNullable(search).map(s -> this.like("telefoneDestino", s)).ifPresent(builder::and);
        ofNullable(tipo).map(this::tipo).ifPresent(builder::and);
        ofNullable(clienteId).map(this::cliente).ifPresent(builder::and);

        return builder.build();

    }

}

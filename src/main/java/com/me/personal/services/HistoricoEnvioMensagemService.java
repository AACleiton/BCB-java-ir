package com.me.personal.services;

import com.me.personal.Specification.HistoricoEnvioMensagemSpecification;
import com.me.personal.domains.HistoricoEnvioMensagem;
import com.me.personal.enumerated.TipoEnvioMensagem;
import com.me.personal.repository.HistoricoEnvioMensagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;

import static java.util.Optional.ofNullable;

@Service
public class HistoricoEnvioMensagemService {

    private final HistoricoEnvioMensagemRepository historicoEnvioMensagemRepository;
    private final HistoricoEnvioMensagemSpecification historicoEnvioMensagemSpecification;
    private final ClienteService clienteService;
    private final PlanoService planoService;

    @Autowired
    public HistoricoEnvioMensagemService(HistoricoEnvioMensagemRepository historicoEnvioMensagemRepository, HistoricoEnvioMensagemSpecification historicoEnvioMensagemSpecification, ClienteService clienteService, PlanoService planoService) {
        this.historicoEnvioMensagemRepository = historicoEnvioMensagemRepository;
        this.historicoEnvioMensagemSpecification = historicoEnvioMensagemSpecification;
        this.clienteService = clienteService;
        this.planoService = planoService;
    }

    @Transactional(readOnly = true)
    public Page<HistoricoEnvioMensagem> findAll(String search, LocalDateTime dataHoraInicio, LocalDateTime dataHoraFim,
                                                TipoEnvioMensagem tipo, Long clienteId, Pageable pageable) {

        var dataHoraInicioValido = ofNullable(dataHoraInicio)
                .orElse(LocalDate.now().with(TemporalAdjusters.firstDayOfMonth()).atStartOfDay());
        var dataHoraFimValido = ofNullable(dataHoraFim)
                .orElse(LocalDate.now().with(TemporalAdjusters.lastDayOfMonth()).atTime(LocalTime.MAX));

        return this.historicoEnvioMensagemRepository
                .findAll(this.historicoEnvioMensagemSpecification
                        .filter(search, dataHoraInicioValido, dataHoraFimValido, tipo, clienteId), pageable);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void enviarMensagem(Long clienteId, TipoEnvioMensagem tipo, String texto) {
        try {
            var clienteRecuperado = this.clienteService.findById(clienteId);

            var historicoEnvioMensagem = new HistoricoEnvioMensagem(
                    LocalDateTime.now(),
                    clienteRecuperado.getTelefone(),
                    tipo,
                    clienteRecuperado,
                    texto
            );

            this.enviarMensagem(historicoEnvioMensagem);
            this.clienteService.processaEnvioMensagem(clienteRecuperado);

            this.historicoEnvioMensagemRepository.save(historicoEnvioMensagem);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao enviar mensagem");
        }
    }

    private void enviarMensagem(HistoricoEnvioMensagem historicoEnvioMensagem) {
        System.out.println("Enviando mensagem...");
        System.out.println("Mensagem enviada! :D");
    }
}

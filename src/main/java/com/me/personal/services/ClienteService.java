package com.me.personal.services;

import com.me.personal.Specification.ClienteSpecification;
import com.me.personal.domains.Cliente;
import com.me.personal.domains.ClientePlano;
import com.me.personal.enumerated.TipoPlano;
import com.me.personal.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteSpecification clienteSpecification;
    private final PlanoService planoService;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository, ClienteSpecification clienteSpecification, PlanoService planoService) {
        this.clienteRepository = clienteRepository;
        this.clienteSpecification = clienteSpecification;
        this.planoService = planoService;
    }

    @Transactional(readOnly = true)
    public Cliente findById(Long id) {
        return this.clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    }

    @Transactional(readOnly = true)
    public Page<Cliente> findAll(String search, Pageable pageable) {
        return this.clienteRepository.findAll(this.clienteSpecification.filters(search), pageable);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Cliente create(Cliente cliente) {
        return this.save(cliente);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Cliente update(Cliente cliente) {
        var clienteRecuperado = this.findById(cliente.getId());

        clienteRecuperado.atualizaDados(cliente);

        return this.save(clienteRecuperado);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Cliente save(Cliente cliente) {
        return this.clienteRepository.save(cliente);
    }

    @Transactional(readOnly = true)
    public BigDecimal consultarSaldoCliente(Long clienteId) {
        var clienteRecuperado = this.findById(clienteId);

        return clienteRecuperado.getPlanoAtualOpt()
                .map(ClientePlano::getSaldoCredito)
                .orElseThrow(() -> new RuntimeException("Plano não encontrado"));
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void adicionarCreditosCliente(Long id, BigDecimal valor) {
        var clienteRecuperado = this.findById(id);

        clienteRecuperado.getPlanoAtualOpt()
                .filter(p -> p.getPlano().getTipoPlano().equals(TipoPlano.PRE_PAGO))
                .ifPresent(p -> p.adicionarCreditos(valor));
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void alterarLimiteSaldoCliente(Long id, BigDecimal valor) {
        var clienteRecuperado = this.findById(id);

        clienteRecuperado.getPlanoAtualOpt()
                .filter(p -> p.getPlano().getTipoPlano().equals(TipoPlano.POS_PAGO))
                .ifPresent(p -> p.setSaldoCredito(valor));

        this.save(clienteRecuperado);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void alterarPlanoCliente(Long id, Long planoId, BigDecimal saldoInicial) {
        var clienteRecuperado = this.findById(id);
        var planoRecuperado = this.planoService.findById(planoId);

        clienteRecuperado.vincularPlano(planoRecuperado, saldoInicial);

        this.save(clienteRecuperado);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void processaEnvioMensagem(Cliente cliente) {
        var planoAtual = cliente.getPlanoAtualOpt().orElseThrow(() -> new RuntimeException("Plano não encontrado"));

        cliente.getPlanoAtual().getPlano().getTipoPlano().processaEnvioMensagem(planoAtual, planoAtual.getPlano().getValorPorMensagem());

        this.save(cliente);
    }
}

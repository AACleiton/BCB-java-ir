package com.me.personal.controllers;

import com.me.personal.DTO.PageableDTO;
import com.me.personal.domains.Cliente;
import com.me.personal.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController("/api/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping("/create")
    public Cliente create(@RequestBody Cliente cliente) {
        return this.clienteService.create(cliente);
    }

    @PutMapping("/update")
    public void update(@RequestBody Cliente cliente) {
        this.clienteService.update(cliente);
    }

    @GetMapping("/find-by-id")
    public Cliente findById(@RequestParam(name = "id") Long id) {
        return this.clienteService.findById(id);
    }

    @GetMapping("/find-all")
    public Page<Cliente> findAll(@RequestParam(name = "search", required = false) String search,
                                 @RequestParam(name = "pageableDTO") PageableDTO pageable) {
        return this.clienteService.findAll(search, pageable.getPageable());
    }

    @GetMapping("/consultar-saldo-cliente")
    public BigDecimal consultarSaldoCliente(@RequestParam(name = "clienteId") Long clienteId) {
        return this.clienteService.consultarSaldoCliente(clienteId);
    }

    @PutMapping("/adicionar-creditos-cliente")
    public void adicionarCreditosCliente(@RequestParam(name = "id") Long id, @RequestParam(name = "valor") BigDecimal valor) {
        this.clienteService.adicionarCreditosCliente(id, valor);
    }

    @PutMapping("/alterar-limite-saldo-cliente")
    public void alterarLimiteSaldoCliente(@RequestParam(name = "id") Long id, @RequestParam(name = "valor") BigDecimal valor) {
        this.clienteService.alterarLimiteSaldoCliente(id, valor);
    }

    @PutMapping("/alterar-plano-cliente")
    public void alterarPlanoCliente(@RequestParam(name = "id") Long id, @RequestParam(name = "planoId") Long planoId, @RequestParam(name = "valor") BigDecimal valor) {
        this.clienteService.alterarPlanoCliente(id, planoId, valor);
    }
}

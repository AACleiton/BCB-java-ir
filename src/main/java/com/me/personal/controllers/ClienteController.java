package com.me.personal.controllers;

import com.me.personal.DTO.*;
import com.me.personal.domains.Cliente;
import com.me.personal.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping("/create")
    public void create(@RequestBody Cliente cliente) {
        this.clienteService.create(cliente);
    }

    @PutMapping("/update")
    public void update(@RequestBody Cliente cliente) {
        this.clienteService.update(cliente);
    }

    @GetMapping("/find-by-id")
    public ResponseEntity<Cliente> findById(@RequestBody Long id) {
        try {
            return ResponseEntity.ok().body(this.clienteService.findById(id));
        }catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/find-all")
    public ResponseEntity<Page<Cliente>> findAll(@RequestBody FindAllClienteDTO findAllClienteDTO) {
        return ResponseEntity.ok().body(this.clienteService.findAll(findAllClienteDTO.getSearch(), findAllClienteDTO.getPageable().getPageable()));
    }

    @GetMapping("/consultar-saldo-cliente")
    public ResponseEntity<BigDecimal> consultarSaldoCliente(@RequestBody Long clienteId) {
        try {
            return ResponseEntity.ok().body(this.clienteService.consultarSaldoCliente(clienteId));
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/adicionar-creditos-cliente")
    public void adicionarCreditosCliente(@RequestBody AdicionarCreditosClienteDTO adicionarCreditosClienteDTO) {
        this.clienteService.adicionarCreditosCliente(adicionarCreditosClienteDTO.getClienteId(), adicionarCreditosClienteDTO.getValor());
    }

    @PutMapping("/alterar-limite-saldo-cliente")
    public void alterarLimiteSaldoCliente(@RequestBody AlterarLimiteSaldoClienteDTO alterarLimiteSaldoClienteDTO) {
        this.clienteService.alterarLimiteSaldoCliente(alterarLimiteSaldoClienteDTO.getClienteId(), alterarLimiteSaldoClienteDTO.getLimite());
    }

    @PutMapping("/alterar-plano-cliente")
    public void alterarPlanoCliente(@RequestBody AlterarPlanoClienteDTO alterarPlanoClienteDTO) {
        this.clienteService.alterarPlanoCliente(alterarPlanoClienteDTO.getClienteId(),
                alterarPlanoClienteDTO.getPlanoId(), alterarPlanoClienteDTO.getSaldoInicial());
    }
}

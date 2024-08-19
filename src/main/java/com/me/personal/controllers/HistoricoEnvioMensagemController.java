package com.me.personal.controllers;

import com.me.personal.DTO.EnvioMensagemDTO;
import com.me.personal.enumerated.TipoEnvioMensagem;
import com.me.personal.services.HistoricoEnvioMensagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/envio-mensagem")
public class HistoricoEnvioMensagemController {

    private final HistoricoEnvioMensagemService historicoEnvioMensagemService;

    @Autowired
    public HistoricoEnvioMensagemController(HistoricoEnvioMensagemService historicoEnvioMensagemService) {
        this.historicoEnvioMensagemService = historicoEnvioMensagemService;
    }

    @PostMapping("/enviar-mensagem")
    public void enviarMensagem(@RequestBody EnvioMensagemDTO envioMensagemDTO) {
        this.historicoEnvioMensagemService.enviarMensagem(envioMensagemDTO.getClienteId(), envioMensagemDTO.getTipo(), envioMensagemDTO.getTexto());
    }
}

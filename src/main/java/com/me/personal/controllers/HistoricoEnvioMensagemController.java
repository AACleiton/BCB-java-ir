package com.me.personal.controllers;

import com.me.personal.domains.HistoricoEnvioMensagem;
import com.me.personal.enumerated.TipoEnvioMensagem;
import com.me.personal.services.HistoricoEnvioMensagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("api/envio-mensagem")
public class HistoricoEnvioMensagemController {

    private final HistoricoEnvioMensagemService historicoEnvioMensagemService;

    @Autowired
    public HistoricoEnvioMensagemController(HistoricoEnvioMensagemService historicoEnvioMensagemService) {
        this.historicoEnvioMensagemService = historicoEnvioMensagemService;
    }

    @PostMapping("/enviar-mensagem")
    public void enviarMensagem(@RequestParam("texto") String texto,
                               @RequestParam("tipo") TipoEnvioMensagem tipo,
                               @RequestParam("clienteId") Long clienteId) {
        this.historicoEnvioMensagemService.enviarMensagem(clienteId, tipo, texto);
    }
}

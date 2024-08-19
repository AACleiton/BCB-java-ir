package com.me.personal.DTO;

import com.me.personal.enumerated.TipoEnvioMensagem;
import org.springframework.web.bind.annotation.RequestParam;

public class EnvioMensagemDTO {
    private String texto;
    private TipoEnvioMensagem tipo;
    private Long clienteId;

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public TipoEnvioMensagem getTipo() {
        return tipo;
    }

    public void setTipo(TipoEnvioMensagem tipo) {
        this.tipo = tipo;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }
}

package com.me.personal.enumerated;

import com.me.personal.domains.ClientePlano;
import com.me.personal.processors.TipoPlanoProcessor;

import java.math.BigDecimal;

public enum TipoPlano {

    POS_PAGO, PRE_PAGO;

    private TipoPlanoProcessor processor;

    public void setProcessor(TipoPlanoProcessor processor) {
        this.processor = processor;
    }

    public void processaEnvioMensagem(ClientePlano clientePlano, BigDecimal valor) {
        if (processor == null) {
            throw new RuntimeException("Processo de envio de mensagem não definido para o plano: " + this);
        }

        processor.processaEnvioMensagem(clientePlano, valor);
    }

}

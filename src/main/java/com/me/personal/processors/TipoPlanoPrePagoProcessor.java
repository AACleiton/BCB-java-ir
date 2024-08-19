package com.me.personal.processors;

import com.me.personal.domains.ClientePlano;
import com.me.personal.enumerated.TipoPlano;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.MathContext;

@Component
public class TipoPlanoPrePagoProcessor implements TipoPlanoProcessor {

    @PostConstruct
    public void init() {
        TipoPlano.PRE_PAGO.setProcessor(this);
    }

    @Override
    public void processaEnvioMensagem(ClientePlano clientePlano, BigDecimal valor) {
        clientePlano.setSaldoCredito(clientePlano.getSaldoCredito().subtract(valor, MathContext.DECIMAL32));

        if(clientePlano.getSaldoCredito().compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("Saldo insuficiente");
        }
    }
}

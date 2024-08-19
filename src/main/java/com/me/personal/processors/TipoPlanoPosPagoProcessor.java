package com.me.personal.processors;

import com.me.personal.domains.ClientePlano;
import com.me.personal.enumerated.TipoPlano;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.MathContext;

@Component
public class TipoPlanoPosPagoProcessor implements TipoPlanoProcessor {

    @PostConstruct
    public void init() {
        TipoPlano.POS_PAGO.setProcessor(this);
    }

    @Override
    public void processaEnvioMensagem(ClientePlano clientePlano, BigDecimal valor) {
        clientePlano.setSaldoCreditoUtilizado(clientePlano.getSaldoCreditoUtilizado().add(valor, MathContext.DECIMAL32));

        if(clientePlano.getSaldoCreditoUtilizado().compareTo(clientePlano.getSaldoCredito()) > 0) {
            throw new RuntimeException("Limite de credito ultrapassado");
        }
    }
}

package com.me.personal.processors;

import com.me.personal.domains.ClientePlano;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public interface TipoPlanoProcessor {
    void processaEnvioMensagem(ClientePlano clientePlano, BigDecimal valor);
}

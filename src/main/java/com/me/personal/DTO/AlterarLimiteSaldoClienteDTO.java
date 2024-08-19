package com.me.personal.DTO;

import java.math.BigDecimal;

public class AlterarLimiteSaldoClienteDTO {

    private Long clienteId;
    private BigDecimal limite;

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public BigDecimal getLimite() {
        return limite;
    }

    public void setLimite(BigDecimal limite) {
        this.limite = limite;
    }
}

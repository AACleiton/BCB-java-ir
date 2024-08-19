package com.me.personal.DTO;

import java.math.BigDecimal;

public class AdicionarCreditosClienteDTO {

    private Long clienteId;
    private BigDecimal valor;

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}

package com.me.personal.domains;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;

@Entity
@Table(name = "cliente_plano")
public class ClientePlano {

    @EmbeddedId
    private ClientePlanoId clientePlanoId;

    @Column(name = "atual")
    private boolean atual = false;

    @Column(name = "saldo_credito")
    private BigDecimal saldoCredito = new BigDecimal(0);

    @Column(name = "saldo_credito_utilizado")
    private BigDecimal saldoCreditoUtilizado = new BigDecimal(0);

    public ClientePlano() {
        clientePlanoId = new ClientePlanoId();
    }

    public ClientePlano(Cliente cliente, Plano plano) {
        this.clientePlanoId = new ClientePlanoId(cliente, plano);
    }

    public Cliente getCliente() {
        return clientePlanoId.getCliente();
    }

    public void setCliente(Cliente cliente) {
        this.clientePlanoId.setCliente(cliente);
    }

    public Plano getPlano() {
        return clientePlanoId.getPlano();
    }

    public void setPlano(Plano plano) {
        this.clientePlanoId.setPlano(plano);
    }

    public BigDecimal getSaldoCredito() {
        return saldoCredito;
    }

    public void setSaldoCredito(BigDecimal saldoCredito) {
        this.saldoCredito = saldoCredito;
    }

    public BigDecimal getSaldoCreditoUtilizado() {
        return saldoCreditoUtilizado;
    }

    public void setSaldoCreditoUtilizado(BigDecimal saldoCreditoUtilizado) {
        this.saldoCreditoUtilizado = saldoCreditoUtilizado;
    }

    public Boolean isAtual() {
        return atual;
    }

    public void setAtual(Boolean atual) {
        this.atual = Optional.ofNullable(atual).orElse(false);
    }

    public void adicionarCreditos(BigDecimal valor) {
        this.saldoCredito = this.saldoCredito.add(valor);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientePlano that = (ClientePlano) o;
        return Objects.equals(clientePlanoId, that.clientePlanoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientePlanoId);
    }

    @Override
    public String toString() {
        return "ClientePlano{" +
                "clientePlanoId=" + clientePlanoId +
                ", saldoCredito=" + saldoCredito +
                ", saldoCreditoUtilizado=" + saldoCreditoUtilizado +
                '}';
    }
}

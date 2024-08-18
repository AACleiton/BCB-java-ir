package com.me.personal.domains;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;

import java.util.Objects;

import static jakarta.persistence.FetchType.LAZY;

@Embeddable
public class ClientePlanoId {

    @ManyToOne(fetch = LAZY)
    @Column(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne(fetch = LAZY)
    @Column(name = "plano_id")
    private Plano plano;

    public ClientePlanoId() {
    }

    public ClientePlanoId(Cliente cliente, Plano plano) {
        this.cliente = cliente;
        this.plano = plano;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Plano getPlano() {
        return plano;
    }

    public void setPlano(Plano plano) {
        this.plano = plano;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientePlanoId that = (ClientePlanoId) o;
        return Objects.equals(cliente, that.cliente) && Objects.equals(plano, that.plano);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cliente, plano);
    }

    @Override
    public String toString() {
        return "ClientePlanoId{" +
                "cliente=" + cliente +
                ", plano=" + plano +
                '}';
    }
}

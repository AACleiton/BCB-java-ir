package com.me.personal.DTO;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class Order{
    private String campo;
    private Sort.Direction direcao = Sort.Direction.ASC;

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

    public Sort.Direction getDirecao() {
        return direcao;
    }

    public void setDirecao(Sort.Direction direcao) {
        this.direcao = direcao;
    }

    public Sort.Order getOrder(){
        return new Sort.Order(direcao, campo);
    }

    @Override
    public String toString() {
        return "Order{" +
                "campo='" + campo + '\'' +
                ", direcao=" + direcao +
                '}';
    }
}

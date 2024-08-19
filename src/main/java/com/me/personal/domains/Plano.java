package com.me.personal.domains;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.me.personal.enumerated.TipoPlano;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "plano")
public class Plano implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private static final String sequence = "plano_id_seq";

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = sequence, strategy = GenerationType.SEQUENCE)
    @JsonProperty("id")
    private Long id;

    @Column(name = "nome")
    @JsonProperty("nome")
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_plano")
    @JsonProperty("tipoPlano")
    private TipoPlano tipoPlano;

    @Column(name = "valor_por_mensagem")
    @JsonProperty("valorPorMensagem")
    private BigDecimal valorPorMensagem = new BigDecimal("0.25");

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoPlano getTipoPlano() {
        return tipoPlano;
    }

    public void setTipoPlano(TipoPlano tipoPlano) {
        this.tipoPlano = tipoPlano;
    }

    public BigDecimal getValorPorMensagem() {
        return valorPorMensagem;
    }

    public void setValorPorMensagem(BigDecimal valorPorMensagem) {
        this.valorPorMensagem = valorPorMensagem;
    }

    public void atualizaDados(Plano plano) {
        this.setNome(plano.getNome());
        this.setTipoPlano(plano.getTipoPlano());
        this.setValorPorMensagem(plano.getValorPorMensagem());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plano plano = (Plano) o;
        return Objects.equals(id, plano.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Plano{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", tipoPlano=" + tipoPlano +
                ", valorPorMensagem=" + valorPorMensagem +
                '}';
    }
}

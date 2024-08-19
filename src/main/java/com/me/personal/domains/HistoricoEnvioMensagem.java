package com.me.personal.domains;

import com.me.personal.enumerated.TipoEnvioMensagem;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "historico_envio_mensagem")
public class HistoricoEnvioMensagem implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private static final String sequence = "historico_envio_mensagem_id_seq";

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = sequence, strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "data_hora_envio")
    private LocalDateTime dataHoraEnvio;

    @Column(name = "telefone_destino")
    private String telefoneDestino;

    @Column(name = "tipo_envio")
    private TipoEnvioMensagem tipoEnvio;

    @Column(name = "texto")
    private String texto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    public HistoricoEnvioMensagem() {
    }

    public HistoricoEnvioMensagem(LocalDateTime dataHoraEnvio, String telefoneDestino, TipoEnvioMensagem tipoEnvio,
                                  Cliente cliente, String texto) {
        this.dataHoraEnvio = dataHoraEnvio;
        this.telefoneDestino = telefoneDestino;
        this.tipoEnvio = tipoEnvio;
        this.cliente = cliente;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getDataHoraEnvio() {
        return dataHoraEnvio;
    }

    public void setDataHoraEnvio(LocalDateTime dataHoraEnvio) {
        this.dataHoraEnvio = dataHoraEnvio;
    }

    public String getTelefoneDestino() {
        return telefoneDestino;
    }

    public void setTelefoneDestino(String telefoneDestino) {
        this.telefoneDestino = telefoneDestino;
    }

    public TipoEnvioMensagem getTipoEnvio() {
        return tipoEnvio;
    }

    public void setTipoEnvio(TipoEnvioMensagem tipoEnvio) {
        this.tipoEnvio = tipoEnvio;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HistoricoEnvioMensagem that = (HistoricoEnvioMensagem) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "HistoricoEnvioMensagem{" +
                "id=" + id +
                ", dataHoraEnvio=" + dataHoraEnvio +
                ", telefoneDestino='" + telefoneDestino + '\'' +
                ", tipoEnvio=" + tipoEnvio +
                '}';
    }
}

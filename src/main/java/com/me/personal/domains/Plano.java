package com.me.personal.domains;

import com.me.personal.enumerated.TipoPlano;
import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "plano")
public class Plano implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private static final String sequence = "plano_id_seq";

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = sequence, strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "tipo_plano")
    private TipoPlano tipoPlano;

}

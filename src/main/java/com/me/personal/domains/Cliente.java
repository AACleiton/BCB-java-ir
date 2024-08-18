package com.me.personal.domains;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private static final String sequence = "cliente_id_seq";

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = sequence, strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "email")
    private String email;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "cpf_responsavel")
    private String cpfReponsavel;

    @Column(name = "cnpj")
    private String cnpj;

    @Column(name = "nome_empresa")
    private String nomeEmpresa;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "clientePlanoId.cliente", cascade = CascadeType.ALL)
    private List<ClientePlano> planos = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpfReponsavel() {
        return cpfReponsavel;
    }

    public void setCpfReponsavel(String cpfReponsavel) {
        this.cpfReponsavel = cpfReponsavel;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public List<ClientePlano> getPlanos() {
        return planos;
    }

    public ClientePlano getPlanoAtual() {
        return planos.stream()
                .filter(ClientePlano::isAtual)
                .findFirst()
                .orElse(null);
    }

    public void setPlanos(List<ClientePlano> planos) {
        this.planos = planos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(cpfReponsavel, cliente.cpfReponsavel) && Objects.equals(cnpj, cliente.cnpj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpfReponsavel, cnpj);
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                ", cpfReponsavel='" + cpfReponsavel + '\'' +
                ", cnpj='" + cnpj + '\'' +
                ", nomeEmpresa='" + nomeEmpresa + '\'' +
                '}';
    }
}

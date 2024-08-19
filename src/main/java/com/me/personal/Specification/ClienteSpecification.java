package com.me.personal.Specification;

import com.me.personal.domains.Cliente;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static java.util.Optional.ofNullable;

@Component
public class ClienteSpecification implements SpecificationDefault<Cliente> {

    public Specification<Cliente> filters(String search) {
        var builder  = this.builder();

        ofNullable(search).map(s -> this.like("nome", s)
                .or(this.like("email", s))
                .or(this.like("cpfReponsavel", s))
                .or(this.like("cnpj", s))
                .or(this.like("nomeEmpresa", s)))
                .ifPresent(builder::and);

        return builder.build();
    }

}

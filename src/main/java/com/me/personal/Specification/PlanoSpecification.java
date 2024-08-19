package com.me.personal.Specification;

import com.me.personal.domains.Plano;
import com.me.personal.enumerated.TipoPlano;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import static java.util.Optional.ofNullable;

@Component
public class PlanoSpecification implements SpecificationDefault<Plano> {

    private Specification<Plano> tipo(TipoPlano tipoPlano) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("tipoPlano"), tipoPlano);
    }

    public Specification<Plano> getSpecification(String search, TipoPlano tipoPlano) {
        var builder = this.builder();

        ofNullable(search).map(s -> this.like("nome", s)).ifPresent(builder::and);
        ofNullable(tipoPlano).map(this::tipo).ifPresent(builder::and);

        return builder.build();
    }

}

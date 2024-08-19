package com.me.personal.Specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Path;
import org.springframework.data.jpa.domain.Specification;

import java.io.Serializable;
import java.util.Collection;
import java.util.UUID;

public interface SpecificationDefault<T extends Serializable> {

    public static Expression<String> prepareAtributeForLike(CriteriaBuilder cb, Path<String> atribute) {
        return cb.function("unaccent", String.class, cb.lower(atribute));
    }

    public static String prepareStringForLike(String s) {
        return "%" + s + "%";
    }

    default Specification<T> id(Long id) {
        return (root, query, cb) -> cb.equal(root.get("id"), id);
    }

    default Specification<T> id(UUID id) {
        return (root, query, cb) -> cb.equal(root.get("id"), id);
    }

    default Specification<T> id(Object id) {
        if (id instanceof Long idLong) {
            return id(idLong);
        }

        throw new RuntimeException("tipo de id não conhecido: " + id.getClass().getSimpleName());
    }

    default Specification<T> id(Collection<UUID> id) {
        return (root, query, cb) -> root.get("id").in(id);
    }

    default Specification<T> isAtivo(Boolean ativo) {
        return (root, query, cb) -> cb.equal(root.get("ativo"), ativo);
    }

    default Specification<T> like(String field, String search) {
        return (root, query, cb) -> cb.like(prepareAtributeForLike(cb, root.get(field)), prepareStringForLike(search));
    }

    default SpecificationBuilder<T> builder() {
        return new SpecificationBuilder<>();
    }
}

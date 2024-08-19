package com.me.personal.repository;

import com.me.personal.domains.Plano;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanoRepository extends JpaRepository<Plano, Long>, JpaSpecificationExecutor<Plano> {
}

package com.me.personal.services;

import com.me.personal.Specification.PlanoSpecification;
import com.me.personal.domains.Plano;
import com.me.personal.enumerated.TipoPlano;
import com.me.personal.repository.PlanoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PlanoService {

    private final PlanoRepository planoRepository;
    private final PlanoSpecification planoSpecification;

    @Autowired
    public PlanoService(PlanoRepository planoRepository, PlanoSpecification planoSpecification) {
        this.planoRepository = planoRepository;
        this.planoSpecification = planoSpecification;
    }

    @Transactional(readOnly = true)
    public Plano findById(Long id) {
        return this.planoRepository.findById(id).orElseThrow(() -> new RuntimeException("Plano não encontrado"));
    }

    @Transactional(readOnly = true)
    public Page<Plano> findAll(String search, TipoPlano tipoPlano, Pageable pageable) {
        return this.planoRepository.findAll(this.planoSpecification.getSpecification(search, tipoPlano), pageable);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Plano create(Plano plano) {
        return this.save(plano);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Plano update(Plano plano) {
        var planoRecuperado = this.findById(plano.getId());

        planoRecuperado.atualizaDados(plano);

        return this.save(planoRecuperado);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Plano save(Plano plano) {
        return this.planoRepository.save(plano);
    }
}

package com.me.personal.repository;

import com.me.personal.domains.HistoricoEnvioMensagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface HistoricoEnvioMensagemRepository extends JpaRepository<HistoricoEnvioMensagem, Long>, JpaSpecificationExecutor<HistoricoEnvioMensagem> {
}

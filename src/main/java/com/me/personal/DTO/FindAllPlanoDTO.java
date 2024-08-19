package com.me.personal.DTO;

import com.me.personal.enumerated.TipoPlano;
import org.springframework.web.bind.annotation.RequestParam;

public class FindAllPlanoDTO {
    private String search;
    private TipoPlano tipo;
    private PageableDTO pageableDTO;

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public TipoPlano getTipo() {
        return tipo;
    }

    public void setTipo(TipoPlano tipo) {
        this.tipo = tipo;
    }

    public PageableDTO getPageableDTO() {
        return pageableDTO;
    }

    public void setPageableDTO(PageableDTO pageableDTO) {
        this.pageableDTO = pageableDTO;
    }
}

package com.me.personal.DTO;

import org.springframework.web.bind.annotation.RequestAttribute;

public class FindAllClienteDTO {
    private String search;
    private PageableDTO pageable;

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public PageableDTO getPageable() {
        return pageable;
    }

    public void setPageable(PageableDTO pageable) {
        this.pageable = pageable;
    }
}

package com.me.personal.controllers;

import com.me.personal.DTO.PageableDTO;
import com.me.personal.domains.Plano;
import com.me.personal.enumerated.TipoPlano;
import com.me.personal.services.PlanoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("api/plano")
public class PlanoController {

    private final PlanoService planoService;

    @Autowired
    public PlanoController(PlanoService planoService) {
        this.planoService = planoService;
    }

    @GetMapping("/find-by-id")
    public void findById(@RequestParam(name = "id") Long id) {
        this.planoService.findById(id);
    }

    @GetMapping("/find-all")
    public void findAll(@RequestParam(name = "search", required = false) String search,
                        @RequestParam(name = "tipo")TipoPlano tipo,
                        @RequestParam(name = "pageableDTO") PageableDTO pageableDTO) {
        this.planoService.findAll(search, tipo, pageableDTO.getPageable());
    }

    @PostMapping("/create")
    public void create(@RequestBody Plano plano) {
        this.planoService.create(plano);
    }

    @PutMapping("/update")
    public void update(@RequestBody Plano plano) {
        this.planoService.update(plano);
    }
}

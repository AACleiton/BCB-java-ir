package com.me.personal.controllers;

import com.me.personal.DTO.FindAllPlanoDTO;
import com.me.personal.DTO.PageableDTO;
import com.me.personal.domains.Plano;
import com.me.personal.enumerated.TipoPlano;
import com.me.personal.services.PlanoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/plano")
public class PlanoController {

    private final PlanoService planoService;

    @Autowired
    public PlanoController(PlanoService planoService) {
        this.planoService = planoService;
    }

    @GetMapping("/find-by-id")
    public ResponseEntity<Plano> findById(@RequestBody Long id) {
        try {
            return ResponseEntity.ok().body(this.planoService.findById(id));
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/find-all")
    public ResponseEntity<Page<Plano>> findAll(@RequestBody FindAllPlanoDTO findAllPlanoDTO) {
        return ResponseEntity.ok().body(this.planoService.findAll(findAllPlanoDTO.getSearch(), findAllPlanoDTO.getTipo(),
                findAllPlanoDTO.getPageableDTO().getPageable()));
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

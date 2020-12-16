package com.example.demo.controller;

import com.example.demo.dto.MateriaDto;
import com.example.demo.entity.MateriaEntity;
import com.example.demo.services.MateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/materia")
public class MateriaController {

    @Autowired
    private MateriaService materiaService;

    @GetMapping
    public ResponseEntity<List<MateriaEntity>> listarMaterias(){

        return ResponseEntity.ok().body(this.materiaService.listar());
    }
    @GetMapping("/{id}")
    public ResponseEntity<MateriaEntity> consultaMateria(@PathVariable Long id){

        return ResponseEntity.ok().body(this.materiaService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Boolean> cadastrarMateria(@Valid @RequestBody MateriaDto materia){
       return ResponseEntity.status(HttpStatus.CREATED).body(this.materiaService.cadastrar(materia));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deletarMateria(@PathVariable Long id){
        return ResponseEntity.ok().body(this.materiaService.deletar(id));
    }

    @PutMapping
    public ResponseEntity<Boolean> atualizarMateria(@Valid @RequestBody MateriaDto materia){
        return ResponseEntity.ok().body(this.materiaService.atualizar(materia));

    }
}

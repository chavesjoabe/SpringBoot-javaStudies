package com.example.demo.controller;

import com.example.demo.entity.MateriaEntity;
import com.example.demo.repositories.MateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/materia")
public class MateriaController {

    @Autowired
    private MateriaRepository materiaRepository;

    @GetMapping
    public ResponseEntity<List<MateriaEntity>> listarMaterias(){

        return ResponseEntity.status(HttpStatus.OK).body(materiaRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MateriaEntity> consultaMateria(@PathVariable Long id){

        return ResponseEntity.status(HttpStatus.OK).body(this.materiaRepository.findById(id).get());
    }


    @PostMapping
    public ResponseEntity<Boolean> cadastrarMateria(@RequestBody MateriaEntity materia){
        try {
            this.materiaRepository.save(materia);
            return ResponseEntity.ok().body(true);
        }
        catch (Exception e){
            return ResponseEntity.ok().body(false);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deletarMateria(@PathVariable Long id){
        MateriaEntity materia = materiaRepository.findById(id).get();

        materiaRepository.delete(materia);

        return ResponseEntity.ok().body(true);
    }
}

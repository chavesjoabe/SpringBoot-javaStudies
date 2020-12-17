package com.example.demo.controller;

import com.example.demo.dto.MateriaDto;
import com.example.demo.model.Response;
import com.example.demo.services.MateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/materia")
public class MateriaController {

    private static final String CADASTRAR = "CADASTRAR";
    private static final String DELETE = "DELETE";
    private static final String UPDATE = "UPDATE";
    private static final String LIST = "LIST";



    @Autowired
    private MateriaService materiaService;

    @GetMapping
    public ResponseEntity<Response<List<MateriaDto>>> listarMaterias(){
        Response<List<MateriaDto>> response = new Response();
        response.setData(this.materiaService.listar());
        response.setStatusCode(HttpStatus.OK.value());
        response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MateriaController.class)
                .listarMaterias()).withSelfRel());

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Response<MateriaDto>> consultaMateria(@PathVariable Long id){
        Response<MateriaDto> response = new Response();
        MateriaDto materia = this.materiaService.buscarPorId(id);
        response.setData(this.materiaService.buscarPorId(id));
        response.setStatusCode(HttpStatus.OK.value());
        response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MateriaController.class).deletarMateria(id)).withRel(DELETE));
        response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MateriaController.class).atualizarMateria(materia)).withRel(UPDATE));

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    public ResponseEntity<Response<Boolean>> cadastrarMateria(@Valid @RequestBody MateriaDto materia){
        Response<Boolean> response = new Response();
        response.setData(this.materiaService.cadastrar(materia));
        response.setStatusCode(HttpStatus.CREATED.value());
        response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MateriaController.class)
                .cadastrarMateria(materia)).withSelfRel());
        response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MateriaController.class)
                .deletarMateria(materia.getId())).withRel(DELETE));
        response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MateriaController.class)
                .atualizarMateria(materia)).withRel(UPDATE));

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Boolean>> deletarMateria(@PathVariable Long id){
        MateriaDto materia = this.materiaService.buscarPorId(id);
        Response<Boolean> response = new Response();

        response.setData(this.materiaService.deletar(id));
        response.setStatusCode(HttpStatus.OK.value());
        response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MateriaController.class)
                .cadastrarMateria(materia)).withRel(CADASTRAR));
        response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MateriaController.class)
                .listarMaterias()).withRel(LIST));

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping
    public ResponseEntity<Response<Boolean>> atualizarMateria(@Valid @RequestBody MateriaDto materia){

        MateriaDto materiaDto = this.materiaService.buscarPorId(materia.getId());
        Response<Boolean> response = new Response();
        response.setData(this.materiaService.atualizar(materia));
        response.setStatusCode(HttpStatus.OK.value());
        response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MateriaController.class)
                .atualizarMateria(materiaDto)).withSelfRel());
        response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MateriaController.class)
                .listarMaterias()).withRel(LIST));
        response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MateriaController.class)
                .deletarMateria(materia.getId())).withRel(DELETE));

        return ResponseEntity.status(HttpStatus.OK).body(response);

    }
}

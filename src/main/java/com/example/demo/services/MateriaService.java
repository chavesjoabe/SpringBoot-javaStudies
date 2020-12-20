package com.example.demo.services;

import com.example.demo.controller.MateriaController;
import com.example.demo.dto.MateriaDto;
import com.example.demo.entity.MateriaEntity;
import com.example.demo.exceptions.MateriaException;
import com.example.demo.repositories.MateriaRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MateriaService implements IMateriaService {

    private static final String MENSAGEM_ERRO = "Erro interno do Servidor, contate o suporte";
    private static final String MATERIA_NAO_ENCONTRADA = "Materia Nao encontrada";
    private MateriaRepository materiaRepository;
    private ModelMapper mapper;


    @Autowired
    public MateriaService(MateriaRepository materiaRepository) {
        this.materiaRepository = materiaRepository;
        this.mapper = new ModelMapper();
    }

    @Override
    public List<MateriaDto> listar() {
        try{
            List<MateriaDto> materiaDto = this.mapper.map(this.materiaRepository.findAll(), new TypeToken<List<MateriaDto>>(){

            }.getType());
            materiaDto.forEach(materia ->{
                materia.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
                        .methodOn(MateriaController.class).consultaMateria(materia.getId()))
                        .withSelfRel());
            });

            return materiaDto;
        } catch (Exception e) {
            throw new MateriaException(MENSAGEM_ERRO, HttpStatus.INTERNAL_SERVER_ERROR);        }
    }

    @Cacheable(value = "materia", key = "#id")
    @Override
    public MateriaDto buscarPorId(Long id) {
        try {
            Optional<MateriaEntity> materiaOptional = this.materiaRepository.findById(id);
            if (materiaOptional.isPresent()) {

                return this.mapper.map(materiaOptional.get(), MateriaDto.class);
            }
            throw new MateriaException(MATERIA_NAO_ENCONTRADA, HttpStatus.NOT_FOUND);
        } catch (MateriaException m) {
            throw m;
        } catch (Exception e) {
            throw new MateriaException(MENSAGEM_ERRO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @CacheEvict(value = "materia", allEntries = true)
    @Override
    public Boolean cadastrar(MateriaDto materia) {
        try {
            MateriaEntity materiaEntity = this.mapper.map(materia, MateriaEntity.class);
            materiaRepository.save(materiaEntity);
            return Boolean.TRUE;
        } catch (Exception e) {
            throw new MateriaException(MENSAGEM_ERRO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Boolean deletar(Long id) {
        try {
            this.buscarPorId(id);
            materiaRepository.deleteById(id);
            return Boolean.TRUE;
        } catch (MateriaException m) {
            throw m;
        } catch (Exception e) {
            throw e;
        }
    }
    @CacheEvict(value = "materia", allEntries = true)
    @Override
    public Boolean atualizar(MateriaDto materia) {
        try {
            this.buscarPorId(materia.getId());

            MateriaEntity materiaAtualizada = this.mapper.map(materia, MateriaEntity.class);

            materiaRepository.save(materiaAtualizada);

            return Boolean.TRUE;

        } catch (MateriaException m) {
            throw m;
        } catch (Exception e) {
            throw e;
        }
    }

    public List<MateriaEntity> listarMateriaPorHoraMinima(int horaMinima){
        try {
            List<MateriaEntity> lista = this.materiaRepository.findByMinHour(horaMinima);

            return lista;
        } catch (MateriaException m) {
            throw m;
        } catch (Exception e) {
            throw e;
        }

    }
}

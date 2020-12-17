package com.example.demo.services;

import com.example.demo.dto.MateriaDto;
import com.example.demo.entity.MateriaEntity;
import com.example.demo.model.Response;

import java.util.List;

public interface IMateriaService {

    public List<MateriaDto> listar();
    public MateriaDto buscarPorId(Long id);
    public Boolean cadastrar(MateriaDto materia);
    public Boolean deletar(Long id);
    public Boolean atualizar(MateriaDto materia);
}

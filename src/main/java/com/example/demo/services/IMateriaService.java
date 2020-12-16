package com.example.demo.services;

import com.example.demo.dto.MateriaDto;
import com.example.demo.entity.MateriaEntity;

import java.util.List;

public interface IMateriaService {

    public List<MateriaEntity> listar();
    public MateriaEntity buscarPorId(Long id);
    public Boolean cadastrar(MateriaDto materia);
    public Boolean deletar(Long id);
    public Boolean atualizar(MateriaDto materia);
}

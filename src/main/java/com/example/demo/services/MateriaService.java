package com.example.demo.services;

import com.example.demo.entity.MateriaEntity;
import com.example.demo.repositories.MateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MateriaService implements IMateriaService {

    @Autowired
    private MateriaRepository materiaRepository;

    @Override
    public List<MateriaEntity> listar() {
        try{
            return materiaRepository.findAll();
        }catch (Exception e){
            return new ArrayList<>();
        }

    }

    @Override
    public MateriaEntity buscarPorId(Long id) {
        try{
            return materiaRepository.findById(id).get();
        }
        catch (Exception e){
            return null;
        }


    }

    @Override
    public Boolean cadastrar(MateriaEntity materia) {
        try{
            materiaRepository.save(materia);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public Boolean deletar(Long id) {
        try{
            materiaRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public Boolean atualizar(MateriaEntity materia) {
        try{
           MateriaEntity materiaAtualizada = materiaRepository.findById(materia.getId()).get();

           materiaAtualizada.setNome(materia.getNome());
           materiaAtualizada.setHoras(materia.getHoras());
           materiaAtualizada.setCodigo(materia.getCodigo());
           materiaAtualizada.setFrenquencia(materia.getFrenquencia());

           materiaRepository.save(materiaAtualizada);

           return true;
        }catch (Exception e){
            return false;
        }
    }
}

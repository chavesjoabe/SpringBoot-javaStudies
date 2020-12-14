package com.example.demo.services;

import com.example.demo.entity.MateriaEntity;
import com.example.demo.exceptions.MateriaException;
import com.example.demo.repositories.MateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
            Optional<MateriaEntity> materiaOptional = this.materiaRepository.findById(id);
            if(materiaOptional.isPresent()) {

                return materiaOptional.get();
            }
            throw new MateriaException("Materia Nao encontrada", HttpStatus.NOT_FOUND);
        }catch (MateriaException m){
            throw m;
        }
        catch (Exception e){
            throw new MateriaException("Erro interno do Servidor, contate o suporte", HttpStatus.INTERNAL_SERVER_ERROR);
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
            this.buscarPorId(id);
            materiaRepository.deleteById(id);
            return true;
        }catch (MateriaException m){
            throw m;
        }
        catch (Exception e){
            throw e;        }
    }

    @Override
    public Boolean atualizar(MateriaEntity materia) {
        try{
            MateriaEntity materiaAtualizada = this.buscarPorId(materia.getId());

                materiaAtualizada.setNome(materia.getNome());
                materiaAtualizada.setHoras(materia.getHoras());
                materiaAtualizada.setCodigo(materia.getCodigo());
                materiaAtualizada.setFrenquencia(materia.getFrenquencia());

                materiaRepository.save(materiaAtualizada);

            return true;

        }catch (MateriaException m){
            throw m;
        }
        catch (Exception e){
            throw e;
        }
    }
}

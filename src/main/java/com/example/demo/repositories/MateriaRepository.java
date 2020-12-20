package com.example.demo.repositories;

import com.example.demo.entity.MateriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MateriaRepository extends JpaRepository<MateriaEntity, Long> {

    @Query("select m from MateriaEntity m where m.horas >= :horaMinima")
    public List<MateriaEntity> findByMinHour(@Param("horaMinima") int horaMinima);
}

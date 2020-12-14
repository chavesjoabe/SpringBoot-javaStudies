package com.example.demo.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class MateriaDto {


    private Long id;

    @NotBlank(message = "Informe o nome da materia")
    private String nome;
    @Min(value = 34, message = "Permitido o minimo de 34hrs por materia")
    @Max(value = 120, message = "Permitido o maximo de 120hrs por materia")

    private int horas;
    @NotBlank(message = "Informe o codigo da materia")
    private String codigo;

    private int frenquencia;
}

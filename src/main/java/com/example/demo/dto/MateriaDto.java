package com.example.demo.dto;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class MateriaDto extends RepresentationModel<MateriaDto> {


    private Long id;

    @NotBlank(message = "Informe o nome da materia")
    private String nome;

    @Min(value = 34, message = "Permitido o minimo de 34hrs por materia")
    @Max(value = 120, message = "Permitido o maximo de 120hrs por materia")
    private int horas;

    @NotBlank(message = "Informe o codigo da materia")
    private String codigo;

    @Min(value = 1, message = "frequencia minima de 1")
    @Max(value = 3,message = "frequencia maxima de 3")
    private Integer frequencia;
}

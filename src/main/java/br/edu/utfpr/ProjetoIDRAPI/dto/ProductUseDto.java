package br.edu.utfpr.ProjetoIDRAPI.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ProductUseDto {

    private Long id;

    private PropertyDto property;

    private LocalDate useDate;

    private Integer quantity;

    private String usedFor;
}

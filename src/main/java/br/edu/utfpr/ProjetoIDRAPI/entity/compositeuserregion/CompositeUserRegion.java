package br.edu.utfpr.ProjetoIDRAPI.entity.compositeuserregion;

import lombok.Data;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

@Data
@Embeddable
public class CompositeUserRegion implements Serializable {
    //A classe composite serve para fazer a foreign key composta do banco de dados.
    //Essa classe é criada para receber a anotação @Embeddable.
    private long user;

    private long region;
}

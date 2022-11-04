package br.edu.utfpr.ProjetoIDRAPI.model;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class CompositePropertyTechnician implements Serializable {
    private long user;

    private long property;
}

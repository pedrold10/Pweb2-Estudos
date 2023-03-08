package br.edu.ifpb.pweb2.bitbank.model;

import java.io.Serializable;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class Correntista implements Serializable {
    private static final long serialVersionUID = 1L;
    @NotNull(message="O campo Id não pode ser nulo")
    private Integer id;
    @Size(max=50, message="O campo nome só pode ter até 50 caracteres")
    private String nome;
    private String email;
    private String senha;
    private boolean admin;
}
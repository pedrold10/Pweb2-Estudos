package br.edu.ifpb.pweb2.bitbank.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Correntista implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Campo obrigatório!")
    private String nome;

    @NotBlank(message = "Campo obrigatório!")
    @Email(message = "Informe um email válido!")
    private String email;

    @NotBlank(message = "Campo obrigatório!")
    @Size(min = 3, max = 60, message = "Senha deve ter entre  3 a 12 caracteres")
    private String senha;

    private boolean admin;
}

package br.edu.ifpb.pweb2.praticas.springcontroladores.model;

import lombok.Data;

@Data
public class Livro {
    private Long id;
    private String titulo;
    private String autor;
    private String estilo;

}

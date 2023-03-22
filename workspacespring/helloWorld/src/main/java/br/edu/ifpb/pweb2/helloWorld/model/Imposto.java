package br.edu.ifpb.pweb2.helloWorld.model;

import lombok.Data;

@Data
public class Imposto {
    private double salarioBase;
    private double aliquota;
    private double valorTributavel;
}

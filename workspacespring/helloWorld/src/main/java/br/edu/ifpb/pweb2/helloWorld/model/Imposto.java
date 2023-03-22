package br.edu.ifpb.pweb2.helloWorld.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Imposto {
    private Double salarioBase;
    private Double aliquota;
    private Double valorTributavel;

    public void calcularValorTributavel(){
        this.valorTributavel = this.getSalarioBase() * this.getAliquota();
    }
}

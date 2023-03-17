package br.edu.ifpb.pweb2.helloWorld.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
public class HelloSpringController {
    @Value("${nome.usuario}")
    private String nomeUsuario;

    @Value("${salario.base}")
    private Double salarioBase;

    @Value("${aliquota.ir}")
    private Double aliquotaIR;

    @RequestMapping("/alo")
    public String alo(Model model){
        model.addAttribute("nome", nomeUsuario);
        model.addAttribute("hora", new Date());
        return "main";
    }

    @RequestMapping("/formvt")
    public String exibirFormularioIR(){
        return "ir";
    }

    //@RequestMapping(value= "/calcvt", method= RequestMethod.POST)
    @PostMapping("/calcvt")
    public String calcularValorTributavel(
            @RequestParam("salbase") double salarioBase,
            @RequestParam("aliq") double aliquotaIR,
            Model model){
        double valorTributavel = salarioBase * aliquotaIR;
        model.addAttribute("valorTributavel", valorTributavel);
        return "ir";
    }
}

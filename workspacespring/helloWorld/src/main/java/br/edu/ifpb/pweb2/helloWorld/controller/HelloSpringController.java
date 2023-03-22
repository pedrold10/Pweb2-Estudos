package br.edu.ifpb.pweb2.helloWorld.controller;

import br.edu.ifpb.pweb2.helloWorld.model.Imposto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public String exibirFormularioIR(Model model, Imposto imposto){
        model.addAttribute("imposto", imposto);
        return "ir";
    }

    //@RequestMapping(value= "/calcvt", method= RequestMethod.POST)
    @PostMapping("/calcvt")
    public String calcularValorTributavel(Imposto imposto, Model model){
        imposto.calcularValorTributavel();
        System.out.println(imposto);
        model.addAttribute("imposto", imposto);
        return "ir";
    }
}

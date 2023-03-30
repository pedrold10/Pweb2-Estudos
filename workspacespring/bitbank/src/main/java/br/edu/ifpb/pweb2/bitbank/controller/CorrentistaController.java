package br.edu.ifpb.pweb2.bitbank.controller;

import br.edu.ifpb.pweb2.bitbank.model.Correntista;
import br.edu.ifpb.pweb2.bitbank.repository.CorrentistaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/correntistas")
public class CorrentistaController {

    @RequestMapping("/form")
    public String getForm(Correntista correntista, Model model){
        if (correntista.getNome() == null || correntista.getEmail() == null) {
            model.addAttribute("error", "Todos os campos são obrigatórios");
            return "correntistas/form";
        }
        model.addAttribute("correntista", correntista);
        return "correntistas/form";
    }

    @RequestMapping("/save")
    public String save(Correntista correntista, Model model){
        CorrentistaRepository.save(correntista);
        model.addAttribute("correntistas", CorrentistaRepository.findAll());
        return "correntistas/list";
    }
}

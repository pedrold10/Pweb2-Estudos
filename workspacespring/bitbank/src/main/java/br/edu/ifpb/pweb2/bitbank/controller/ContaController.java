package br.edu.ifpb.pweb2.bitbank.controller;

import br.edu.ifpb.pweb2.bitbank.model.Conta;
import br.edu.ifpb.pweb2.bitbank.model.Correntista;
import br.edu.ifpb.pweb2.bitbank.repository.ContaRepository;
import br.edu.ifpb.pweb2.bitbank.repository.CorrentistaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/contas")
public class ContaController {
    @RequestMapping("/form")
    public ModelAndView getForm(ModelAndView modelAndView){
        modelAndView.setViewName("contas/form");
        modelAndView.addObject("conta", new Conta(new Correntista()));
        return modelAndView;
    }
    @ModelAttribute("correntistaItems")
    public List<Correntista> getCorrentistas(){
        return CorrentistaRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView adicioneConta(Conta conta, ModelAndView modelAndView){
        Correntista correntista = CorrentistaRepository.findById(conta.getCorrentista().getId());
        conta.setId(ContaRepository.getMaxId());
        conta.setCorrentista(correntista);
        ContaRepository.save(conta);
        modelAndView.setViewName("contas/list");
        modelAndView.addObject("contas", ContaRepository.findAll());
        return modelAndView;

    }
    @RequestMapping("/lista")
    public ModelAndView liste(ModelAndView modelAndView){
        modelAndView.setViewName("contas/list");
        modelAndView.addObject("contas", ContaRepository.findAll());
        return modelAndView;
    }
}

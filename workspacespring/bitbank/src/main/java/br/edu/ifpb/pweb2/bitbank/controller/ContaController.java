package br.edu.ifpb.pweb2.bitbank.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifpb.pweb2.bitbank.model.Conta;
import br.edu.ifpb.pweb2.bitbank.model.Correntista;
import br.edu.ifpb.pweb2.bitbank.model.Transacao;
import br.edu.ifpb.pweb2.bitbank.repository.ContaRepository;
import br.edu.ifpb.pweb2.bitbank.repository.CorrentistaRepository;

@Controller
@RequestMapping("/contas")
public class ContaController {

    @Autowired
    CorrentistaRepository correntistaRepository;

    @Autowired
    ContaRepository contaRepository;

    @RequestMapping("/form")
    public ModelAndView getForm(ModelAndView model) {
        model.addObject("conta", new Conta(new Correntista()));
        model.addObject("menu", "conta");
        model.setViewName("contas/form");
        return model;
    }

    @ModelAttribute("correntistaItems")
    public List<Correntista> getCorrentistas() {
        return correntistaRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView save(Conta conta, ModelAndView model, RedirectAttributes attrs) {
        Correntista correntista = null;
        Optional<Correntista> opCorrentista = correntistaRepository.findById(conta.getCorrentista().getId());
        if (opCorrentista.isPresent()) {
            correntista = opCorrentista.get();
            conta.setCorrentista(correntista);
            contaRepository.save(conta);
            attrs.addFlashAttribute("mensagem", "Conta cadastrada com sucesso!");
            model.setViewName("redirect:contas");
        } else {
            model.addObject("mensagem",
                    "Correntista com id = " + conta.getCorrentista().getId() + " não encontrado.");
            model.setViewName("contas/form");
        }
        return model;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView listAll(ModelAndView model) {
        model.addObject("menu", "conta");
        model.addObject("contas", contaRepository.findAll());
        model.setViewName("contas/list");
        return model;
    }

    @RequestMapping("/{id}")
    public ModelAndView getContaById(@PathVariable(value = "id") Integer id, ModelAndView model) {
        model.addObject("menu", "conta");
        Optional<Conta> opConta = contaRepository.findById(id);
        if (opConta.isPresent()) {
            model.setViewName("contas/form");
            model.addObject("conta", opConta.get());
        } else {
            model.setViewName("contas/list");
            model.addObject("mensagem", "Conta com id " + id + " não encontrado.");
        }
        return model;
    }

    @RequestMapping("/nuconta")
    public ModelAndView getNuConta(ModelAndView mav) {
        mav.addObject("menu", "operacao");
        mav.setViewName("contas/operacao");
        return mav;
    }

    @RequestMapping(value = "/operacao")
    public ModelAndView transactConta(String nuConta, Transacao transacao, ModelAndView mav) {
        String proxPagina = "";
        mav.addObject("menu", "operacao");
        if (nuConta != null && transacao.getValor() == null) {
            Conta conta = contaRepository.findByNumeroWithTransacoes(nuConta);
            if (conta != null) {
                mav.addObject("conta", conta);
                mav.addObject("transacao", transacao);
                proxPagina = "contas/operacao";
            } else {
                mav.addObject("mensagem", "Conta inexistente!");
                mav.addObject("menu", "operacao");
                proxPagina = "contas/operacao";
            }
        } else {
            Conta conta = contaRepository.findByNumeroWithTransacoes(nuConta);
            conta.addTransacao(transacao);
            contaRepository.save(conta);
            proxPagina = "redirect:/contas/" + conta.getId() + "/transacoes";
        }
        mav.setViewName(proxPagina);
        return mav;
    }

    @RequestMapping(value = "/{id}/transacoes")
    public ModelAndView listAllTrasacoes(@PathVariable("id") Integer idConta, ModelAndView model) {
        Conta conta = contaRepository.findByIdWithTransacoes(idConta);
        model.addObject("conta", conta);
        model.addObject("menu", "operacao");
        model.setViewName("contas/transacoes");
        return model;
    }
}

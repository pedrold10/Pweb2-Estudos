package br.edu.ifpb.pweb2.praticas.springcontroladores.controller;

import br.edu.ifpb.pweb2.praticas.springcontroladores.model.Livro;
import br.edu.ifpb.pweb2.praticas.springcontroladores.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroRepository livroRepository;

    @RequestMapping("/form")
    public String getFormLivro(Model model){
        model.addAttribute("livro", new Livro());
        return "/livros/livros-form";
    }

    @ModelAttribute("estilos")
    public Map<String, String> getEstilos(){
        Map<String, String> estilos = new LinkedHashMap<String, String>();
        estilos.put("1", "Romance");
        estilos.put("2", "Terror");
        estilos.put("3", "Suspense");
        estilos.put("4", "Biografia");
        return estilos;
    }

    @RequestMapping("/save")
    public String cadastreLivro(Livro livro, Model model){
        if(livro.getId() == null){
            livroRepository.insert(livro);
        }
        else{
            livroRepository.update(livro);
        }
        return "/livros/list-livros";
    }

    @RequestMapping(method = { RequestMethod.GET })
    public String listeLivrosMatriculados(Model model){
        model.addAttribute("livros", livroRepository.findAll());
        return "/livros/list-livros";
    }
    @RequestMapping("/busque/{id}")
    public String busqueLivro(@PathVariable("id") Long id, Model model){
        Livro livro = livroRepository.findById(id);
        model.addAttribute("livros", Collections.singleton(livro));
        return "livros/list-livros";
    }
}

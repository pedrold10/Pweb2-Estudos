package br.edu.ifpb.pweb2.praticas.springcontroladores.repository;

import br.edu.ifpb.pweb2.praticas.springcontroladores.model.Livro;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
@Scope("application")
public class LivroRepository {
    private Map<Long, Livro> repositorio = new HashMap<Long, Livro>();

    public Livro findById(Long id){
        return repositorio.get(id);
    }
    public void insert(Livro livro){
        Long id = getMaxId();
        livro.setId(id);
        repositorio.put(id, livro);
    }
    public void update(Livro livro){
        repositorio.put(livro.getId(), livro);
    }
    public List<Livro> findAll(){
        List<Livro> Livros = repositorio
                .values().stream().collect(Collectors.toList());
        return Livros;
    }
    private Long getMaxId() {
        List<Livro> Livros = findAll();
        if(Livros == null || Livros.isEmpty())
            return 1L;
        Livro contaMaxId = Livros
                .stream()
                .max(Comparator.comparing(Livro::getId))
                .orElseThrow(NoSuchElementException::new);
        return contaMaxId.getId() == null ? 1L : contaMaxId.getId() + 1;
    }
}

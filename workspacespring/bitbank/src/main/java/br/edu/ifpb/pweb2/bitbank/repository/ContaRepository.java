package br.edu.ifpb.pweb2.bitbank.repository;

import br.edu.ifpb.pweb2.bitbank.model.Conta;

import java.util.*;
import java.util.stream.Collectors;

public class ContaRepository {
    private static Map<Integer, Conta> repositorio = new HashMap<Integer, Conta>();

    public static Conta findById(Integer id){
        return repositorio.get(id);
    }
    public static void save(Conta conta){
        repositorio.put(conta.getId(), conta);
    }
    public static List<Conta> findAll(){
        List<Conta> contas = repositorio.values().stream().collect(Collectors.toList());
        return contas;
    }

    public static Integer getMaxId(){
        List<Conta> contas = findAll();
        if(contas == null || contas.isEmpty())
            return 1;
        Conta contaMaxId = contas
                .stream()
                .max(Comparator.comparing(Conta::getId))
                .orElseThrow(NoSuchElementException::new);
        return contaMaxId.getId() == null ? 1 : contaMaxId.getId() + 1;
    }
}

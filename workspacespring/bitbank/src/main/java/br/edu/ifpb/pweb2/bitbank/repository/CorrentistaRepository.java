package br.edu.ifpb.pweb2.bitbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import br.edu.ifpb.pweb2.bitbank.model.Correntista;

@Component
public interface CorrentistaRepository extends JpaRepository<Correntista, Integer> {

}

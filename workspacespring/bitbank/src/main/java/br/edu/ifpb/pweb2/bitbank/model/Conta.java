package br.edu.ifpb.pweb2.bitbank.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Conta implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String numero;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date data;

    @OneToMany(mappedBy = "conta")
    private Set<Transacao> transacoes = new HashSet<Transacao>();

    @OneToOne
    @JoinColumn(name = "id_correntista")
    private Correntista correntista;

    public Conta(Correntista correntista) {
        this.correntista = correntista;
    }

    public BigDecimal getSaldo() {
        BigDecimal total = BigDecimal.ZERO;
        for (Transacao t : this.transacoes) {
            total = total.add(t.getValor());
        }
        return total;
    }
    public void addTransacao(Transacao transacao){
        this.transacoes.add(transacao);
        transacao.setConta(this);
    }
}
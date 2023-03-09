package app;

import br.edu.ifpb.pweb2.springioc.configuration.DocumentGeneratorConf;
import br.edu.ifpb.pweb2.springioc.configuration.gerador.GeradorDocumentoIntf;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class GeradorTextoApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(
                        DocumentGeneratorConf.class
        );
        GeradorDocumentoIntf gerador = context.getBean(GeradorDocumentoIntf.class);
        gerador.addTexto("Algum texto aleatório");
        gerador.addTexto("Mais texto aleatório");
        gerador.addTexto("Um outro texto aleatório");

        context.close();
    }
}

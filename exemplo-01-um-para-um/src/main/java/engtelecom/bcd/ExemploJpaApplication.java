package engtelecom.bcd;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import engtelecom.bcd.model.Endereco;
import engtelecom.bcd.model.Pessoa;
import engtelecom.bcd.repository.EnderecoRepository;
import engtelecom.bcd.repository.PessoaRepository;

/**
 * A anotação abaixo é para indicar que essa é uma aplicação SpringBoot
 */
@SpringBootApplication
public class ExemploJpaApplication {

    // O uso de Logger é uma boa prática para registrar informações de depuração,
    // erro, etc.
    private static final Logger log = LoggerFactory.getLogger(ExemploJpaApplication.class);

    // Injeção de dependência para os repositórios
    @Autowired
    PessoaRepository pessoaRepository;
    @Autowired
    EnderecoRepository enderecoRepository;

    public static void main(String[] args) {
        // Método run para executar a aplicação. É necessário passar como parâmetro uma classe que
        // tenha a anotação de aplicação e, neste caso, que tenha uma Bean para executar um cliente em
        // linha de comando
        SpringApplication.run(ExemploJpaApplication.class, args);
        log.info("Aplicação finalizada");
    }

    /**
     * Este método será invocado assim que a aplicação for executada.
     * @return
     */
    @Bean
    public CommandLineRunner demoOneToOne() {
        return (args) -> {

            try {
                log.info("Iniciando aplicação");

                this.povoarBase();
                this.listandoRegistros();

            } catch (Exception e) {
                log.error(e.toString());
            }
        };
    }

    /**
     * Para adicionar registros no banco de dados
     * @throws Exception
     */
    private void povoarBase() throws Exception{
        // Criando dois objetos da classe Pessoa
        Pessoa juca = new Pessoa("Juca de Oliveira", "juca@email.com", "123.456.789-00");
        Pessoa pedro = new Pessoa("Pedro", "pedro@email.com", "456-789-012-33");

        // Criando dois objetos da classe Endereco
        Endereco enderecoJuca = new Endereco("Rua das Oliveiras, 10", "São José", "SC", "88.103-30", juca);
        Endereco enderecoPedro = new Endereco("Rua José Lino Kretzer, 608", "São José", "SC", "88.103-30", pedro);

        // Atribuindo um endereço ao Juca
        juca.setEndereco(enderecoJuca);

        // Atribuindo um endereço ao Pedro
        pedro.setEndereco(enderecoPedro);

        // Persistindo as pessoas (isso irá salvar automaticamente o endereço)
        pessoaRepository.save(juca);
        pessoaRepository.save(pedro);
    }

    /**
     * Consultando registros no banco de dados
     * @throws Exception
     */
    private void listandoRegistros() throws Exception{
        System.out.println("----------- Todas Pessoas ---------------------");
        // Listando todas as pessoas
        for (var pessoa : pessoaRepository.findAll()) {
            System.out.println(pessoa);
        }
        System.out.println("-----------------------------------------------");

        System.out.println("------- Pessoas com CPF específico ------------");
        List<Pessoa> resultado = pessoaRepository.findByCpf("123.456.789-00");
        resultado.forEach(System.out::println);
        System.out.println("-----------------------------------------------");
    }
}

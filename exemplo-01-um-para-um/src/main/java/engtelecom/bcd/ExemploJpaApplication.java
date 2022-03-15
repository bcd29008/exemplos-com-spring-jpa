package engtelecom.bcd;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import engtelecom.bcd.entities.Endereco;
import engtelecom.bcd.entities.Pessoa;
import engtelecom.bcd.repository.EnderecoRepository;
import engtelecom.bcd.repository.PessoaRepository;


/**
 * A anotação abaixo é para indicar que essa é uma aplicação SpringBoot
 */
@SpringBootApplication
public class ExemploJpaApplication {

	// O uso de Logger é uma boa prática para registrar informações de depuração, erro, etc.
	private static final Logger log = LoggerFactory.getLogger(ExemploJpaApplication.class);

	public static void main(String[] args) {
		//Método run para executar a aplicação. É necessário passar como parâmetro uma classe que
		//tenha a anotação de aplicação e, neste caso, que tenha uma Bean para executar um cliente em
		//linha de comando
		SpringApplication.run(ExemploJpaApplication.class, args);
		log.info("Aplicação finalizada");
	}


	// Este método será invocado assim que a aplicação for executada. Como parâmetro pode-se passar
	// todas as interfaces que herdam de Repository
	@Bean
	public CommandLineRunner demoOneToOne(PessoaRepository pessoaRepository, EnderecoRepository enderecoRepository){
		return (args) -> {
			
			log.info("Iniciando aplicação");


			// Criando dois objeto da classe Pessoa
			Pessoa juca = new Pessoa("Juca de Oliveira","juca@email.com", "123.456.789-00");
			Pessoa pedro = new Pessoa("Pedro","pedro@email.com", "456-789-012-33");

			// Criando dois objetos da classe Endereco
			Endereco enderecoJuca = new Endereco("Rua das Oliveiras, 10", "São José", "SC", "88.103-30");
			Endereco enderecoPedro = new Endereco("Rua José Lino Kretzer, 608", "São José", "SC", "88.103-30");


			// Atribuindo uma pessoa ao endereço
			enderecoJuca.setPessoa(juca);

			// Atribuindo um endereço ao Juca
			juca.setEndereco(enderecoJuca);

			// Atribuindo um endereço ao Pedro
			pedro.setEndereco(enderecoPedro);

			
			// Persistindo as pessoas (isso irá salvar automaticamente o endereço)
			pessoaRepository.save(juca);
			pessoaRepository.save(pedro);
			
			
			// Listando todas as pessoas
			for (var element : pessoaRepository.findAll()) {
				System.out.println(element);
			}

			Pessoa resultado = pessoaRepository.findByCpf("123.456.789-00");

			if (resultado != null){
				System.out.println("Pessoa encontrada pelo CPF: 123.456.789-00");
				System.out.println(resultado);
			}
		};
	}
}

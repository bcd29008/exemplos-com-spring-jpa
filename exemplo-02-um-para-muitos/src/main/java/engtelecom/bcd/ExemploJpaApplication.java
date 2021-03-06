package engtelecom.bcd;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import engtelecom.bcd.entities.Campus;
import engtelecom.bcd.entities.Curso;
import engtelecom.bcd.repository.CampusRepository;
import engtelecom.bcd.repository.CursoRepository;
import lombok.extern.slf4j.Slf4j;

/**
 * A anotação SpringBootApplication é para indicar que essa é uma aplicação SpringBoot
 * 
 * A anotação @Slf4j é da biblioteca Lombok e permite usar com facilidade o serviço de log
 */
@Slf4j 
@SpringBootApplication
public class ExemploJpaApplication {

	
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
	public CommandLineRunner demoUmParaMuitos(CampusRepository campusRepository, CursoRepository cursoRepository){
		return (args) -> {
			
			log.info("Iniciando aplicação");


			// Criando duas entidades Campus
			Campus campusSje = new Campus("São José", "SJE", "Rua José Lino, 608", "São José");
			Campus campusFln = new Campus("Florianópolis", "FLN", "Avenida Mauro Ramos, 100", "Florianópolis");

			// Persistindo as entidades no banco de dados
			campusRepository.save(campusSje);
			campusRepository.save(campusFln);

			
			// Criando três entidades Curso e as persistindo no banco de dados
			cursoRepository.save(new Curso("Engenharia de Telecomunicações", 4300, campusSje));
			cursoRepository.save(new Curso("Engenharia de Computação", 4200, campusSje));
			cursoRepository.save(new Curso("Engenharia Elétrica", 4500, campusFln));
			

			// Listando todos os campus
			for (var element : campusRepository.findAll()) {
				System.out.println(element);
			}

			// Listando todos os cursos
			for (var element : cursoRepository.findAll()) {
				System.out.println(element);
			}

			// Listando todos os cursos que possuem carga horária maior que 4000. Está sendo feito uso do method reference para percorrer a lista ao invés de fazer um foreach como foi feito acima.
			// Outra opção para percorrer seria com expressões lambda
			// cursoRepository.findByCargaHorariaGreaterThan(4000).forEach(curso->{System.out.println(curso);});
			cursoRepository.findByCargaHorariaGreaterThan(4000).forEach(System.out::println);

			// Listando total de cursos no campus São José
			int totalCursosSje = cursoRepository.countByCampus(campusRepository.findBySigla("SJE"));

			System.out.println("Total de cursos no campus São José: " + totalCursosSje);
		};
	}
}

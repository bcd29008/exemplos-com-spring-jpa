package engtelecom.bcd;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import engtelecom.bcd.model.Campus;
import engtelecom.bcd.model.Curso;
import engtelecom.bcd.repository.CampusRepository;
import engtelecom.bcd.repository.CursoRepository;
import lombok.extern.slf4j.Slf4j;

/**
 * A anotação SpringBootApplication é para indicar que essa é uma aplicação
 * SpringBoot
 * 
 * A anotação @Slf4j é da biblioteca Lombok e permite usar com facilidade o
 * serviço de log
 */
@Slf4j
@SpringBootApplication
public class ExemploJpaApplication {

    @Autowired
    CampusRepository campusRepository;
    @Autowired
    CursoRepository cursoRepository;

    public static void main(String[] args) {
        // Método run para executar a aplicação. É necessário passar como parâmetro uma
        // classe que tenha a anotação de aplicação e, neste caso, que tenha uma Bean
        // para executar um cliente em linha de comando
        SpringApplication.run(ExemploJpaApplication.class, args);
        log.info("Aplicação finalizada");
    }

    private void povoando() throws Exception{
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
    }

    private void listandoRegistros() throws Exception{
        // Listando todos os campus
        System.out.println("--------------- Campus -------------------------");
        for (var element : campusRepository.findAll()) {
            System.out.println(element);
        }
        System.out.println("-----------------------------------------------");

        System.out.println("--------------- Cursos ------------------------");
        // Listando todos os cursos usando lambda
        cursoRepository.findAll().forEach(curso -> {
            System.out.println(curso);
        });
        System.out.println("-----------------------------------------------");

        // Listando todos os cursos que possuem carga horária maior que 4000. Está sendo
        // feito uso do method reference para percorrer a lista ao invés de fazer um
        // foreach como foi feito acima.
        //
        // Indicando que quer pegar a página 1, com 20 elementos, ordenado pelo campo
        // nome de forma crescente
        System.out.println("---------- Cursos páginado e ordenado ---------");
        Pageable pagina = PageRequest.of(0, 20, Sort.by("nome").ascending());
        cursoRepository.findByCargaHorariaGreaterThan(4000, pagina).forEach(System.out::println);
        System.out.println("-----------------------------------------------");

        System.out.println("--------------- Total de cursos ---------------");
        // Listando total de cursos no campus São José
        Optional<Campus> buscaCampus = campusRepository.findBySigla("SJE");
        if (buscaCampus.isPresent()) {
            int totalCursosSje = cursoRepository.countByCampus(buscaCampus.get());
            System.out.println("Total de cursos no campus São José: " + totalCursosSje);
        }
        System.out.println("-----------------------------------------------");
    }


    // Este método será invocado assim que a aplicação for executada
    @Bean
    public CommandLineRunner demoUmParaMuitos() {
        return (args) -> {
            try {
                log.info("Iniciando aplicação");

                this.povoando();
                this.listandoRegistros();
            } catch (Exception e) {
                log.error(e.toString());
            }
        };
    }
}

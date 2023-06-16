package engtelecom.bcd;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import engtelecom.bcd.model.Employee;
import engtelecom.bcd.model.JobHistory;
import engtelecom.bcd.repository.CourseRepository;
import engtelecom.bcd.repository.DepartmentRepository;
import engtelecom.bcd.repository.EmployeeRepository;

/**
 * A anotação abaixo é para indicar que essa é uma aplicação SpringBoot
 */
@SpringBootApplication
public class ExemploJpaApplication {

    @Autowired
    CourseRepository courseRepository;
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    EmployeeRepository employeeRepository;

    // O uso de Logger é uma boa prática para registrar informações de depuração,
    // erro, etc.
    private static final Logger log = LoggerFactory.getLogger(ExemploJpaApplication.class);

    public static void main(String[] args) {
        // Método run para executar a aplicação. É necessário passar como parâmetro uma
        // classe que tenha a anotação de aplicação e, neste caso, que tenha uma Bean para executar
        // um cliente em linha de comando
        SpringApplication.run(ExemploJpaApplication.class, args);
        log.info("Aplicação finalizada");
    }

    // Este método será invocado assim que a aplicação for executada.
    @Bean
    public CommandLineRunner demo() {
        return (args) -> {
            try {
                log.info("Iniciando aplicação");

                // Listando todos os cursos
                for (var element : courseRepository.findAll()) {
                    System.out.println(element);
                }

                System.out.println("Lista de aniversariamente do mês de março");
                // Listando todos os funcionários que fazem aniversário em março
                employeeRepository.findByAniversariantesNoMes(3).forEach(System.out::println);

                System.out.println("Cursos que aconteceram no ano de 1989");
                // Listando os nomes dos cursos que aconteceram em um ano
                courseRepository.findByCursosRealizadosEmUmAno(1989).forEach(curso -> {
                    System.out.println("Nome do curso: " + curso.getCname());
                });

                Optional<Employee> buscaEmp = employeeRepository.findById(1);

                // Se encontrou alguma entidade
                if (buscaEmp.isPresent()) {
                    Employee jones = buscaEmp.get();

                    List<JobHistory> historicoCargos = employeeRepository.findByDeCargosNaEmpresa(jones);

                    System.out.println("Lista de todos os cargos assumidos pelo funcionário: " + jones.getForenames());

                    StringBuilder sb = new StringBuilder();

                    sb.append(String.format("|%-10s|%-10s|%-40s|\n", "Ingresso", "Saída", "Nome do cargo"));
                    sb.append("----------------------------------------------------------------\n");

                    historicoCargos.forEach(cargo -> {
                        sb.append(String.format("|%-10s|%-10s|%-40s|\n", cargo.getStartdate(), cargo.getEnddate(),
                                cargo.getPosition()));
                    });
                    System.out.println(sb.toString());

                }

            } catch (Exception e) {
                log.error(e.toString());
            }
        };
    }
}

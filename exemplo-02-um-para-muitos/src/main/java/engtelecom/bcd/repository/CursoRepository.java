package engtelecom.bcd.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import engtelecom.bcd.entities.Campus;
import engtelecom.bcd.entities.Curso;

/**
 * The goal of the Spring Data repository abstraction is to significantly reduce the amount of boilerplate code required to implement data access layers for various persistence stores.
 * 
 * https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories
 * 
 * Métodos herdados da interface CrudRepository
 * https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/repository/CrudRepository.html
 * 
 * <S extends T> S save(S entity); // para persistir no BD a entidade
 *
 *  Optional<T> findById(ID primaryKey); // para obter uma entidade que possua um determinado ID
 * 
 *  Iterable<T> findAll(); para retornar todas entidades
 * 
 *  long count();  // para retornar o total de entidades
 * 
 * void delete(T entity); // para excluir uma entidade
 * 
 *  boolean existsById(ID primaryKey); // para verificar se existe uma entidade com determinado ID
 * 
 */
public interface CursoRepository extends CrudRepository<Curso, Long> {

    /*
        É possível criar consultas personalizadas apenas declarando os nomes dos métodos nesta interface
        e sem a necessidade de implementá-los. O Spring faz isso por ti. Só é necessário seguir algumas
        regras para os nomes dos métodos. Veja mais em:

        https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-methods.query-creation
        https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#appendix.query.method.subject

        No exemplo abaixo foi passado um parâmetro para permitir a ordenação dos resultados. Pode-se também fazer uso de
        parâmetros para fazer a paginação dos resultados (útil quando a tabela tem muitas linhas). Veja mais em:
        
        https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.paging-and-sorting
    */

    // Listar todos os cursos que possuam o nome informado como parâmetro
    List<Curso> findByNome(String nomeDoCurso);

    // Listar todos os cursos que possuam o nome informado como parâmetro, porém sem que os nomes sejam repetidos e não sensível a caixa (alta ou baixa)
    List<Curso> findDistinctByNomeIgnoreCase(String nomeDoCurso);

    // Listar todos os cursos que possuam o nome informado como parâmetro e que seja ordenado pelo nome
    // se colocar Desc no final no nome do método então será ordem decrescente
    List<Curso> findByNomeContainingOrderByNome(String nomeDoCurso);

    // Contar o total de cursos do campus informado como parâmetro
    int countByCampus(Campus campus);


    // Combinando múltiplos critérios
    List<Curso> findByCampusAndCargaHoraria(Campus campus, int cargaHoraria);

    // Listando os cursos que estão com NULL em sua carga horária
    List<Curso> findByCargaHorariaIsNull();

    // Listando os cursos cujo nome inicia com uma String (i.e. Engenharia)
    List<Curso> findByNomeStartingWith(String prefixo);

    // Listando os cursos cujo nome termina com uma String (i.e. Telecomunicações)
    List<Curso> findByNomeEndingWith(String sufixo);

    // Listando os cursos cujo nome contém uma String (i.e. tele)
    List<Curso> findByNomeContaining(String padrao);

    // Listando os cursos que possuam carga horária maior que o valor informado
    // Para menor seria LessThan, menor igual LessThanEqual
    List<Curso> findByCargaHorariaGreaterThan(int valor);

    // Listando todos os cursos cuja carga horário esteja entre os valores informados
    List<Curso> findByCargaHorariaBetween(int inicio, int fim);

    // Apagando curso por nome
    void deleteByNome(String nome);
}

package engtelecom.bcd.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import engtelecom.bcd.entities.Campus;
import engtelecom.bcd.entities.Curso;


/**
 * Rather than return everything from a large result set, Spring Data REST recognizes some URL parameters that influence the page size and the starting page number.
 * If you extend PagingAndSortingRepository<T, ID> and access the list of all entities, you get links to the first 20 entities. 
 * 
 * https://docs.spring.io/spring-data/rest/docs/current/reference/html/#paging-and-sorting
 * 
 * 
 */
@RepositoryRestResource(collectionResourceRel = "cursos", path = "cursos")
public interface CursoRepository extends PagingAndSortingRepository<Curso, Long>, CrudRepository<Curso, Long> {

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
    Page<Curso> findByNome(String nomeDoCurso, Pageable pageable);

    // Listar todos os cursos que possuam o nome informado como parâmetro, porém sem que os nomes sejam repetidos e não sensível a caixa (alta ou baixa)
    Page<Curso> findDistinctByNomeIgnoreCase(String nomeDoCurso, Pageable pageable);

    // Listar todos os cursos que possuam o nome informado como parâmetro e que seja ordenado pelo nome
    // se colocar Desc no final no nome do método então será ordem decrescente
    Page<Curso> findByNomeContainingOrderByNome(String nomeDoCurso, Pageable pageable);

    // Contar o total de cursos do campus informado como parâmetro
    int countByCampus(Campus campus);

    // Combinando múltiplos critérios
    List<Curso> findByCampusAndCargaHoraria(Campus campus, int cargaHoraria);

    // Listando os cursos que estão com NULL em sua carga horária
    List<Curso> findByCargaHorariaIsNull();

    // Listando os cursos cujo nome inicia com uma String (i.e. Engenharia)
    Page<Curso> findByNomeStartingWith(String prefixo, Pageable pageable);

    // Listando os cursos cujo nome termina com uma String (i.e. Telecomunicações)
    Page<Curso> findByNomeEndingWith(String sufixo, Pageable pageable);

    // Listando os cursos cujo nome contém uma String (i.e. tele)
    Page<Curso> findByNomeContaining(String padrao, Pageable pageable);

    // Listando os cursos que possuam carga horária maior que o valor informado
    // Para menor seria LessThan, menor igual LessThanEqual
    Page<Curso> findByCargaHorariaGreaterThan(int valor, Pageable pageable);

    // Listando todos os cursos cuja carga horário esteja entre os valores informados
    Page<Curso> findByCargaHorariaBetween(int inicio, int fim, Pageable pageable);

    // Apagando curso por nome
    void deleteByNome(String nome);
}

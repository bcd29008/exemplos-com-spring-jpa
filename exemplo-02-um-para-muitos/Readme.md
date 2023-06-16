# Exemplo JPA com um relacionamento um-para-muitos

Abaixo é apresentado do diagrama ER do modelo relacional do exemplo presente neste diretório. Trata-se de um exemplo simples para permitir observar como os atributos das classes Java serão mapeados para as colunas em uma tabela no MySQL e como criar um relacionamento um-para-muitos de Campus para Curso, que com JPA também requer que na classe Curso seja criado uma anotação muitos-para-um.

![Diagrama ER](campus-curso.png)

## Configuração do Spring para conexão com o banco de dados MySQL 


Editar o arquivo [src/main/java/resources/application.properties](src/main/java/resources/application.properties) e incluir as informações sobre a conexão com o servidor MySQL [conforme indicado aqui](../Readme.md#configuração-do-spring-para-conexão-com-o-banco-de-dados-mysql).


## Derivando métodos de consultas com Spring Data JPA

O [Spring Data JPA](https://spring.io/projects/spring-data-jpa) permite executar diferentes tipos de consultas com base nos nomes de métodos da classe Entidade (*Derived Query Methods*). Para tal o nome do método é divido em dois componentes separados pelo delimitador `By`.

1. **Introdutor**
   - `find`, `read`, `query`, `count` ou `get` 
   - Indica ao Spring Data JPA o que você deseja fazer com o método e pode conter outras expressões, como o `Distinct`
2. **Critério**
   - Aparece após o delimitador `By` e é aquilo que determinar o critério de seleção das tuplas. O critério pode ser concatenado com as palavras `And` e `Or`.

### Exemplos

```java

// Obter um campus pela Sigla
Optional<Campus> findBySigla(String sigla);

// Listar todos os campus que possuam o nome informado
List<Campus> findByNome(String nome);

// Listar todos os cursos que possuam o nome informado como parâmetro
List<Curso> findByNome(String nomeDoCurso);

// Listar todos os cursos que possuam o nome informado como parâmetro, porém sem que os nomes sejam repetidos
List<Curso> findDistinctByNome(String nomeDoCurso);

// Contar o total de cursos do campus informado como parâmetro
int countByCampus(Campus campus)
```
## Spring Data REST

Para esse exemplo faremos uso do [Spring Data REST](https://docs.spring.io/spring-data/rest/docs/current/reference/html) que permite facilmente criar recursos REST com base nos [repositórios](src/main/java/engtelecom/bcd/repository) usados para interagir com as [entidades](src/main/java/engtelecom/bcd/model). Sendo assim, foi necessário adicionar a seguinte linha na seção de dependências do arquivo [build.gradle](build.gradle):
```groovy
implementation 'org.springframework.boot:spring-boot-starter-data-rest'
```

O caminho do recurso é derivado do nome da classe e deixado no plural (respeitando as regras do idioma inglês). É possível alterar esse nome por meio da anotação `@RepositoryRestResource`. Exemplo:

```java
@RepositoryRestResource(collectionResourceRel = "campus", path = "campus")
public interface CampusRepository extends CrudRepository<Campus, Long>{
```

É possível adicionar facilmente a [paginação para uma coleção](https://docs.spring.io/spring-data/rest/docs/current/reference/html/#paging-and-sorting) se a interface do repositório estender a interface `PagingAndSortingRepository<T, ID>`. Exemplo:

```java
@RepositoryRestResource(collectionResourceRel = "cursos", path = "cursos")
public interface CursoRepository extends PagingAndSortingRepository<Curso, Long>, CrudRepository<Curso, Long> {
```

Assim, nas consultas personalizadas será necessário acrescentar um parâmetro do tipo `Pageable` e o tipo de retorno deverá ser do tipo `Page` ou `Slice` e não do tipo `List`. Exemplos:

```java
// Listando os cursos cujo nome inicia com uma String (i.e. Engenharia)
Page<Curso> findByNomeStartingWith(String prefixo, Pageable pageable);
```



### Endpoint disponibilizados

- http://localhost:8080/api/
- http://localhost:8080/api/campus
- http://localhost:8080/api/campus/search
- http://localhost:8080/api/cursos{?page,size,sort}
  - http://localhost:8080/api/cursos?page=0&size=20&sort=nome,asc
- http://localhost:8080/api/cursos/search

## Biblioteca Lombok

Neste exemplo foi feito uso da biblioteca [Lombok](https://projectlombok.org/), [veja mais detalhes aqui](../Readme.md#biblioteca-lombok).

## Executando o projeto

Abra a IDE e execute a classe [ExemploJpaApplication.java](src/main/java/engtelecom/bcd/ExemploJpaApplication.java) ou execute a tarefa gradle:

```bash
./gradlew bootRun
```

## Referências

- https://spring.io/guides/gs/accessing-data-jpa/
- https://docs.spring.io/spring-data/jpa/docs/current/reference/html/
- https://spring.io/guides/gs/accessing-data-rest/
- https://docs.spring.io/spring-data/rest/docs/current/reference/html
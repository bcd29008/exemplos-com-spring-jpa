# Uso do Spring Boot para acessar dados com JPA



Este repositório apresenta pequenos exemplos com o *framework* [Spring](https://spring.io) para persistir dados em um banco de dados MySQL. Nos exemplos é feito uso do [Spring Data JPA](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#reference) que faz uso de padrões de projeto [**Repository**](https://java-design-patterns.com/patterns/repository/) e [**Data Access Objects** (DAO)](https://java-design-patterns.com/patterns/dao/) e é baseado na especificação [**Java Persistence API** (JPA2)]((https://www.oracle.com/java/technologies/persistence-jsp.html)), usada por *frameworks* que fazem o mapeamento objeto-relacional ([*Object-Relational Mapping* - ORM](https://en.wikipedia.org/wiki/Object%E2%80%93relational_mapping)).

## Como criar o projeto Java

O [Spring Boot](https://spring.io/projects/spring-boot) permite a criação simplicada de aplicações isoladas, ideais durante a etapa de desenvolvimento, bem como para aplicações de produção baseadas no [*framework* Spring](https://spring.io/).

Com o Spring Boot não é necessário fazer qualquer configuração em arquivos `XML`, algo que seria típico com JPA, uma vez que algumas configurações são mantidas no arquivo `persistence.xml`. No Spring Boot toda configuração pode ser feita diretamente no código Java e por meio de arquivos de propriedades (*properties file*) para configurações de conexão com o banco de dados, entre outras.

Para cada um dos exemplos disponíveis neste repositório foi feito uso do [Spring Initializr](https://start.spring.io/) para criar o esqueleto do projeto. Se deseja criar um projeto como foi criado aqui, então siga os passos abaixo (Se estiver com o IntelliJ Ultimate, então é possível criar o projeto por lá sem a necessidade de ir para o *site* do Spring):

1. Gerar o projeto em https://start.spring.io/
   1. Project: gradle
   2. Language: Java
   3. Spring boot: 2.6.3
   4. Project metadata: 
       - group: engtelecom.bcd
       - packaging: jar
       - java: 11
   5. Dependências:
      1. Spring Data JPA
      2. MySQL Driver
      3. Spring Boot DevTools
2. Baixar o .ZIP contendo o projeto gradle, descompactá-lo em um pasta local e abrir esta pasta com o Visual Studio Code ou IntelliJ.

O [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/current/reference/html/using.html#using.devtools) inclui um conjunto de ferramentas para tornar mais agradável a experiência de desenvolvimento. De forma resumida, ele irá reiniciar automaticamente a aplicação sempre que notar alguma alteração nos arquivos contidos no *classpath*. Se não desejar tal comportamento, então você pode remover o Spring Boot DevTools da lista de dependências no arquivo `build.gradle`.

### Informando os dados para conexão no MySQL

O projeto criado na seção anterior terá o arquivo `src/main/java/resources/application.properties` onde são colocadas informações de configuração da aplicação, o que inclui, as informações de conexão com o banco de dados MySQL. Edite o arquivo e faça alterações nas seguintes propriedades:
```properties
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://localhost:3306/nome_do_database
spring.datasource.username=nome-do-usuario
spring.datasource.password=senha-do-usuario

spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

Veja se a propriedade `spring.datasource.driver-class-name` está com o valor `com.mysql.cj.jdbc.Driver`. Caso não esteja, faça a correção no arquivo.


### Próximos passos

Com o projeto criado e com as informações de conexão com MySQL definidas é hora de criar as classes Java contendo a lógica da sua aplicação. 

1. Criar um [POJO](https://pt.wikipedia.org/wiki/Plain_Old_Java_Objects) para cada entidade do banco
2. Criar uma interface para atuar Repositório de cada POJO e esta interface deverá herdar de alguma interface do Spring, por exemplo, `CrudRepository`.
3. Criar uma classe com o método `public static void main` a qual deverá ser anotada com `@SpringBootApplication`. 
4. Por fim, poderá executar a aplicação com a seguinte tarefa grade: `gradle bootRun`

## Exemplos disponíveis neste repositório

1. [Relacionamento um-para-um](exemplo-01-um-para-um)
2. [Relacionamento um-para-muitos](exemplo-02-um-para-muitos/)
3. [Relacionamento muitos-para-muitos](exemplo-03-muitos-para-muitos/)


## Referências
- https://docs.jboss.org/hibernate/annotations/3.5/reference/en/html/entity.html
- https://spring.io/guides
- https://spring.io/guides/gs/accessing-data-mysql/
- https://spring.io/guides/gs/accessing-data-jpa/
- https://docs.spring.io/spring-boot/docs/2.6.3/reference/htmlsingle/#data.sql.jpa-and-spring-data
- https://docs.spring.io/spring-boot/docs/2.6.3/reference/htmlsingle/#boot-features-spring-mvc-template-engines
- https://www.oracle.com/technical-resources/articles/javase/persistenceapi.html
- https://www.baeldung.com/jpa-many-to-many
- https://attacomsian.com/blog
- http://querydsl.com/
- https://www.oracle.com/corporate/features/project-lombok.html
- https://projectlombok.org/
- [Official Gradle documentation](https://docs.gradle.org)
- [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.6.3/gradle-plugin/reference/html/)
- [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.6.3/gradle-plugin/reference/html/#build-image)
- [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.6.3/reference/htmlsingle/#boot-features-jpa-and-spring-data)
- [Thymeleaf](https://docs.spring.io/spring-boot/docs/2.6.3/reference/htmlsingle/#boot-features-spring-mvc-template-engines)
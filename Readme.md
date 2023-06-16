# Uso do Spring Boot para acessar dados com JPA



Este repositório apresenta pequenos exemplos com o *framework* [Spring](https://spring.io) para persistir dados em um banco de dados MySQL. 

Nos exemplos é feito uso do [Spring Data JPA](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#reference) que faz uso de padrões de projeto [**Repository**](https://java-design-patterns.com/patterns/repository/) e [**Data Access Objects** (DAO)](https://java-design-patterns.com/patterns/dao/) e é baseado na especificação [**Java Persistence API** (JPA2)]((https://www.oracle.com/java/technologies/persistence-jsp.html)), usada por *frameworks* que fazem o mapeamento objeto-relacional ([*Object-Relational Mapping* - ORM](https://en.wikipedia.org/wiki/Object%E2%80%93relational_mapping)).

## Como criar o projeto Java

O [Spring Boot](https://spring.io/projects/spring-boot) permite a criação simplicada de aplicações isoladas, ideais durante a etapa de desenvolvimento, bem como para aplicações de produção baseadas no [*framework* Spring](https://spring.io/).

Com o Spring Boot não é necessário fazer qualquer configuração em arquivos `XML`, algo que seria típico com JPA, uma vez que algumas configurações são mantidas no arquivo `persistence.xml`. No Spring Boot toda configuração pode ser feita diretamente no código Java e por meio de arquivos de propriedades (*properties file*) para configurações de conexão com o banco de dados, entre outras.

Para cada um dos exemplos disponíveis neste repositório foi feito uso do [Spring Initializr](https://start.spring.io/) para criar o esqueleto do projeto. Se deseja criar um projeto como foi criado aqui, então siga os passos abaixo (Se estiver com o IntelliJ Ultimate, então é possível criar o projeto por lá sem a necessidade de ir para o *site* do Spring):

1. Gerar o projeto em https://start.spring.io/
   1. Project: gradle - groovy
   2. Language: Java
   3. Spring boot: 3.1.0
   4. Project metadata: 
       - group: engtelecom.bcd
       - packaging: jar
       - java: 17
   5. Dependências:
      1. Spring Data JPA
      2. MySQL Driver
      3. Spring Boot DevTools
2. Baixar o .ZIP contendo o projeto gradle, descompactá-lo em um pasta local e abrir esta pasta com o Visual Studio Code ou IntelliJ.

O [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/current/reference/html/using.html#using.devtools) inclui um conjunto de ferramentas para tornar mais agradável a experiência de desenvolvimento. De forma resumida, ele irá reiniciar automaticamente a aplicação sempre que notar alguma alteração nos arquivos contidos no *classpath*. Se não desejar tal comportamento, então você pode remover o Spring Boot DevTools da lista de dependências no arquivo `build.gradle`.

## Servidor MySQL

Para executar esse exemplo é necessário que tenha um servidor MySQL disponível. Você subir um rapidamente dentro de contêiner com o Docker. Basta executar a linha abaixo:

```bash
docker run -d --rm -p 3306:3306 -e MYSQL_ROOT_PASSWORD=senhaRoot -e MYSQL_DATABASE=bcd -e MYSQL_USER=aluno -e MYSQL_PASSWORD=aluno -e MYSQL_ROOT_HOST='%' --name meumysql mysql/mysql-server:latest
```

Cabe lembrar que sempre que o contêiner for parado, ele será excluído (opção `--rm` no comando acima) e todos os dados serão perdidos. Se quiser que os dados continuem mesmo depois da parada e exclusão do contêiner, então passe o parâmetro `-v $(pwd)/db_data:/var/lib/mysql` que fará o mapeamento do diretório usado pelo MySQL no contêiner para um diretório no computador hospedeiro.

### Configuração do Spring para conexão com o banco de dados MySQL

O projeto criado na seção anterior terá o arquivo `src/main/java/resources/application.properties` onde são colocadas informações de configuração da aplicação, o que inclui, as informações de conexão com o banco de dados MySQL.

Edite o arquivo e faça alterações nas seguintes propriedades:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/bcd
spring.datasource.username=aluno
spring.datasource.password=aluno
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
```


## Próximos passos

Com o projeto criado e com as informações de conexão com MySQL definidas é hora de criar as classes Java contendo a lógica da sua aplicação. 

1. Criar um [POJO](https://pt.wikipedia.org/wiki/Plain_Old_Java_Objects) para cada entidade do banco
2. Criar uma interface para atuar Repositório de cada POJO e esta interface deverá herdar de alguma interface do Spring, por exemplo, `CrudRepository`.
3. Criar uma classe com o método `public static void main` a qual deverá ser anotada com `@SpringBootApplication`. 
4. Por fim, poderá executar a aplicação com a seguinte tarefa grade: `gradle bootRun`

## Exemplos disponíveis neste repositório

1. [Relacionamento um-para-um](exemplo-01-um-para-um)
2. [Relacionamento um-para-muitos](exemplo-02-um-para-muitos/)
3. [Relacionamento muitos-para-muitos a partir de um banco de dados existente](exemplo-03-muitos-para-muitos/)
4. [Relacionamento muitos-para-muitos - exemplo de uma Livraria](exemplo-04-muitos-para-muitos-livraria/)

## Biblioteca Lombok

A biblioteca [Lombok](https://projectlombok.org/) que tem por objetivo tornar a escrita de códigos Java mais ágil. Por exemplo, ao criar um POJO o desenvolvedor não precisará criar manualmente (mesmo que a IDE faça isso por ele) métodos `get`,  `set`, `toString`, construtores, etc. Tudo isso pode ser obtido de forma automática, em tempo de compilação, por meio de anotações Java.

Para usar o Lombok é necessário [adicionar o plugin](https://plugins.gradle.org/plugin/io.freefair.lombok) na seção correspondente no arquivo `build.gradle` e a IDE precisa ter suporte ao Lombok. O IntelliJ já tem o plugin do Lombok ativo por padrão, porém o Visual Studio Code não tem. Sendo assim, se estiver com o Visual Studio Code clique no painel de extensões, procure por `lombok` e instale a extensão [Lombok Annotations Support for VS Code](https://marketplace.visualstudio.com/items?itemName=GabrielBB.vscode-lombok)


## Referências

- https://www.datafaker.net/documentation/getting-started/
- https://docs.jboss.org/hibernate/annotations/3.5/reference/en/html/entity.html
- https://spring.io/guides
- https://spring.io/guides/gs/accessing-data-mysql/
- https://spring.io/guides/gs/accessing-data-jpa/
- https://docs.spring.io/spring-boot/docs/2.6.3/reference/htmlsingle/#data.sql.jpa-and-spring-data
- https://docs.spring.io/spring-boot/docs/2.6.3/reference/htmlsingle/#boot-features-spring-mvc-template-engines
- https://www.oracle.com/technical-resources/articles/javase/persistenceapi.html
- https://www.baeldung.com/jpa-many-to-many
- https://www.baeldung.com/jpa-persisting-enums-in-jpa
- https://attacomsian.com/blog
- http://querydsl.com/
- https://www.oracle.com/corporate/features/project-lombok.html
- https://projectlombok.org/
- [Official Gradle documentation](https://docs.gradle.org)
- [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.6.3/gradle-plugin/reference/html/)
- [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.6.3/gradle-plugin/reference/html/#build-image)
- [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.6.3/reference/htmlsingle/#boot-features-jpa-and-spring-data)
- [Thymeleaf](https://docs.spring.io/spring-boot/docs/2.6.3/reference/htmlsingle/#boot-features-spring-mvc-template-engines)
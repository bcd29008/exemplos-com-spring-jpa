# Exemplo JPA com um relacionamento um-para-um

Editar o arquivo [src/main/java/resources/application.properties](src/main/java/resources/application.properties) e incluir as informações sobre a conexão com o servidor MySQL.
   ```properties
    spring.jpa.hibernate.ddl-auto=update
    spring.datasource.url=jdbc:mysql://localhost:3306/nome_do_database
    spring.datasource.username=nome-do-usuario
    spring.datasource.password=senha-do-usuario
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
   ```

A propriedade `spring.jpa.hibernate.ddl-auto` deve ser deixada com o valor `update` na primeira vez que for executar a aplicação. Isso fará com que as tabelas sejam criadas no MySQL. Uma vez que o modelo não mude mais, então seria interessante trocar de `update` para `none` e assim evitar que as tabelas sejam modificadas nas execuções subsequentes da aplicação.

## Modelo

Abaixo é apresentado do diagrama ER do modelo relacional do exemplo presente neste diretório. Trata-se de um exemplo simples para permitir observar como os atributos das classes Java serão mapeados para as colunas em uma tabela no MySQL e como criar um relacionamento um-para-um entre Pessoa e Endereço.

![Diagrama ER](pessoa-endereco.png)

Cabe frisar que no diagrama acima a cardinalidade máxima um-para-um só será respeitada se na coluna `id_pessoa` da tabela Endereço for criada uma restrição UNIQUE (ou INDEX) e isto é feito automaticamente pelo JPA quando se usa a anotação `OneToOne`.


## Executando o projeto

Abra a IDE e execute a classe [ExemploJpaApplication.java](src/main/java/engtelecom/bcd/ExemploJpaApplication.java) ou execute a tarefa gradle:

```bash
./gradlew bootRun
```
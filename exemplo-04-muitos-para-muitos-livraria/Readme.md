# Livraria

Neste exemplo é demonstrado o uso de 

- `Enum` (idioma do livro e situação do pedido)
- `@Embeddable` e `@Embedded` para indicar que a entidade `Dimensão` não terá uma tabela específica. Seus atributos serão incluídos na tabela gerada para a entidade `Edicao`
- `@EmbeddedId` para chave primária composta (entidade `Edição`)
- `@IdClass` quando se deseja um relacionamento muitos-para-muitos com chave estrangeira composta e quando se deseja ter colunas adicionais neste relacionamento
- Classe [`LivrariaRunner`](src/main/java/engtelecom/bcd/LivrariaRunner.java) tem toda a lógica da aplicação e seu método `run` é invocado assim que aplicação é executada.
  - Irá criar o esquema, povoar com alguns dados, listar os dados e depois alterar os valores de um pedido
- A anotação `@EqualsAndHashCode` do Lombok precisa excluir os atributos que são coleções e fazem parte de algum relacionamento. Ex: `@EqualsAndHashCode(exclude = {"livros"})` na classe [`Editora.java`](src/main/java/engtelecom/bcd/entities/Editora.java)

## Modelo

![modelagem livraria](livraria.png)

## Executando o projeto

Abra a IDE e execute a classe [LivrariaApplication.java](src/main/java/engtelecom/bcd/LivrariaApplication.java) ou execute a tarefa gradle:

```bash
./gradlew bootRun
```

## Biblioteca Lombok

Neste exemplo foi feito uso da biblioteca [Lombok](https://projectlombok.org/), [veja mais detalhes aqui](../Readme.md#biblioteca-lombok).

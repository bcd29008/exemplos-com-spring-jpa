package engtelecom.bcd;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import engtelecom.bcd.enums.Idiomas;
import engtelecom.bcd.enums.Situacao;
import engtelecom.bcd.model.Autor;
import engtelecom.bcd.model.Cliente;
import engtelecom.bcd.model.Dimensao;
import engtelecom.bcd.model.Edicao;
import engtelecom.bcd.model.EdicaoId;
import engtelecom.bcd.model.Editora;
import engtelecom.bcd.model.ItemDoPedido;
import engtelecom.bcd.model.Livro;
import engtelecom.bcd.model.Pedido;
import engtelecom.bcd.repositories.AutorRepository;
import engtelecom.bcd.repositories.ClienteRepository;
import engtelecom.bcd.repositories.EdicaoRepository;
import engtelecom.bcd.repositories.EditoraRepository;
import engtelecom.bcd.repositories.ItemDoPedidoRepository;
import engtelecom.bcd.repositories.LivroRepository;
import engtelecom.bcd.repositories.PedidoRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datafaker.Faker;

/**
 * 
 * Classe principal da aplicação Livraria
 * 
 * Anotação Componente fará com que a classe seja detectada e registrada de
 * forma automática
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class LivrariaRunner implements CommandLineRunner {

    @NonNull
    @Autowired
    private AutorRepository autorRepository;
    @NonNull
    @Autowired
    private ClienteRepository clienteRepository;
    @NonNull
    @Autowired
    private EdicaoRepository edicaoRepository;
    @NonNull
    @Autowired
    private EditoraRepository editoraRepository;
    @NonNull
    @Autowired
    private ItemDoPedidoRepository itemDoPedidoRepository;
    @NonNull
    @Autowired
    private LivroRepository livroRepository;
    @NonNull
    @Autowired
    private PedidoRepository pedidoRepository;

    private final Faker faker = new Faker();

    private Edicao novaEdicao(int numero, Livro livro, Editora editora) {
        var data = new java.sql.Date(faker.date().past(1000, TimeUnit.DAYS).getTime());
        var dimensoes = new Dimensao(faker.number().randomDouble(1, 10, 30), faker.number().randomDouble(1, 10, 30),
                faker.number().randomDouble(1, 2, 5));
        var isbn = faker.number().digits(13);
        var preco = faker.number().randomDouble(2, 40, 250);
        var paginas = faker.number().numberBetween(100, 400);

        var edicao = new Edicao(new EdicaoId(numero, livro.getIdLivro()), isbn, data, paginas, preco, dimensoes,
                editora, livro);

        return edicao;
    }

    private void povoandoBanco() {

        // Adicionando um cliente
        var nome = faker.name().fullName();
        var email = faker.internet().emailAddress();
        var endereco = faker.address().streetAddress();
        var cpf = faker.cpf().valid(true);
        var dataNascimento = new java.sql.Date(faker.date().birthday().getTime());

        var cliente = new Cliente(nome, cpf, endereco, dataNascimento, email);
        clienteRepository.save(cliente);
        // ------------------------------ //

        // Adicionando 2 autores
        var autor = new Autor(faker.name().firstName(), faker.name().lastName());
        var autor2 = new Autor(faker.name().firstName(), faker.name().lastName());
        autorRepository.save(autor);
        autorRepository.save(autor2);
        // ------------------------------ //

        // Adicionando uma editora
        var editora = new Editora();
        editora.setNome(faker.book().publisher());
        editora.setCidade(faker.address().city());
        editoraRepository.save(editora);
        // ------------------------------ //

        // Adicionando um livro
        var livro = new Livro();
        livro.setDescricao(faker.backToTheFuture().quote());
        livro.setTitulo(faker.book().title());
        livro.setIdioma(Idiomas.EN);
        livro.adicionarAutor(autor);
        livro.adicionarAutor(autor2);
        livroRepository.save(livro);
        // ------------------------------ //

        // Adicionando 2 edições
        var edicao = novaEdicao(1, livro, editora);
        var edicao2 = novaEdicao(2, livro, editora);
        edicaoRepository.save(edicao);
        edicaoRepository.save(edicao2);
        // ------------------------------ //

        // Adicionando um pedido
        var data = new java.sql.Date(faker.date().past(100, TimeUnit.DAYS).getTime());
        var pedido = new Pedido(cliente, data, Situacao.ANALISE);
        pedidoRepository.save(pedido);

        var itemDoPedido = new ItemDoPedido(pedido, edicao, edicao.getPreco(), 1);
        itemDoPedidoRepository.save(itemDoPedido);
        // ------------------------------ //

        // Adicionando um pedido com dois itens
        var data2 = new java.sql.Date(faker.date().past(100, TimeUnit.DAYS).getTime());
        var pedido2 = new Pedido(cliente, data2, Situacao.ANALISE);
        pedidoRepository.save(pedido2);

        var item1 = new ItemDoPedido(pedido2, edicao, edicao.getPreco(), 1);
        var item2 = new ItemDoPedido(pedido2, edicao2, edicao2.getPreco(), 3);
        itemDoPedidoRepository.save(item1);
        itemDoPedidoRepository.save(item2);
        // ------------------------------ //
    }

    public void listandoDados() {

        System.out.println(" ------ Todas as linhas de todas as tabelas ------ ");
        // Listando todas as linhas de todas as tabelas
        clienteRepository.findAll().forEach(System.out::println);
        editoraRepository.findAll().forEach(System.out::println);
        autorRepository.findAll().forEach(System.out::println);
        livroRepository.findAll().forEach(System.out::println);
        edicaoRepository.findAll().forEach(System.out::println);
        pedidoRepository.findAll().forEach(System.out::println);
        System.out.println(" ------------------------------------------------- ");

    }

    public void alterandoSituacaoPedido() {
        System.out.println(" ------ Atualizando situação de pedido ----------- ");
        
        var pedido = pedidoRepository.findById(1);
        
        if (pedido.isPresent()) {
            var p = pedido.get(); // obtendo objeto do tipo Pedido

            System.out.println("--------- Antes de atualizar --------- ");
            System.out.println("Pedido: "+ p.getIdPedido());
            System.out.println("Situação: " + p.getSituacao());
            p.setSituacao(Situacao.APROVADO);
            pedidoRepository.save(p);
            System.out.println("\n--------- Depois de atualizar -------- ");
            System.out.println("Pedido: "+ p.getIdPedido());
            System.out.println("Situação: " + p.getSituacao());
        }
        System.out.println(" ------------------------------------------------- ");
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            log.info("Iniciando a aplicação");

            this.povoandoBanco();
            this.listandoDados();
            this.alterandoSituacaoPedido();

        } catch (Exception e) {
            log.error(e.toString());
        }

    }
}

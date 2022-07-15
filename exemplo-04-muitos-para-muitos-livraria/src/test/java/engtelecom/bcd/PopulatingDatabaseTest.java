package engtelecom.bcd;

import engtelecom.bcd.entities.*;
import engtelecom.bcd.repositories.*;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import net.datafaker.Faker;

@SpringBootTest
class PopulatingDatabaseTest {


	@Autowired
	ClienteRepository clienteRepository;
	@Autowired
	AutorRepository autorRepository;
	@Autowired
	EdicaoRepository edicaoRepository;
	@Autowired
	EditoraRepository editoraRepository;
	@Autowired
	LivroRepository livroRepository;
	@Autowired
	PedidoRepository pedidoRepository;
	@Autowired
	ItemDoPedidoRepository itemDoPedidoRepository;

	private Faker faker = new Faker();

	private Cliente getCliente(){		
		var nome = faker.name().fullName();
		var email = faker.internet().emailAddress();
		var endereco = faker.address().streetAddress();
		var cpf = faker.number().digits(11);

		var dataNascimento = new java.sql.Date(faker.date().birthday().getTime());
		
		var cliente = new Cliente(nome, cpf, endereco, dataNascimento, email); 

		return cliente;
	}

	private Autor getAutor(){
		var nome = faker.name().firstName();
		var sobrenome = faker.name().lastName();
		return new Autor(nome, sobrenome);
	}


	private Editora getEditora(){
		var editora = new Editora();

		editora.setNome(faker.book().publisher());
		editora.setCidade(faker.address().city());

		return editora;
	}

	@Test
	@Order(1)
	void adicionarClientes() {
		getCliente();
	}

	@Test
	@Order(2)
	void adicionarLivro(){
		getEditora();
		getAutor();

	}

	@Test
	@Order(3)
	void adicionarPedido(){

	}
	



}

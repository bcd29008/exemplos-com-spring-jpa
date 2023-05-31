package engtelecom.bcd.entities;

import java.io.Serializable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
 * POJO para representar uma entidade Pessoa.
 * 
 * É necessário que a classe tem getter/setter, construtor padrão, construtor
 * com parâmetros, toString, hashCode() e equals(). Use a IDE para gerar isso
 * para você ou faça uso do projeto Lombok (https://projectlombok.org).
 * 
 * A anotação Entity indica que isso será uma entidade JPA no mapeamento ORM
 * 
 * A anotação Table pode ser usada para indicar o nome da tabela que será
 * criada. Se não for informada, então o nome da classe será usado como o nome
 * da Tabela. Neste exemplo, a anotação Table poderia ser suprimida
 */
@Entity
@Table(name = "Pessoa")
public class Pessoa implements Serializable {

    /**
     * A anotação Id indica que o atributo é a chave primária da entidade
     * 
     * A anotação GeneratedValue indica a estratégia para geração dos valores da
     * chave primário ao criar uma nova entidade. No exemplo abaixo a chave primária
     * será configurada no MySQL com AUTOINCREMENT
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAluno;

    /**
     * A anotação Column pode ser usada para especificar o nome da coluna na tabela
     * (name) e se não será permitido valor nulo na coluna
     */
    @Column(nullable = false)
    private String nome;

    private String email;

    @Column(nullable = false, unique = true)
    private String cpf;

    /**
     * A anotação OneToOne serve para indicar o relacionamento um-para-um entre as
     * entidades. Em relacionamento bidirecionais é necessário especificar esta
     * anotação em ambas as entidades (como foi feito neste exemplo), porém somente
     * uma das entidades será a dona da associação (indicada pela propriedade
     * mappedBy).
     * 
     * A propriedade fetch indica a estratégia para obtenção dos dados de uma
     * entidade associada. A estratégia padrão é EAGER e indica que ao obter uma
     * entidade, todas as entidades relacionadas também são obtidas do banco. A
     * estratégia LAZY só traz as entidades relacionadas quando for necessário.
     * 
     * A propriedade cascade indica se operações em cascata devem ser aplicadas na
     * entidade associada. Por exemplo, ao excluir uma pessoa deve-se excluir em
     * cascata o endereço?
     */
    @OneToOne(mappedBy = "pessoa", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Endereco endereco;

    /**
     * É obrigatório ter um construtor padrão, porém este pode ser protected.
     */
    protected Pessoa() {
    }

    public Pessoa(String nome, String email, String cpf) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
    }

    public Integer getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(Integer idAluno) {
        this.idAluno = idAluno;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append("Pessoa [cpf=" + cpf + ", email=" + email + ", idAluno=" + idAluno + ", nome=" + nome);

        if (this.endereco != null) {
            sb.append(", endereco=" + endereco);
        }
        sb.append("]");

        return sb.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idAluno == null) ? 0 : idAluno.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Pessoa other = (Pessoa) obj;
        if (idAluno == null) {
            if (other.idAluno != null)
                return false;
        } else if (!idAluno.equals(other.idAluno))
            return false;
        return true;
    }
}

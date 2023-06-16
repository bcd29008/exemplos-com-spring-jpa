package engtelecom.bcd.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

/**
 * 
 * POJO para representar uma entidade Endereco.
 * 
 * É necessário que a classe tem getter/setter, construtor padrão, construtor
 * com parâmetros, toString, hashCode() e equals(). Use a IDE para gerar isso
 * para você ou faça uso do projeto Lombok (https://projectlombok.org).
 */
@Entity
public class Endereco implements Serializable{

    /**
     * A anotação Id indica que o atributo é a chave primária da entidade
     * 
     * A anotação GeneratedValue indica a estratégia para geração dos valores da
     * chave primário ao criar uma nova entidade. No exemplo abaixo a chave primária
     * será configurada no MySQL com AUTOINCREMENT
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEndereco;

    private String rua;
    private String cidade;
    private String estado;
    private String cep;

    /**
     * A anotação OneToOne serve para indicar o relacionamento um-para-um entre as
     * entidades. Em relacionamento bidirecionais é necessário especificar esta
     * anotação em ambas as entidades (como foi feito neste exemplo), porém somente
     * uma das entidades será a dona da associação (indicada pela propriedade
     * mappedBy).
     * 
     * 
     * A propriedade fetch indica a estratégia para obtenção dos dados de uma
     * entidade associada. A estratégia padrão é EAGER e indica que ao obter uma
     * entidade, todas as entidades relacionadas também são obtidas do banco. A
     * estratégia LAZY só traz as entidades relacionadas quando for necessário.
     * 
     * A propriedade optional indica se o relacionamento é opcional ou não.
     * 
     * A anotação JoinColumn é usada para especificar o nome da chave estrangeira na
     * entidade dona do relacionamento (no caso, esta entidade). No lado inverso (na
     * entidade Pessoa) é feito uso da propriedade mappedBy para indicar que o
     * relacionamento é mapeado por outra entidade.
     * 
     * A propriedade name indica qual será o nome da coluna da chave estrangeira e a
     * propriedade nullable indica se é permitido valores nulos na chave estrangeira
     * 
     * OneToOne irá criar uma restrição UNIQUE no campo id_pessoa da tabela
     * endereço. Ou seja, isso vai garantir que não será possível ter mais de uma
     * pessoa associada ao mesmo endereço
     */
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_pessoa")
    private Pessoa pessoa;

    /**
     * Por ser uma entidade, o JPA requer um construtor padrão, porém este pode ser protected.
     */
    protected Endereco() {
    }

    /**
     * Esse construtor é para facilitar a criação de objetos já com todos os atributos preenchidos
     * @param rua
     * @param cidade
     * @param estado
     * @param cep
     * @param p
     */
    public Endereco(String rua, String cidade, String estado, String cep, Pessoa p) {
        this.rua = rua;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
        this.pessoa = p;
    }

    // Os métodos abaixo são necessários e todos foram gerados com ajuda da IDE.
    // O projeto Lombok (https://projectlombok.org) é uma alternativa para não ter que escrever explicitamente tais métodos

    public Integer getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(Integer idEndereco) {
        this.idEndereco = idEndereco;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    @Override
    public String toString() {
        return "Endereco [cep=" + cep + ", cidade=" + cidade + ", estado=" + estado + ", idEndereco=" + idEndereco
                + ", rua=" + rua + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idEndereco == null) ? 0 : idEndereco.hashCode());
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
        Endereco other = (Endereco) obj;
        if (idEndereco == null) {
            if (other.idEndereco != null)
                return false;
        } else if (!idEndereco.equals(other.idEndereco))
            return false;
        return true;
    }

}

package engtelecom.bcd.entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import lombok.AccessLevel;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(exclude = { "pedidos", "eR" })
@RequiredArgsConstructor
@ToString(exclude = { "pedidos", "eR" })
@Entity
public class Cliente implements Serializable{

    /**
     * Expressão regular para verificar se uma String é um endereço de email válido
     * 
     * A anotação Transient indica que não deve ser criada uma coluna na tabela para
     * ela
     */
    @Transient
    private final String eR = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCliente;

    @NonNull
    @Column(nullable = false)
    private String nome;

    @NonNull
    @Column(nullable = false)
    private String cpf;

    @NonNull
    private String endereco;

    @NonNull
    private Date dataNascimento;

    @OneToMany(mappedBy = "cliente")
    private Set<Pedido> pedidos = new HashSet<>();

    /**
     * Para demonstrar que aqui se deseja implementar manualmente o método setEmail
     * e que o Lombok não deverá gerar este método
     */
    @Setter(value = AccessLevel.NONE)
    @NonNull
    private String email;

    /**
     * Se o valor informado não for um endereço de email válido, então deixar como
     * vazio o valor do atributo email
     * 
     * @param email endereço de email
     */
    public void setEmail(String email) {
        this.email = (email.matches(eR)) ? email : "";
    }
}
